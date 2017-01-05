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
        Response.ResponseBuilder builder = Response.seeOther(URI.create("http://localhost:8080/gamerBankWeb/"));
//        String returnString = timeBank.getFormattedBalance(hours + timeBank.getBalance());

        if (hours > Integer.MAX_VALUE) {
//            returnString = "That is not a legal number. It's too high.";
        } else if (hours < Integer.MIN_VALUE) {
//            returnString = "That is not a legal number. It's too low.";
        } else {
            try {
                timeBank.storeBalance(hours);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        builder.entity(returnString);
        return builder.build();
    }

}
