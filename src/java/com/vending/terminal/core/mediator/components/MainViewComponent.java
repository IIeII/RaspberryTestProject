package com.vending.terminal.core.mediator.components;

import com.vending.terminal.utils.AppLogger;

import javax.swing.*;
import java.awt.*;

public class MainViewComponent {

    private JFrame mainFrame;
    private JLabel balance;

    public MainViewComponent() {
    }

    public void createView(){

        mainFrame = new JFrame("Vending!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setAlwaysOnTop(true);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);

        mainFrame.setVisible(true);

        JLabel label = new JLabel("Balance:");
        balance = new JLabel("0.00");
        mainFrame.add(label);
        mainFrame.add(balance);

        Font labelFont = label.getFont();
        int componentWidth = mainFrame.getWidth();
        double widthRatio = (double)componentWidth / (double) label.getPreferredSize().width;
        int newFontSize = (int)(labelFont.getSize() * widthRatio * 0.7);

        label.setFont(new Font(labelFont.getName(), Font.PLAIN, newFontSize));
        balance.setFont(new Font(labelFont.getName(), Font.PLAIN, newFontSize));
    }

    public void updateDate(Number value){
        balance.setText(value.toString());
        AppLogger.getInstance().log("Balance setted to: " + balance);
    }
}
