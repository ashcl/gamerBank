package com.bytonsoftware.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

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
}
