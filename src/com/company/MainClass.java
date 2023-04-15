package com.company;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass extends JFrame {

    JEditorPane editorPane = new JEditorPane();
    ErrorHandlerClass errorHandlerClass = new ErrorHandlerClass();
    FileManagementClass fileManagementClass = new FileManagementClass();
    ArrayList<ProcessClass> listOfItems = new ArrayList<>();
    ArrayList<ProcessClass> queue = new ArrayList<>();
    public boolean isSorted = false;

    String itemsString = "-> FIFO\n-> Preemptive SJF\n-> Non-Preemptive SJF\n-> RR (with specified time quantum)" +
            "\n-> Priority with preemption\n-> Non-Preemptive priority";
    String welcomeText = "Welcome to our Program!\nFor start please select one of items that access" +
            "top of this program.\n" + itemsString + "\nAfter select click Run!";

    private void timer() {
        for (int j = 0; j < 10000; j++) {
            int temp = 0;
            if (j % 2 == 0)
                temp = j / 2;
            else
                temp = 2 * j;
        }
    }
    public void sortArrayArrival(ArrayList<ProcessClass> listOfItems) {
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
    public static void adder(int iterator,ArrayList<ProcessClass> listOfItems,ArrayList<ProcessClass> queue){
        queue.clear();
        for(int i=iterator;i<iterator+100;i++){
            queue.add(listOfItems.get(i));
        }
        iterator++;
    }

    private void frame() throws IOException {
        JPanel panel = new JPanel();
        JButton buttonRun = new JButton("Run");
        JButton buttonReset = new JButton("Reset");
        JButton buttonEnterItem = new JButton("Enter Item");
        JMenuBar menuBar = new JMenuBar();

        String[] schedulingMethods = {"FIFO", "Preemptive SJF", "Non-Preemptive SJF", "RR (with specified time quantum)", "Priority with preemption", "Non-Preemptive priority"};
        JComboBox<String> comboBox = new JComboBox<>(schedulingMethods);

        buttonRun.addActionListener(e -> {
            if (errorHandlerClass.ifFileExist(this)) {
                isSorted = fileManagementClass.readWriteSortArray(listOfItems, isSorted);


                switch (comboBox.getItemAt(comboBox.getSelectedIndex())) {
                    case "FIFO":
                        FIFOClass exe0=new FIFOClass();
                        exe0.findavgTime(listOfItems,queue);
                        break;
                        /*
                        int timeCounter = 0, complete = 0;
                        fifoClass = new FIFOClass();
                        queue.add(listOfItems.get(0));
                        for (int i = 1; i < 100000; i++) {
                            timer();
                            timeCounter++;
                            if (timeCounter - complete == queue.get(0).getPBurstTime()) {
                                complete += queue.get(0).getPBurstTime();
                                queue.remove(0);
                            }
                            if (timeCounter >= listOfItems.get(i).getPArrivalTime()) {
                                if (queue.size() <= 100) {
                                    queue.add(listOfItems.get(i));
                                }
                            } else {
                                i--;
                            }
                            System.out.println("t: " + timeCounter);
                            if (timeCounter <= 100) {
                                for (int j = 0; j < queue.size(); j++) {
                                    System.out.print("id: " + queue.get(j).getPId() + ", b: " + queue.get(j).getPBurstTime() + " / ");
                                }
                            }
                        }

                        editorPane.setText(fifoClass.ResultToString(listOfItems));
                        updateJFrame();
                        break;*/
                    case "Preemptive SJF":
                        psjf exe=new psjf();
                        exe.findavgTime(listOfItems,queue);
                        break;
                    case "Non-Preemptive SJF":
                        non_psjf exe1=new non_psjf();
                        exe1.findavgTime(listOfItems,queue);
                        break;
                    case "RR (with specified time quantum)":
                        break;
                    case "Priority with preemption":
                        p_priority exe2=new p_priority();
                        exe2.findavgTime(listOfItems,queue);
                        break;
                    case "Non-Preemptive priority":
                        nonp_priority exe3=new nonp_priority();
                        exe3.findavgTime(listOfItems,queue);
                        break;
                }
                updateJFrame();
            }
            updateJFrame();
        });

        buttonReset.addActionListener(e -> {
            try {
                fileManagementClass.createFile();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Error has been Occur!\nIf you opened file of" +
                        " this program please close it and try again.\nError message: " + exception, "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateJFrame();
        });

        menuBar.add(comboBox);
        menuBar.add(buttonRun);
        menuBar.add(buttonReset);
        menuBar.add(buttonEnterItem);

        editorPane.setText(welcomeText);
        setTitle("Scheduling CPU");
        setJMenuBar(menuBar);
        panel.add(editorPane);
        setBackground(java.awt.Color.lightGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 400);
        editorPane.setSize(getWidth() - 50, getHeight() - 50);
        editorPane.setEditable(false);
        add(panel);
        setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(jScrollPane);
        setLocationRelativeTo(getOwner());
        pack();
    }

    private void updateJFrame() {
        SwingUtilities.updateComponentTreeUI(this);
        invalidate();
        validate();
        repaint();
        pack();
    }

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        try {
            mainClass.frame();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainClass, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
