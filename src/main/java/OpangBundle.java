import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import config.Config;
import db.MongoDBConnection;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by fiyyanp on 10/25/2016.
 */
public class OpangBundle implements ConfiguredBundle<Config> {
    private static final Logger LOG = LoggerFactory.getLogger(OpangResource.class);
    private static final String EXCHANGE_NAME = "semut.opang";
    private static final String SERVICE_QUEUE = "serviceQueue";
    private MongoDBConnection dbCon;

    public OpangBundle() {
    }

    @Override
    public void run(Config configuration, Environment environment) throws Exception {
        dbCon = configuration.getMongoDb();
        final ExecutorService deliveryExecutor = environment.lifecycle()
                .executorService("opang-delivery-thread-pool")
                .maxThreads(configuration.getNumIndexingThreads()).build();

        configuration.getRabbitMq()
                .buildRetryInitialConnect(environment, deliveryExecutor, "opang", this::callback);

    }

    private void callback(Connection connection) {
        try {
            final Channel channel = connection.createChannel();
            channel.basicQos(1);
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
            channel.basicConsume(SERVICE_QUEUE, false, new OpangConsumer(channel, dbCon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }
}
