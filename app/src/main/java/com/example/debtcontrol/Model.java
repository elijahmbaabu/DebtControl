package com.example.debtcontrol;

public class Model {
    private final String id;
    private String name;
    private String itemList;
    private String total;



    public Model(String id, String name, String itemList, String total) {
        this.id = id;
        this.name = name;
        this.itemList = itemList;
        this.total = total;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getItemList() {
        return itemList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemList(String itemList) {
        this.itemList = itemList;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }
}
