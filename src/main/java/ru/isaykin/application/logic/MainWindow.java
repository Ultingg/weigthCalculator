package ru.isaykin.application.logic;

import javax.swing.*;

import static ru.isaykin.application.logic.CalcLogic.measureWindow;
import static ru.isaykin.application.logic.CalcLogic.truckWindow;

public class MainWindow extends JFrame {
    private JButton measureWindowButton;
    private JButton truckWindowButton;
    private JLabel greetingsLabel;
    private JPanel mainPanel;

    public MainWindow() {
        truckWindowButton.addActionListener(e -> {
            truckWindow.setVisible(true);
            setVisible(false);
        });
        measureWindowButton.addActionListener(e -> {
            measureWindow.setVisible(true);
            setVisible(false);
        });


        //конфиг окна
        setTitle("Весовичок");
        setSize(500, 300);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);


    }
}
