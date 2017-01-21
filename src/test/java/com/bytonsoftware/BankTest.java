package com.bytonsoftware;

import com.bytonsoftware.business.TimeBank;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by clash on 1/5/17.
 */
public class BankTest {

    Client client;
    WebTarget target;
    TimeBank bank;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = this.client.target("http://localhost:8080/gamerBankWeb/resources/bank");
        this.bank = new TimeBank();
    }

    @Test
    public void testStoreLog() {
        try {
            this.bank.storeLog(1.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
