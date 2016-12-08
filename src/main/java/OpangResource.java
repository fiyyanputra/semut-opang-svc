import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import common.CommonApp;
import db.MongoDBConnection;
import model.ResultProfile;
import model.RequestBody;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class OpangResource {
    private static final Logger LOG = LoggerFactory.getLogger(OpangResource.class);
    private static final String ORDER_PROCESS = "07301";
    private static final String BID_PROCESS = "07302";
    private static final String ENDPOINT_URL = "http://167.205.7.226:65412/api/opank/getprofileojek";
//    private static final String ENDPOINT_URL = "http://bsts-svc.lskk.ee.itb.ac.id/dev/api/opank/getprofileojek";
    private MongoDBConnection db;
    private CommonApp common;
    private Channel mChannel;
    private ObjectMapper mapper = new ObjectMapper();

    public OpangResource(final Channel channel, final MongoDBConnection dbConnection) {
        this.common = new CommonApp();
        this.mChannel = channel;
        this.db = dbConnection;
    }

    private String SetOrder(RequestBody request) {
        //store to db
        db.connect();
        db.getCollection("order");
        Document doc = new Document();
        final Locale id = new Locale("in", "ID");
        final String orderID = java.util.UUID.randomUUID().toString();

        doc.append("order_id", orderID)
            .append("id_user", request.getIdUser())
            .append("asal", request.getAsal())
            .append("tujuan", request.getTujuan())
            .append("latitude_asal", request.getLatAsal())
            .append("longitude_asal", request.getLongTujuan())
            .append("latitude_tujuan", request.getLatTujuan())
            .append("longitude_tujuan", request.getLongTujuan())
            .append("tanggal_order", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", id).format(new Date()));

        db.collection.insertOne(doc);
        db.disconnect();
        //end

        //send notif to drivers
        try {
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .type("order")
                    .correlationId(request.getIdUser())
                    .build();

            String userId = request.getIdUser();

            //set params
            HashMap<String, String> params = new HashMap<>();
            params.put("Id_user_ojek", userId);

            //set headers
            HashMap<String, String> headers = new HashMap<>();
            headers.put("API-KEY", "SEMUT_ANDROID");
            headers.put("sessid", "0");
            headers.put("deviceid", "1234567");

            String response = common.sendRequest(ENDPOINT_URL, params, headers);
            ResultProfile profile = mapper.readValue(response, ResultProfile.class);

            String message = "{" +
                    "\"order_id\": \""+orderID+"\"," +
                    "\"asal\": \"" + request.getAsal() + "\"," +
                    "\"tujuan\": \"" + request.getTujuan() + "\"," +
                    "\"jarak\": \"" + request.getJarak() + "\"," +
                    "\"lat_asal\": \"" + request.getLatAsal() + "\"," +
                    "\"long_asal\": \"" + request.getLongAsal() + "\"," +
                    "\"lat_tujuan\": \"" + request.getLatTujuan() + "\"," +
                    "\"long_tujuan\": \"" + request.getLongTujuan() + "\"," +
                    "\"nama\": \"" + (profile.getSuccess() ? profile.getProfile().getName(): "-") + "\"," +
                    "\"id\": \"" + (profile.getSuccess() ? profile.getProfile().getID(): "-") + "\"," +
                    "\"gender\": \"" + (profile.getSuccess() ? profile.getProfile().getGender(): "-") + "\"," +
                    "\"phone\": \"" + (profile.getSuccess() ? profile.getProfile().getPhoneNumber(): "-") + "\"," +
                    "\"poin\": \"" + (profile.getSuccess() ? profile.getProfile().getPoin(): "-") + "\"," +
                    "\"poin_level\": \"" + (profile.getSuccess() ? profile.getProfile().getPoinlevel(): "-") + "\"" +
                    "}";

            for(int i=0; i<request.getDrivers().size(); i++ ){
                LOG.info(" [*] Send order to drivers: "+request.getDrivers().get(i));
                mChannel.basicPublish("", request.getDrivers().get(i), props, message.getBytes());
            }

        } catch (IOException e) {
            LOG.error("Something went wrong. Reason : " + e.getMessage(), e);
        }

        String response = "{\"status\": true,\"message\":\"order diproses\",\"order_id\": \""+orderID+"\"}";
        return response;
    }

    private String SetBid(RequestBody request) {
        //store to db
        db.connect();
        db.getCollection("bid");
        Document doc = new Document();

        doc.append("order_id", request.getOrderId())
                .append("id_user", request.getIdUser())
                .append("id_driver", request.getIdDriver())
                .append("latitude_driver", request.getLatDriver())
                .append("longitude_driver", request.getLongDriver())
                .append("harga", request.getHarga());

        db.collection.insertOne(doc);
        db.disconnect();
        //end

        //send notif to user
        try {
            String queueUser = "opang.user."+request.getIdUser();

            String driverId = request.getIdDriver();

            //set params
            HashMap<String, String> params = new HashMap<>();
            params.put("Id_user_ojek", "422");

            //set headers
            HashMap<String, String> headers = new HashMap<>();
            headers.put("API-KEY", "SEMUT_ANDROID");
            headers.put("sessid", "0");
            headers.put("deviceid", "1234567");

            String response = common.sendRequest(ENDPOINT_URL, params, headers);
            ResultProfile profile = mapper.readValue(response, ResultProfile.class);

            String message = "{" +
                    "\"order_id\": \""+ request.getOrderId()+"\"," +
                    "\"id_user\": \"" + request.getIdUser() + "\"," +
                    "\"id_user_driver\": \"" + request.getIdDriver() + "\"," +
                    "\"lat_driver\": \"" + request.getLatDriver() + "\"," +
                    "\"long_driver\": \"" + request.getLongDriver() + "\"," +
                    "\"harga\": \"" + request.getHarga() + "\"," +
                    "\"nama\": \"" + (profile.getSuccess() ? profile.getProfile().getName(): "-") + "\"," +
                    "\"gender\": \"" + (profile.getSuccess() ? profile.getProfile().getGender(): "-") + "\"," +
                    "\"phone\": \"" + (profile.getSuccess() ? profile.getProfile().getPhoneNumber(): "-") + "\"," +
                    "\"plat_number\": \"" + (profile.getSuccess() ? profile.getProfile().getPlatMotor(): "-") + "\"," +
                    "\"poin\": \"" + (profile.getSuccess() ? profile.getProfile().getPoin(): "-") + "\"," +
                    "\"poin_level\": \"" + (profile.getSuccess() ? profile.getProfile().getPoinlevel(): "-") + "\"" +
                    "}";

            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .type("bid")
                    .build();

            mChannel.basicPublish("", queueUser, props, message.getBytes("UTF-8"));
        } catch (IOException e) {
            LOG.error(this.getClass().getSimpleName()+" Something went wrong. Reason : " + e.getMessage(), e);
        }

        String response = "{\"status\": true,\"message\":\"tawaran diproses\"}";
        return response;
    }


    public String execute(RequestBody request) {
        switch (request.getProcess()) {
            case ORDER_PROCESS:
                return SetOrder(request);
            case BID_PROCESS:
                return SetBid(request);
            default:
                return  "";

        }
    }
}
