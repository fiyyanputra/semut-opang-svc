package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.RequestBody;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class CommonApp {
    private static final Logger LOG = LoggerFactory.getLogger(CommonApp.class);
    OkHttpClient client = new OkHttpClient();

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

    public String sendRequest(String url,HashMap<String, String> params, HashMap<String, String> headers) throws IOException {

        Request.Builder requestBuilder = new Request.Builder().url(url);

        if(headers != null){
            Set entries = headers.entrySet();
            Iterator iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                requestBuilder.addHeader(key, val);
            }
        }
        requestBuilder.post(getPostParam(params));
        Request request = requestBuilder.build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private okhttp3.RequestBody getPostParam(HashMap<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();

        for ( Map.Entry<String, String> entry : params.entrySet() ) {
            builder.add( entry.getKey(), entry.getValue() );
        }

        // Create RequestBody
        okhttp3.RequestBody formBody = builder.build();
        return formBody;

    }
}
