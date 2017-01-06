package com.bytonsoftware;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by clash on 1/5/17.
 */
public class BankTest {

    Client client;
    WebTarget target;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = this.client.target("http://localhost:8080/gamerBankWeb/resources/bank");
    }

    /*@Test
    public void testThatMaxLimitIsInPlace() {
        *//*Form form = new Form();
        form.param("hours", "2147483647");*//*
        Response legalResponse = this.target.path("/updateTime")
                .queryParam("hours", 2147483647)
                .request(MediaType.TEXT_PLAIN)
                .get();
        assertNotNull(legalResponse.getHeaderString("statusMessage"));

        String illegalResponse = this.target.path("/updateTime")
                .queryParam("hours", 2147483648d)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println(illegalResponse);
        assertNotNull(illegalResponse.equalsIgnoreCase("That is not a legal number. It's too high."));
    }

    @Test
    public void testThatMinLimitIsInPlace() {
        String legalResponse = this.target.path("/updateTime")
                .queryParam("hours", -2147483648)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println(legalResponse);
        assertFalse(legalResponse.equalsIgnoreCase("That is not a legal number. It's too low."));

        String illegalResponse = this.target.path("/updateTime")
                .queryParam("hours", -2147483649d)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println(illegalResponse);
        assertTrue(illegalResponse.equalsIgnoreCase("That is not a legal number. It's too low."));
    }*/

}
