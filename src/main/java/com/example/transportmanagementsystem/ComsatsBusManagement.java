package com.example.transportmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComsatsBusManagement extends Application {
    private static List<Vehicle> transports;
    private static List<Route> routes;
    private static List<Student> students;
    private static Student currentUser;                                                  //
//    private static Vehicle selectedVehicle;
    private static Route selectedRoute;
    private static Payment paymentObject;
    private static BusPass busPass;
//    private static int vanRouteCount;

    //////////////////////////////
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(root, 800, 400);

        stage.setTitle("Comsats Bus Management System!");
        stage.setScene(scene);
        stage.show();
    }

    public static boolean logIn(String username, String password){
//        System.out.println(username);
//        System.out.println(password);
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).checkLogin(username, password)){
                currentUser = students.get(i);
                return true;
            }
        }
        return false;
    }
    public static void logout(){
        currentUser = null;
        selectedRoute = null;
        paymentObject = null;
        busPass = null;
    }

    public static List<Route> getRoutes(String vehicleType){
        List<Route> filteredRoutes = new ArrayList<>();
        for(int i=0; i<routes.size(); i++){
            Route r = routes.get(i);
            if(r.getVehicle().getAvailableSeats() == 0){
                System.out.println("no seats available");
                continue;
            }
            if(vehicleType.equals("Bus") && r.getVehicle() instanceof Bus){
                filteredRoutes.add(r);
            }
            else if(vehicleType.equals("Van") && r.getVehicle() instanceof Van){
                filteredRoutes.add(r);
            }

        }
        return filteredRoutes;
    }
    public static List<Vehicle> getVehicle(String vehicleType){
        List<Vehicle> filteredVehicle = new ArrayList<>();
        for (int i = 0; i < transports.size(); i++) {
            Vehicle v = transports.get(i);
            if(v.getAvailableSeats() == 0){
                System.out.println("no seats available");
                continue;
            }
            if (vehicleType == "Bus" && v instanceof Bus) {
                filteredVehicle.add(v);
            }
            if (vehicleType == "Van" && v instanceof Van) {
                filteredVehicle.add(v);
            }
        }
        return filteredVehicle;
    }
    public static Route addAndCreateVanRoute(String pickupPoint, Vehicle van){
//        vanRouteCount++;
        String routeName = "van-route-" + pickupPoint;
        Route r = new Route(routeName, pickupPoint, van);
        routes.add(r);
        return r;
    }
    public static void confirmTransport(Route route){
        selectedRoute = route;
    };
    public static String printPaymentDetails(){
        return "Student Details:\n" + currentUser.getDetails() +
                "\n\nFee Charges:\n" + selectedRoute.getVehicle().getPricingDetails(currentUser);
    }
    public static void confirmPaymentType(String selectedPaymentType){
        LocalDate createdAt = LocalDate.now();
        float amount = selectedRoute.getVehicle().getTotalCharges(currentUser);
        if(selectedPaymentType.equals("OnlinePayment")){
             Random random = new Random();
            // Generate a random number between 10^10 and 10^11 - 1
            long transactionId =  (long) (Math.pow(10, 10) + random.nextDouble() * Math.pow(10, 10));
            paymentObject = new OnlinePayment(amount, transactionId, createdAt);
        }
        else if(selectedPaymentType.equals("BankChallan")){
            Random random = new Random();
            // Generate a random number between 10^10 and 10^11 - 1
            long challanNumber =  (long) (Math.pow(10, 10) + random.nextDouble() * Math.pow(10, 10));
            LocalDate expiryDate = createdAt.plusDays(10);
            paymentObject = new BankChallan(amount, challanNumber, createdAt, expiryDate);
        }
    }
    public static String printBankChallan(){
        return paymentObject.printDetails();
    }
    public static String printOnlinePaymentDetails(){
        return paymentObject.printDetails();
    }
    public static boolean payNow(String accountNo, String passWord){
//        System.out.println(accountNo);
//        System.out.println(passWord);
        if(currentUser.checkOnlinePayment(accountNo, passWord)){
            generateBusPass();
            System.out.println("Online Payment Successful");
            return true;
        }
        System.out.println("Online Payment Failed");
        return false;
    }
    public static void generateBusPass(){
        selectedRoute.getVehicle().addPassanger(currentUser);
        busPass = new BusPass(currentUser, selectedRoute.getVehicle());
    }
    public static String printBusPass(){
        return busPass.printDetails(selectedRoute);
    }

