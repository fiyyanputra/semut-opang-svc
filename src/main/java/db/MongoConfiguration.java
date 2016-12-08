package db;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fiyyanp on 10/25/2016.
 */
public class MongoConfiguration {
    @JsonProperty
    private String host;
    @JsonProperty
    private String user;
    @JsonProperty
    private String password;
    @JsonProperty
    private String dbName;
    @JsonProperty
    private boolean isAuth;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }
}
