package com.example.transportmanagementsystem;

public class Student {
    private String name;
    private int semester;
    private int id;
    private StudentType type;
    //private BusPass busPass;

   public Student(String name,int semester,int id,StudentType type){
        this.name=name;
        this.semester=semester;
        this.id=id;
        this.type=type;
    }
    public String getName(){
       return name;
    }
    public void setName(String name){
       this.name=name;
    }
}
