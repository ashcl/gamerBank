package com.bytonsoftware.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by clash on 1/4/17.
 */
public class BankUtil {

    public static String obtainBalance() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.1.11:8080/gamerBankWeb/resources/bank");
        String value = target.request(MediaType.TEXT_PLAIN).get(String.class);
        return value;
    }
}
