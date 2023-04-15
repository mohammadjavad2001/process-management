package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FramesProcesses extends JFrame {
    private JPanel panel = new JPanel(new GridLayout(100, 1000));
    private JButton[] jButtons = new JButton[100000];
    private int count = 0;

    public FramesProcesses() {
        setTitle("Processes");
        setBackground(java.awt.Color.lightGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 400));
        add(panel);
        setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(jScrollPane);
        setLocationRelativeTo(getOwner());
        pack();
    }

    public void createButtons(ArrayList<ProcessClass> listOfItems, int i) {
        String str = "Process ID:" + listOfItems.get(i).getPId() + "\nArrival Time: " + listOfItems.get(i).getPArrivalTime()
                + "\nPriority: " + listOfItems.get(i).getPPriority() + "\nBurst Time: " + listOfItems.get(i).getPBurstTime();

        jButtons[count] = new JButton();
        jButtons[count].setSize(80, 80);
        jButtons[count].setBackground(Color.green);
        jButtons[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtons[count].setActionCommand(str);
        jButtons[count].setText("Process:" + listOfItems.get(i).getPId());
        jButtons[count].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String choice = e.getActionCommand();
                JOptionPane.showMessageDialog(null, choice, "Process Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(jButtons[count]);
        count++;
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
        pack();
    }

    public void removeAllElementsOfJFrame(){
        panel.removeAll();
    }
}
