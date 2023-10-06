package com.example.fetchmobileassess;

public class Item {
    private int listId;
    private String name;
    private int id;

    public Item(int id, int listId, String name){
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    //Getters
    public int getId(){
        return id;
    }
    public int getListId(){
        return listId;
    }
    public String getName(){
        return name;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setListId(int listId){
        this.listId = listId;
    }
    public void setName(String name){
        this.name = name;
    }

}
