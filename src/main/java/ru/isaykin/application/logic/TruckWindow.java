package ru.isaykin.application.logic;

import ru.isaykin.application.logic.model.Truck;

import javax.swing.*;

import static java.lang.Double.parseDouble;
import static ru.isaykin.application.logic.CalcLogic.*;

public class TruckWindow extends JFrame {
    private String truckNumber;
    private JPanel panel;
    private JLabel truckNumberLabel;
    private JLabel truckWeightLabel;
    private JTextField truckNumberTextField;
    private JTextField truckWeightTextField;
    private JTextField frontPriceTextField;
    private JTextField rearPriceTextField;
    private JLabel frontPriceLabel;
    private JLabel rearPriceLabel;
    private JTextField firstWheelTextField;
    private JLabel firstWheelWeightLabel;
    private JLabel nameLabel;
    private JButton saveButton;
    private JButton updateButton;
    private JLabel actualTruckNumberLabel;
    private JLabel actualFrontPriceLabel;
    private JLabel actualRearPriceLabel;
    private JLabel actualTruckWeightLabel;
    private JLabel actualFirstWheelWeightLabel;
    private JButton mainWindowButton;
    private JButton measureWindowButton;

    public TruckWindow() {
        Truck newTruck = new Truck();

        saveButton.addActionListener(e -> {
            try {
                newTruck.setTruckNumber(truckNumberTextField.getText());
                newTruck.setFrontPrice(parseDouble(frontPriceTextField.getText()));
                newTruck.setRearPrice(parseDouble(rearPriceTextField.getText()));
                newTruck.setTruckWeight(parseDouble(truckWeightTextField.getText()));
                newTruck.setFirstWheelWeight(parseDouble(firstWheelTextField.getText()));
                measureWindow.addToComboBox(newTruck);
                measureWindow.configuration(newTruck);
                truckList.add(newTruck);
            } catch (NumberFormatException ex) {
                ex.getMessage();
                JOptionPane.showMessageDialog(null, "Введены неверные данные.");
            }

        });
        measureWindowButton.addActionListener(e -> {
            truckWindow.setVisible(false);
            measureWindow.setVisible(true);
        });

        mainWindowButton.addActionListener(e -> {
            truckWindow.setVisible(false);
            mainWindow.setVisible(true);
        });
//конфиг окна
        setTitle("Весовичок");
        setSize(500, 400);
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
