package com.probojnik.terminal.network;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
/**
 * @author Stanislav Shamji
 */
public class SendXML { // extends RequestTask

    // private static final URI SERVICE_EPR = null;
    // private static final String SOAPRequestXML = null;

    public static String sendXML(String... s) {
        String xml = s[1]; // "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request_type><row request_type=\"getterminfo\"/></request_type>";
        String responseXML = "";
        HttpPost httppost = new HttpPost(s[0]); // "http://188.163.194.90:8087/"
        try {
            StringEntity se = new StringEntity(xml, HTTP.UTF_8);

            se.setContentType("text/xml");
            httppost.setHeader("Content-Type", "application/soap+xml; charset=UTF-8");
            httppost.setEntity(se);

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(httppost);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                responseXML = EntityUtils.toString(entity, HTTP.UTF_8);
                System.out.println("entity = " + entity);
            } else {
                String hTTPStatus = httpResponse.getStatusLine().toString();
                System.err.println("SendXML | sendXML | HTTPStatus = " + hTTPStatus);
            }

            return responseXML;
        } catch (ClientProtocolException e) {
            System.err.println("SendXML | sendXML | ClientProtocolException | e = " + e);
        } catch (IOException e) {
            System.err.println("SendXML | sendXML | IOException | e = " + e);
        }

        return "";
    }

}
