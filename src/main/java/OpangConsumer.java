import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import common.CommonApp;
import db.MongoDBConnection;
import model.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by fiyyanp on 10/25/2016.
 */
public class OpangConsumer extends DefaultConsumer{
    private static final Logger LOG = LoggerFactory.getLogger(OpangConsumer.class);
    private static final String EXCHANGE_NAME = "semut.opang";
    private final MongoDBConnection dbConnection;
    private CommonApp common;

    public OpangConsumer(Channel channel, MongoDBConnection dbCon) {
        super(channel);
        common = new CommonApp();

        dbConnection = dbCon;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        if (getChannel().isOpen()) {
            AMQP.BasicProperties PROPS = new AMQP.BasicProperties().builder()
                    .correlationId(properties.getCorrelationId())
                    .replyTo(properties.getReplyTo())
                    .type("callback")
                    .build();

            String message = new String(body, "UTF-8");
            LOG.info(" [*] CONSUME REQUEST : " + message);
            RequestBody request = common.buildParams(message);
            String response = new OpangResource(getChannel(), dbConnection).execute(request);
            getChannel().basicAck(envelope.getDeliveryTag(), false);
            getChannel().basicPublish("", PROPS.getReplyTo(), PROPS, response.getBytes());
            LOG.info(" [*] PUBLISH CALLBACK: "+response);
        }
    }
}
