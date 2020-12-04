package ru.isaykin.application.logic;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static ru.isaykin.application.logic.CalcLogic.truckNumbersList;

public class Combo extends JFrame {

    private JComboBox comboBox;
    private DefaultComboBoxModel<String> model;
    private List<String> stringList;

    public Combo() {
        super("ККкомбо");
        stringList = truckNumbersList;
//        stringList = new ArrayList<>(asList("TYV855/DKN585", "MOY909/DKN586"));
        model = new DefaultComboBoxModel<>();
        for (String string : truckNumbersList) {
            model.addElement(string);
        }
        comboBox = new JComboBox(model);

        JPanel panel = new JPanel();
        panel.add(comboBox);
        setContentPane(panel);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
       new Combo();
    }
}
