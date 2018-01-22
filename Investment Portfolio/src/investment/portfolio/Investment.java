/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investment.portfolio;

import java.util.ArrayList;

/**
 *
 * @author parth
 */
public class Investment {
    
    private String type;
    private String symbol;
    private String name;
    private double quantity;
    private double price;
    private double bookValue;
    
    public Investment(String type, String symbol, String name, double quantity, double price, double bookValue){
        settype(type);
        setsymbol(symbol);
        setname(name);
        setquantity(quantity);
        setprice(price);
        setbookValue(bookValue);
    }
    
    public void settype(String type){
        this.type = type;
    }
    
    public String gettype(){
        return type;
    }
    
    public void setsymbol(String symbol){
        this.symbol = symbol;
    }
    
    public String getsymbol(){
        return symbol;
    }
    
    
    public void setname(String name){
        this.name = name;
    }
    
    public String getname(){
        return name;
    }
    
    
    public void setquantity(double quantity){
        this.quantity = quantity;
    }
    
    public double getquantity(){
        return quantity;
    }
    
    
    public void setprice(double price){
        this.price = price;
    }
    
    public double getprice(){
        return price;
    }
    
    
    public void setbookValue(double bookValue){
        this.bookValue = bookValue;
    }
    
    public double getbookValue(){
        return bookValue;
    }
    
    
}
