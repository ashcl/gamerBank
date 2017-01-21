package com.bytonsoftware.business;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by clash on 1/4/17.
 */
public class TimeBank {
    public String getFormattedBalance(double hours) {

        long wholeNum = (long) hours;
        BigDecimal minutes = new BigDecimal(hours - wholeNum);
        minutes = minutes.multiply(new BigDecimal(60));
        minutes = minutes.setScale(0, BigDecimal.ROUND_HALF_UP);
        long finalMinutes = minutes.longValue();

        return String.format("Time Available: %d hrs., %d min.", wholeNum, finalMinutes);
    }

    public void storeBalance(double hours) throws IOException {
        File file = new File("./timeSaved.txt");
        double output = 0.0;
        if (file.exists()) {
            try {
                output = getBalance() + hours;
                if (output < Integer.MAX_VALUE && output > Integer.MIN_VALUE) {
                    FileWriter fileWriter = new FileWriter("./timeSaved.txt");
                    fileWriter.write(String.valueOf(output));
                    fileWriter.close();
                } else {
                    System.out.println("Sorry, that is unacceptable. You have reached the maximum, or minimum, for your time.");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            FileManager.addFile(new File("."), "timeSaved.txt");
            FileWriter fileWriter = new FileWriter("./timeSaved.txt");
            fileWriter.write(String.valueOf(output));
            fileWriter.close();
        }
    }

    public double getBalance() {
        File file = new File("./timeSaved.txt");
        double hours = 0.0;
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                hours = Double.parseDouble(scanner.nextLine());
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return hours;
    }

    public void storeLog(double hoursEarned) throws IOException {
        File file = new File("./timeEditLog.txt");
        Date now = new Date();
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(now);
        String output = timeStamp + "|" + hoursEarned + "\n";
        if (file.exists()) {
            try {
                System.out.println("Log: " + output);
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
                fileWriter.append(output);
                fileWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            FileManager.addFile(new File("."), "timeEditLog.txt");
            System.out.println("Log: " + output);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("./timeEditLog.txt"));
            fileWriter.write(output);
            fileWriter.close();
        }
    }

    public JSONArray getLog() {
        JSONArray jsonArray = new JSONArray();
        JSONObject dateJSONObject;
        try {
            File logFile = new File("./timeEditLog.txt");
            FileReader fReader = new FileReader(logFile);
            BufferedReader bufferedReader = new BufferedReader(fReader);
            Stream<String> stringStream = bufferedReader.lines();
            Object[] lines = stringStream.toArray();
            for (int i = 0; i < lines.length; i++) {

                //Re-instantiate dateJSONObject so we don't get duplicate entries
                dateJSONObject = new JSONObject();

                //Get the whole log and parse it
                String unParsedLog = (String) lines[i];
                String date = unParsedLog.substring(0, unParsedLog.indexOf("|"));
                String hoursEarned = unParsedLog.substring(unParsedLog.indexOf("|") + 1, unParsedLog.length());

                //Put the parsed data into the JSONObject
                dateJSONObject.put("date", date);
                dateJSONObject.put("hours", hoursEarned);

                //Finally put the JSONObject into the json array
                jsonArray.put(dateJSONObject);
            }
            stringStream.close();
            bufferedReader.close();
            fReader.close();
            return jsonArray;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
