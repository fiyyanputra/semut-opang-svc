package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import db.MongoDBConnection;
import io.codemonastery.dropwizard.rabbitmq.ConnectionFactory;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by fiyyanp on 10/13/2016.
 */
public class Config extends Configuration{
    @JsonProperty
    private int numIndexingThreads;
    @JsonProperty
    private String mongoDBhost;
    @JsonProperty
    private Boolean mongoDBisAuth;
    @JsonProperty
    private String mongoDBuser;
    @JsonProperty
    private String mongoDBpassword;
    @JsonProperty
    private String mongoDBname;
    @Valid
    @NotNull
    private ConnectionFactory rabbitMq = new ConnectionFactory();
    @Valid
    @NotNull
    private MongoDBConnection mongoDb = new MongoDBConnection();

    /* getter */
    public ConnectionFactory getRabbitMq() {
        return rabbitMq;
    }

    public void setRabbitMq(ConnectionFactory rabbitMq) {
        this.rabbitMq = rabbitMq;
    }

    public MongoDBConnection getMongoDb() {
        return mongoDb;
    }

    public void setMongoDb(MongoDBConnection mongoDb) {
        this.mongoDb = mongoDb;
    }

    public int getNumIndexingThreads() {
        return numIndexingThreads;
    }

    public void setNumIndexingThreads(int numIndexingThreads) {
        this.numIndexingThreads = numIndexingThreads;
    }
}
