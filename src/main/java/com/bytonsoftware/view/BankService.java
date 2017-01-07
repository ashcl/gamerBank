package com.bytonsoftware.view;

import com.bytonsoftware.business.TimeBank;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

/**
 * Created by clash on 1/4/17.
 */
@Path("/bank")
public class BankService {

    TimeBank timeBank = new TimeBank();
    private int maxInputHours = 200;
    private int minInputHours = -200;

    @GET
    public String getHours() {
        return String.valueOf(timeBank.getFormattedBalance(timeBank.getBalance()));
    }

    @GET
    @Path("/updateTime")
    public Response addHours(@QueryParam("hours") double hours) {
        Response.ResponseBuilder builder = Response.seeOther(URI.create("http://192.168.1.25:8080/gamerBankWeb/"));

        if (hours < maxInputHours && hours > minInputHours) {
            try {
                timeBank.storeBalance(hours);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }

}
