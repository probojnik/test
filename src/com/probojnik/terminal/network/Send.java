package com.probojnik.terminal.network;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Send {
    private static Send instance;

    private Send(Context context) {
    }

    public static Send getInstance(Context context) {
        if(instance == null)
            instance = new Send(context);
        return instance;
    }

    public List<Map<String, String>> sendReq(Map<RequestParam, String> requestHashMap) {
        List<Map<String, String>> arrayList;
        arrayList = new ArrayList<Map<String, String>>();

        try {
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); // Post
            String getParams = ""; // Get

            for (RequestParam k : requestHashMap.keySet()) {
                System.out.println("Send | sendReq | params --- " + k + " = " + requestHashMap.get(k));
                if (k != RequestParam.URL) {
                    //nameValuePairs.add(new BasicNameValuePair(k.toString(), param.get(k).toString())); // Post
                    getParams += ((getParams.length() == 0) ? "?" : "&") + k + "=" + requestHashMap.get(k); // Get
                }
            }

            //HttpGet.setEntity(new UrlEncodedFormEntity(nameValuePairs)); // Post

            //HttpPost httpPost = new HttpPost(param.get("url")); // Post
            HttpGet httpGet = new HttpGet(requestHashMap.get(RequestParam.URL) + getParams); // Get

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpResponseEntity = httpResponse.getEntity();
                String httpResponseEntityString = EntityUtils.toString(httpResponseEntity, HTTP.UTF_8);
                System.out.println("Send | sendReq | httpResponseEntityString = " + httpResponseEntityString);

                arrayList = ParseXml.parseXML(httpResponseEntityString, requestHashMap); // 4
            } else {
                String hTTPStatus = httpResponse.getStatusLine().toString();
                System.err.println("Send | sendReq | HTTPStatus = " + hTTPStatus);
            }

            return arrayList;
        } catch (ClientProtocolException e) {
            System.err.println("Send | sendReq | ClientProtocolException = " + e);
        } catch (IOException e) {
            System.err.println("Send | sendReq | IOException = " + e);
        }
        return null;

    }

}