//    public static void printAll(){
//        System.out.println("TRANSPORTS:");
//        System.out.println();
//    }
    public static void saveData(){
        writeTransportsToFile("transports_written.txt");
        writeRoutesToFile("routes_written.txt");
        writeStudentsToFile("students_written.txt");
    }
    private static void writeTransportsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Vehicle transport : transports) {
                writer.write(transport.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRoutesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Route route : routes) {
                writer.write(route.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeStudentsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadData(){
        readStudentsFromFile("students_written.txt");
        readVehiclesFromFile("transports_written.txt");
        readRoutesFromFile("routes_written.txt");
        System.out.println("Routes:");
        for (Route route : routes) {
            System.out.println(route);
        }
    }
    private static void readVehiclesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String number = parts[0].trim();
                    int totalSeats = Integer.parseInt(parts[1].trim());
                    float charges = Float.parseFloat(parts[2].trim());
                    String type = parts[3].trim();
                    System.out.println("LOOOL"+type);

                    Vehicle vehicle;
                    if(type.equals("Bus")){
                        vehicle = new Bus(number, totalSeats, charges);
                    }
                    else{
                        vehicle = new Van(number, totalSeats, charges);
                    }
                    if (parts.length > 4) {
                        for (int i = 4; i < parts.length; i++) {
                            int passengerId = Integer.parseInt(parts[i].trim());
                            Student associatedPassenger = findPassengerById(passengerId);
                            vehicle.addPassanger(associatedPassenger);
                        }
                    }


                    transports.add(vehicle);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Student findPassengerById(int passengerId){
        for (Student student : students) {
            if (student.getID() == passengerId) {
                return student;
            }
        }
        return null;
    }

    private static void readRoutesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String pickupPoint = parts[1].trim();
                    String vehicleNumber = parts[2].trim();

                    Vehicle associatedVehicle = findVehicleByNumber(vehicleNumber);
                    routes.add(new Route(name, pickupPoint, associatedVehicle));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Vehicle findVehicleByNumber(String number) {
        for (Vehicle vehicle : transports) {
            if (vehicle.getNumber().equals(number)) {
                return vehicle;
            }
        }
        return null;
    }
    private static void readStudentsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    String password = parts[2].trim();
                    String accountNumber = parts[3].trim();
                    int semester = Integer.parseInt(parts[4].trim());
                    StudentType type = StudentType.valueOf(parts[5].trim());

                    students.add(new Student(name, id, password, accountNumber, semester, type));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        transports = new ArrayList<>();
//        Bus b1 = new Bus("bus 001", 30, 20000, "Bus");
//        Bus b2 = new Bus("bus 002", 30, 20000);
//        Bus b3 = new Bus("bus 003", 30, 20000);
//        Van v1 = new Van("van 001", 12, 35000);
//        Van v2 = new Van("van 002", 12, 35000);
//        transports.add(b1);
//        transports.add(b2);
//        transports.add(b3);
//        transports.add(v1);
//        transports.add(v2);


        routes = new ArrayList<>();
//        Route r1 = new Route("Route 1", "Stop 1", b1);
//        Route r2 = new Route("Route 2", "Stop 2", b1);
//        Route r3 = new Route("Route 3", "Stop 3", b2);
//        Route r4 = new Route("Route 4", "Stop 4", b2);
//        Route r5 = new Route("Route 5", "Stop 5", b3);
//        routes.add(r1);
//        routes.add(r2);
//        routes.add(r3);
//        routes.add(r4);
//        routes.add(r5);

        students=new ArrayList<>();
//        Student s1 = new Student("student 1",001, "pas001", "acc-no-001",3,StudentType.Hostalite);
//        Student s2 = new Student("student 2",002, "pas002", "acc-no-002",2,StudentType.NonHostalite);
//        students.add(s1);
//        students.add(s2);

        String vehicleType = "Bus";


        loadData();
        for (int i = 0; i < transports.size(); i++) {
            Vehicle v = transports.get(i);
            if (vehicleType == "Bus" && v instanceof Bus) {
                v.printVehicle();
            }
            if (v instanceof Van) {
                v.printVehicle();
            }
            System.out.println();
        }

        launch();
    }
}