package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by fiyyanp on 10/25/2016.
 */
public class ManagerRabbitMQ {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerRabbitMQ.class);
    protected Channel mChannel = null;
    protected Connection mConnection;
    private static final String EXCHANGE_NAME = "semut.opang";
    private String userName;
    private String password;
    private String virtualHost;
    private String serverIP;
    private int port;
    private boolean running;

    public ManagerRabbitMQ(Config configuration) {
        this.userName = configuration.getRabbitMQUsername();
        this.password = configuration.getRabbitMQPassword();
        this.virtualHost = configuration.getRabbitMQVHost();
        this.serverIP = configuration.getRabbitMQHost();
        this.port = 5672;
    }

    public Channel connectToRabbitMQ(){
        if (mChannel != null && mChannel.isOpen()){//already declared
            running = true;
        }

        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/"+virtualHost);
        connectionFactory.setHost(serverIP);
        connectionFactory.setPort(port);

        try {
            mConnection = connectionFactory.newConnection();
            mChannel = mConnection.createChannel();

            LOG.info("Connect to rabbitMQ");
        } catch (IOException e) {
            LOG.info("Connection Failed, Reason : "+e.getMessage(), e);
        }

        registerChannelHost();
        return mChannel;
    }

    private void registerChannelHost() {
        try {
            mChannel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRunning() {
        return running;
    }
}
