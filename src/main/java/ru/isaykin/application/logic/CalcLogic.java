package ru.isaykin.application.logic;

import ru.isaykin.application.logic.model.Truck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcLogic {
    static MainWindow mainWindow;
    static TruckWindow truckWindow;
    static MeasureWindow measureWindow;
    static List<Truck> truckList;
    static List<String> truckNumbersList;

    public static void main(String[] args) {

        truckList = truckConfig();
        truckNumbersList = new ArrayList<>(Arrays.asList("TYV855/DKN585", "MOY909/DKN586"));
        mainWindow = new MainWindow();
        truckWindow = new TruckWindow();
        measureWindow = new MeasureWindow();

    }

    public static List<Truck> truckConfig() {
        List<Truck> trucks = new ArrayList<>();
        Truck truck585 = new Truck(
                "TYV855/DKN585",
                16510,
                6000,
                385,
                49);
        Truck truck586 = new Truck(
                "MOY909/DKN586",
                16530,
                5100,
                400,
                710);
        trucks.add(truck585);
        trucks.add(truck586);

        return trucks;
    }
}
