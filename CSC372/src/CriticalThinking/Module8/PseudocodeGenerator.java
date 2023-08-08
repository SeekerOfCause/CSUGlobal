package CriticalThinking.Module8;

import java.io.FileWriter;
import java.io.IOException;

public class PseudocodeGenerator {

    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("database_model_pseudocode.txt");

            // Pseudocode for Vehicle class
            writer.write("class Vehicle {\n");
            writer.write("    vehicleID: int\n");
            writer.write("    color: String\n");
            writer.write("}\n\n");

            // Pseudocode for Car class
            writer.write("class Car extends Vehicle {\n");
            writer.write("    make: String\n");
            writer.write("    model: String\n");
            writer.write("}\n\n");

            // Pseudocode for Truck class
            writer.write("class Truck extends Vehicle {\n");
            writer.write("    payloadWeightLimitLbs: int\n");
            writer.write("    truckBedSizeSqFt: int\n");
            writer.write("}\n\n");

            // Pseudocode for Driver class
            writer.write("class Driver {\n");
            writer.write("    driverID: int\n");
            writer.write("    name: String\n");
            writer.write("}\n\n");

            // Pseudocode for VehicleTable
            writer.write("table VehicleTable {\n");
            writer.write("    vehicleID: int\n");
            writer.write("    color: String\n");
            writer.write("}\n\n");

            // Pseudocode for CarTable
            writer.write("table CarTable {\n");
            writer.write("    vehicleID: int\n");
            writer.write("    color: String\n");
            writer.write("    make: String\n");
            writer.write("    model: String\n");
            writer.write("}\n\n");

            // Pseudocode for TruckTable
            writer.write("table TruckTable {\n");
            writer.write("    vehicleID: int\n");
            writer.write("    color: String\n");
            writer.write("    payloadWeightLimitLbs: int\n");
            writer.write("    truckBedSizeSqFt: int\n");
            writer.write("}\n\n");

            // Pseudocode for DriverTable
            writer.write("table DriverTable {\n");
            writer.write("    driverID: int\n");
            writer.write("    name: String\n");
            writer.write("}\n\n");

            // Pseudocode for CarDriverAssociationTable
            writer.write("table CarDriverAssociationTable {\n");
            writer.write("    vehicleID: int\n");
            writer.write("    driverID: int\n");
            writer.write("}\n");

            writer.close();
            System.out.println("Pseudocode has been generated and saved to 'database_model_pseudocode.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
