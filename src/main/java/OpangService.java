import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import config.Config;
import db.DBConnection;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rabbit.ManagerRabbitMQ;

import java.io.IOException;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class OpangService extends Application<Config> {
    public static void main(String[] args) throws Exception {
        new OpangService().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {

    }

    @Override
    public void run(Config configuration, Environment environment) throws Exception {
        ManagerRabbitMQ manage = new ManagerRabbitMQ(configuration);
        DBConnection dbCon = new DBConnection(configuration.getMongoDBhost(), configuration.getMongoDBisAuth(), configuration.getMongoDBuser(), configuration.getMongoDBpassword(), configuration.getMongoDBname());

        Channel mChannel = manage.connectToRabbitMQ();
        environment.jersey().register(new OpangResource(mChannel, dbCon));
    }
}

