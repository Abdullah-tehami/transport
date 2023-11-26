package com.example.transportmanagementsystem;

public class Student {
    private String name;
    private int id;
    private String password;
    private String accountNumber;
    private int semester;
    private StudentType type;

   public Student(String name,int id, String password, String accountNumber,int semester,StudentType type){
        this.name=name;
        this.semester=semester;
        this.id=id;
        this.accountNumber=accountNumber;
        this.password=password;
        this.type=type;
    }
    public String getName(){
       return name;
    }
    public int getID(){
       return this.id;
    }
    public StudentType getType(){
       return this.type;
    }
    public boolean checkLogin(String username, String password){
       if(this.name.equals(username) && this.password.equals(password)){
           System.out.println("UserFound");
           return true;
       }
       return false;
    }
    public boolean checkOnlinePayment(String accountNumber, String password){
        if(this.accountNumber.equals(accountNumber) && this.password.equals(password)){
            return true;
        }
        return false;
    }
    public String getDetails(){
        return "Name: " + this.getName() +
                "\nID: " + this.getID() +
                "\nType: " + this.getType();
    }
    public String toFileString() {
        return String.format("%s,%d,%s,%s,%d,%s", name, id, password, accountNumber, semester, type);
    }
}
