package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fiyyanp on 10/25/2016.
 */
public class MongoDBConnection extends MongoConfiguration{
    private MongoClient mongoClient;
    private MongoDatabase db;
    public MongoCollection<Document> collection;

    public MongoDBConnection() {

    }

    public void connect(){
        ServerAddress serverAddress = new ServerAddress(getHost(), 27017);
        if(isAuth()){
            MongoCredential credential = MongoCredential.createPlainCredential(getUser(), getDbName(), getPassword().toCharArray());
            List<MongoCredential> auths = new ArrayList<MongoCredential>();
            auths.add(credential);
            mongoClient = new MongoClient(serverAddress, auths);
        }else{
            mongoClient = new MongoClient(serverAddress);
        }

        db = mongoClient.getDatabase(getDbName());


    }

    public void disconnect(){
        mongoClient.close();
    }

    public void getCollection(String colName){
        collection = db.getCollection(colName);
    }
}
