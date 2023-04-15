package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FileManagementClass {
    Random random = new Random();
    private final String filePath = "ProcessInfo.BGH";

    public boolean fileExist() {
        File file = new File(filePath);
        return (file.exists() && !file.isDirectory());
    }

    public void createFile() {
        try {
            File file = new File(filePath);
            writeFile();
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < 100000; i++) {
                fileWriter.write(processInfo(i));
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Has Occurred: " + e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean readWriteSortArray(ArrayList<ProcessClass> listOfItems, boolean isSorted) {
        if (!isSorted) {
            if (readWriteFile(listOfItems)) {
                sortArrayArrival(listOfItems);
                JOptionPane.showMessageDialog(null, "Finish Sort", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Error Has Occurred!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void sortArrayArrival(ArrayList<ProcessClass> listOfItems) {
        int size = listOfItems.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (listOfItems.get(i).getPArrivalTime() > listOfItems.get(i + 1).getPArrivalTime()) {
                    int tpid = listOfItems.get(i + 1).getPId();
                    int tpat = listOfItems.get(i + 1).getPArrivalTime();
                    int tprioioty = listOfItems.get(i + 1).getPPriority();
                    int tpb = listOfItems.get(i + 1).getPBurstTime();

                    listOfItems.get(i + 1).setPId(listOfItems.get(i).getPId());
                    listOfItems.get(i + 1).setPArrivalTime(listOfItems.get(i).getPArrivalTime());
                    listOfItems.get(i + 1).setPPriority(listOfItems.get(i).getPPriority());
                    listOfItems.get(i + 1).setPBurstTime(listOfItems.get(i).getPBurstTime());

                    listOfItems.get(i).setPId(tpid);
                    listOfItems.get(i).setPArrivalTime(tpat);
                    listOfItems.get(i).setPPriority(tprioioty);
                    listOfItems.get(i).setPBurstTime(tpb);
                }
            }
        }
    }

    private boolean readWriteFile(ArrayList<ProcessClass> listOfItems) {
        int tempID, tempArrivalTime, tempPriority, tempBurstTime;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] splitData = decode(myReader.nextLine()).split(" ");
                tempID = Integer.parseInt(splitData[0]);
                tempArrivalTime = Integer.parseInt(splitData[1]);
                tempPriority = Integer.parseInt(splitData[2]);
                tempBurstTime = Integer.parseInt(splitData[3]);
                ProcessClass temp = new ProcessClass(tempID, tempArrivalTime, tempPriority, tempBurstTime);
                listOfItems.add(temp);
            }
            JOptionPane.showMessageDialog(null, "Finish", "Info", JOptionPane.INFORMATION_MESSAGE);
            myReader.close();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public String processInfo(int i) {
        String str = "";
        str += i + " ";
        str += i / 4 + " ";
        str += random.nextInt(6) + 1 + " ";
        str += random.nextInt(5) + 1 + "\n";
        return code(str);
    }

    private String code(String input) {
        /*String[] splitString = input.split("");
        String result = "";
        for (int i = 0; i < splitString.length; i++) {
            splitString[i] = Character.toString((char) (((int) splitString[i].charAt(0)) + 46));
            result += splitString[i];
        }
        return result + "\n";*/
        return input;
    }

    private String decode(String input) {
        /*String[] splitString = input.split("");
        String result = "";
        for (int i = 0; i < splitString.length; i++) {
            splitString[i] = Character.toString((char) (((int) splitString[i].charAt(0)) - 46));
            result += splitString[i];
        }
        return result + "\n";*/
        return input;
    }
}
