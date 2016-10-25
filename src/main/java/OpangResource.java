import com.rabbitmq.client.*;
import common.CommonApp;
import config.Config;
import db.DBConnection;
import model.RequestBody;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class OpangResource {
    private static final Logger LOG = LoggerFactory.getLogger(OpangResource.class);
    private static final String EXCHANGE_NAME = "semut.opang";
    private static final String SERVICE_QUEUE = "serviceQueue";
    private static final String ORDER_PROCESS = "07301";
    private static final String BID_PROCESS = "07302";
    private  Connection connection;
    private Config configuration;
    private CommonApp common;

    public OpangResource(final ConnectionFactory connectionFactory, final Config configuration) {
        this.common = new CommonApp();
        this.configuration = configuration;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            //System.out.println(" [x] Awaiting requests");
            LOG.info(" [x] Awaiting requests");

            while (true) {
                //consume serviceQueue
                channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
                channel.basicQos(1);
                Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope,
                                               AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, "UTF-8");

                        AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                                .Builder()
                                .correlationId(properties.getCorrelationId())
                                .replyTo(properties.getReplyTo())
                                .type("callback")
                                .build();

                        //System.out.println("[*] Consume request : "+message);
                        LOG.info(" [*] CONSUME REQUEST : "+message);
                        RequestBody request = common.buildParams(message);
                        String response = "";
                        switch (request.getProcess()) {
                            case ORDER_PROCESS:
                                response = SetOrder(request);
                                break;
                            case BID_PROCESS:
                                response = SetBid(request);
                                break;
                            default:
                                break;
                        }

                        channel.basicPublish( "", replyProps.getReplyTo(), replyProps, response.getBytes());
                        channel.basicAck(envelope.getDeliveryTag(), false);
//                        System.out.println("[*] Publish response: "+response);
                        LOG.info(" [*] PUBLISH RESPONSE: "+response);
                    }
                };
                channel.basicConsume(SERVICE_QUEUE, false, consumer);
            }
        } catch (IOException e) {
            LOG.error("Something went wrong. Reason : " + e.getMessage(), e);
        }
    }

    private String SetOrder(RequestBody request) {
        //store to db
        DBConnection db = new DBConnection(configuration.getMongoDBhost(), configuration.getMongoDBisAuth(), configuration.getMongoDBuser(), configuration.getMongoDBpassword(), configuration.getMongoDBname());
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
            String message = "{\"order_id\": \""+orderID+"\",\"asal\": \"" + request.getAsal() + "\",\"tujuan\": \"" + request.getTujuan() + "\",\"lat_asal\": \"" + request.getLatAsal() + "\",\"long_asal\": \"" + request.getLongAsal() + "\",\"lat_tujuan\": \"" + request.getLatTujuan() + "\",\"long_tujuan\": \"" + request.getLongTujuan() + "\"}";
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .type("order")
                    .correlationId(request.getIdUser())
                    .build();

            Channel ch = connection.createChannel();
            //ch.exchangeDeclare("semut.opang.order", "fanout", true);
            //ch.basicPublish("semut.opang.order", "", null, message.getBytes());
            for(int i=0; i<request.getDrivers().size(); i++ ){
//                System.out.println("[*] Send order to drivers: "+request.getDrivers().get(i));
                LOG.info(" [*] Send order to drivers: "+request.getDrivers().get(i));
                ch.basicPublish("", request.getDrivers().get(i), props, message.getBytes());
            }

        } catch (IOException e) {
            LOG.error("Something went wrong. Reason : " + e.getMessage(), e);
        }

        String response = "{\"status\": true,\"message\":\"order diproses\",\"order_id\": \""+orderID+"\"}";
        return response;
    }

    private String SetBid(RequestBody request) {
        //store to db
        DBConnection db = new DBConnection(configuration.getMongoDBhost(), configuration.getMongoDBisAuth(), configuration.getMongoDBuser(), configuration.getMongoDBpassword(), configuration.getMongoDBname());
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

            String message = "{\"order_id\": \""+ request.getOrderId()+"\",\"id_user\": \"" + request.getIdUser() + "\",\"id_user_driver\": \"" + request.getIdDriver() + "\",\"lat_driver\": \"" + request.getLatDriver() + "\",\"long_driver\": \"" + request.getLongDriver() + "\",\"harga\": \"" + request.getHarga() + "}";
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .type("bid")
                    .build();

            Channel ch = connection.createChannel();
            //ch.exchangeDeclare("semut.opang.bid", "fanout", true);
            //ch.basicPublish("semut.opang.bid", "", props, message.getBytes("UTF-8"));
            ch.basicPublish("", queueUser, props, message.getBytes("UTF-8"));
        } catch (IOException e) {
            LOG.error(this.getClass().getSimpleName()+" Something went wrong. Reason : " + e.getMessage(), e);
        }

        String response = "{\"status\": true,\"message\":\"tawaran diproses\"}";
        return response;
    }
}
