package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static void saveContract(Contract contract) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.txt", true))) { //save contract to file
            //convert contract to string format for file
            String contractData = contractToFileString(contract);
            writer.write(contractData);
            writer.newLine();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    //convert a contract to a string thats formatted for file
    private static String contractToFileString(Contract contract) {

        StringBuilder sb = new StringBuilder();
        // is contract an instance of sales contract or lease contract
        if (contract instanceof SalesContract) {

            SalesContract salesContract = (SalesContract) contract;

            sb.append("SALE|");
            sb.append(contract.getDate()).append("|");
            sb.append(contract.getCustomerName()).append("|");
            sb.append(contract.getCustomerEmail()).append("|");
            Vehicle vehicle = contract.getVehicleSold();
            sb.append(vehicle.getVin()).append("|");
            sb.append(vehicle.getYear()).append("|");
            sb.append(vehicle.getMake()).append("|");
            sb.append(vehicle.getModel()).append("|");
            sb.append(vehicle.getVehicleType()).append("|");
            sb.append(vehicle.getColor()).append("|");
            sb.append(vehicle.getOdometer()).append("|");
            sb.append(vehicle.getPrice()).append("|");
            sb.append(vehicle.getPrice() * 0.05).append("|");
            sb.append(100.00).append("|");
            sb.append(vehicle.getPrice() < 10000 ? 295.00 : 495.00).append("|");
            sb.append(salesContract.getTotalPrice()).append("|");
            sb.append(salesContract.isFinanceOption() ? "YES" : "NO").append("|");
            sb.append(salesContract.getMonthlyPayment());
        } else if (contract instanceof LeaseContract) {

            LeaseContract leaseContract = (LeaseContract) contract;

            sb.append("LEASE|");
            sb.append(contract.getDate()).append("|");
            sb.append(contract.getCustomerName()).append("|");
            sb.append(contract.getCustomerEmail()).append("|");
            Vehicle vehicle = contract.getVehicleSold();
            sb.append(vehicle.getVin()).append("|");
            sb.append(vehicle.getYear()).append("|");
            sb.append(vehicle.getMake()).append("|");
            sb.append(vehicle.getModel()).append("|");
            sb.append(vehicle.getVehicleType()).append("|");
            sb.append(vehicle.getColor()).append("|");
            sb.append(vehicle.getOdometer()).append("|");
            sb.append(vehicle.getPrice()).append("|");
            sb.append(vehicle.getPrice() * 0.50).append("|");
            sb.append(vehicle.getPrice() * 0.07).append("|");
            sb.append(leaseContract.getTotalPrice()).append("|");
            sb.append(leaseContract.getMonthlyPayment());

        }

        return sb.toString();
    }
}
