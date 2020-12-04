package ru.isaykin.application.logic;

import org.springframework.beans.factory.annotation.Autowired;
import ru.isaykin.application.logic.model.Measure;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;

import javax.swing.*;
import java.time.Instant;
import java.util.Date;

import static java.lang.Double.parseDouble;
import static java.lang.String.valueOf;
import static ru.isaykin.application.logic.CalcLogic.*;

public class MeasureWindow extends JFrame {
    private JPanel measurePanel;
    private JLabel regNumberNameLabel;
    private JLabel regNumberTextLabel;
    private JTextField frontBarTextField;
    private JLabel frontPriceLabel;
    private JLabel rearPriceLabel;
    private JLabel frontPriceTextLabel;
    private JLabel rearPriceTextLabel;
    private JTextField rearBarTextField;
    private JLabel frontBarLabel;
    private JLabel rearBarLabel;
    private JLabel frontAxeWeightLabel;
    private JLabel rearAxeWeight;
    private JLabel frontAxeWeightTextLabel;
    private JLabel rearAxeWeightTextLabel;
    private JLabel completeWeightLabel;
    private JLabel cargoWeightLabel;
    private JLabel completeWeightTextLabel;
    private JLabel cargoWeightTextLabel;
    private JPanel axePanel;
    private JPanel weightPanel;
    private JButton mainWindowButton;
    private JButton truckWindowButton;
    private JButton countButton;
    private JButton sendReportButton;
    private JLabel truckWeightLabel;
    private JLabel truckWeightTextLabel;
    private JComboBox truckNumberComboBox;
    private DefaultComboBoxModel<String> truckDefaultComboBoxModel;
    private Truck currentTruck;
    @Autowired
    private  MeasureRepository measureRepository;

    public MeasureWindow() {

        //население модели рег. номерами
        for (Truck truck : truckList) {
            truckDefaultComboBoxModel.addElement(truck.getTruckNumber());
        }

        //Данные грузовика по умолчанию
        currentTruck = new Truck(
                "TYV855/DKN585",
                16510,
                6000,
                385,
                495);

        sendReportButton.addActionListener(e -> {
            Measure measureToSend = new Measure();
            measureToSend.measureSet(currentTruck.getTruckNumber()
                    , parseDouble(frontAxeWeightTextLabel.getText())
                    , parseDouble(rearAxeWeightTextLabel.getText())
                    , parseDouble(completeWeightTextLabel.getText())
                    , parseDouble(truckWeightTextLabel.getText())
                    , parseDouble(cargoWeightTextLabel.getText()));
            measureRepository.save(measureToSend);

        });

        countButton.addActionListener(e -> {
            try {
                double frontBar = parseDouble(frontBarTextField.getText());
                double rearBar = parseDouble(rearBarTextField.getText());
                countWeights(currentTruck, frontBar, rearBar);
            } catch (NumberFormatException ex) {
                ex.getMessage();
                JOptionPane.showMessageDialog(null, "Введены неверные данные (используйте точку для разделения дробной части)");
            }


        });

        truckWindowButton.addActionListener(e -> {
            truckWindow.setVisible(true);
            setVisible(false);
        });
        mainWindowButton.addActionListener(e -> {
            mainWindow.setVisible(true);
            measureWindow.setVisible(false);
        });

//конфиг окна
        setContentPane(measurePanel);
        setTitle("Весовичок");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);


    }

    public void addToComboBox(Truck truck) {
        if (!truckList.contains(truck))
            truckDefaultComboBoxModel.addElement(truck.getTruckNumber());

    }

    public void configuration(Truck truckToMeasure) {
        regNumberTextLabel.setText(truckToMeasure.getTruckNumber());
        frontPriceTextLabel.setText(valueOf(truckToMeasure.getFrontPrice()));
        rearPriceTextLabel.setText(valueOf(truckToMeasure.getRearPrice()));
        truckWeightTextLabel.setText(valueOf(truckToMeasure.getTruckWeight()));
        currentTruck = truckToMeasure;
    }

    private void countWeights(Truck truckToCount, double frontBar, double rearBar) {
        double rearAxeWeight = truckToCount.getRearPrice() * 10 * rearBar;
        double frontAxeWeight = truckToCount.getFrontPrice() * 10 * frontBar - truckToCount.getFirstWheelWeight();
        double completeWeight = rearAxeWeight + frontAxeWeight + truckToCount.getFirstWheelWeight();
        double cargoWeight = completeWeight - truckToCount.getTruckWeight();
        cargoWeightTextLabel.setText(valueOf(cargoWeight));
        completeWeightTextLabel.setText(valueOf(completeWeight));
        frontAxeWeightTextLabel.setText(valueOf(frontAxeWeight));
        rearAxeWeightTextLabel.setText(valueOf(rearAxeWeight));
    }

    private void createUIComponents() {
        truckDefaultComboBoxModel = new DefaultComboBoxModel<String>();
        truckNumberComboBox = new JComboBox(truckDefaultComboBoxModel);
        // TODO: place custom component creation code here
    }
}
