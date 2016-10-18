import com.rabbitmq.client.ConnectionFactory;
import config.Config;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class OpangService extends Application<Config> {
    private static final Logger LOG = LoggerFactory.getLogger(OpangService.class);
    public static void main(String[] args) throws Exception {
        new OpangService().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {

    }

    @Override
    public void run(Config configuration, Environment environment) throws Exception {
        try {
            ConnectionFactory connectionFactory = createConnectionFactory(configuration);
            environment.jersey().register(new OpangResource(connectionFactory, configuration));
        } catch (IOException e) {
            LOG.error("Error connecting to RabbitMQ server. Reason : " + e.getMessage(), e);
        }
    }

    private ConnectionFactory createConnectionFactory(Config configuration) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(configuration.getRabbitMQUsername());
        factory.setPassword(configuration.getRabbitMQPassword());
        factory.setHost(configuration.getRabbitMQHost());
        factory.setVirtualHost("/"+configuration.getRabbitMQVHost());
        return factory;
    }
}

