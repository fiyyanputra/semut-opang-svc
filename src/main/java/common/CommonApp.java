package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class CommonApp {
    private static final Logger LOG = LoggerFactory.getLogger(CommonApp.class);

    public RequestBody buildParams(String message) {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody request = null;
        try {
            request = mapper.readValue(message, RequestBody.class);
            return request;

        } catch (IOException e) {
            LOG.error("Something went wrong. Reason : " + e.getMessage(), e);
        }
        return request;
    }
}
