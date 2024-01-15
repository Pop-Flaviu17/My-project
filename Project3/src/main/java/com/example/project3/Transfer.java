package com.example.project3;

public class Transfer {
    private String date;
    private String amount;
    private String monetary;
    public Transfer(String date, String amount, String monetary){
        this.date=date;
        this.amount=amount;
        this.monetary=monetary;
    }
    public String getDate(){
        return date;
    }
    public String getAmount(){
        return amount;
    }
    public String getMonetary(){
        return monetary;
    }
}
