package com.ps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for(Vehicle vehicle: this.inventory){
            if(vehicle.getPrice() > min && vehicle.getPrice() < max){
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for(Vehicle vehicle: this.inventory){
            if(vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehicleByYear(int min, int max){
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for(Vehicle vehicle: this.inventory){
            if(vehicle.getYear() > min && vehicle.getYear() < max){
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for(Vehicle vehicle: this.inventory){
            if(vehicle.getColor().equalsIgnoreCase(color)){
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max){
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for(Vehicle vehicle: this.inventory){
            if(vehicle.getOdometer() > min && vehicle.getOdometer() < max){
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByType(String type){
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
        for(Vehicle vehicle: this.inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(type)){
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getAllVehicles(){
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        this.inventory.remove(vehicle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void sellOrLeaseVehicle() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter VIN");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle is not in inventory.");
            return;
        }

        System.out.println("Enter contract date (YYYYMMDD):");
        String date = scanner.nextLine();
        System.out.println("What is the customer name:");
        String customerName = scanner.nextLine();
        System.out.println("What is the customer email:");
        String customerEmail = scanner.nextLine();

        System.out.println("Sale or lease? (Enter 'sale' or 'lease')");
        String contractType = scanner.nextLine().trim().toLowerCase();

        Contract contract;
        if (contractType.equals("sale")) {
            System.out.println("Does the customer want to finance? (yes/no)");
            String financeOption = scanner.nextLine().trim().toLowerCase();
            boolean isFinanced = financeOption.equals("yes");
            contract = new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
        } else if (contractType.equals("lease")) {
            if (vehicle.getYear() > 3) {
                System.out.println("You can't lease a vehicle over 3 years old.");
                return;
            }
            contract = new LeaseContract(date, customerName, customerEmail, vehicle);
        } else {
            System.out.println("Contract type does not exist.");
            return;
        }

        ContractFileManager.saveContract(contract);
        removeVehicle(vehicle);
        System.out.println("Contract saved.");
    }

    private Vehicle findVehicleByVin(int vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}