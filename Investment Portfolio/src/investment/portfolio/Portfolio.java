/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investment.portfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author parth
 */
public class Portfolio{

    
    public Portfolio(){
        list = new ArrayList<Investment>();
        map = new HashMap<String, ArrayList<Integer>>();
    }
        
    private ArrayList<Investment> list;
    
    HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

    public void setmap(HashMap map){
        this.map = map;
    }
    
    public HashMap<String, ArrayList<Integer>> getmap(){
        return map;
    }
    
    public void setlist(ArrayList list){
        this.list = list;
    }
    
    public ArrayList<Investment> getlist(){
        return list;
    }

    
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void buy(JTextArea information, String type, String symbol, String name, int quantity, double price){
        
        boolean flag = true;

        // Checking each stock in the ArrayList
        for (Investment I : list){
            // Checking for symbol in Stock
            if(I.getsymbol().equals(symbol) == true){                    
            I.setquantity(I.getquantity() + quantity);
            I.setprice(price);
            I.setbookValue((I.getquantity() * I.getprice()) + 9.99);
            flag = false;
            
            information.append("You purchased more quantity for this investment\n");
            }
            else{
                flag = true;
            }
        }
            
            
        if(flag == true){
            if(type == "Stock"){            
                double bookValue = (quantity * price) + 9.99; 
                list.add(new Stock(type, symbol, name, quantity, price, bookValue));
                information.append("Stock was purchased\n");
            }
            else{
                double bookValue = (quantity * price);           
                list.add(new MutualFund(type, symbol, name, quantity, price, bookValue));
                information.append("MutualFund was purchased\n");
            }
        }
                 
        //adding to hashmap
        String[] temp = new String[15];
              
        temp = name.split("\\s+");
        
        for(int i = 0; i < 15; i++){
            try{ 
                if(temp[i] == null){
                    break;
                }
                else {
                    insertHashMap(temp[i], (list.size() - 1));
                }
            }catch(ArrayIndexOutOfBoundsException a){
                break;
            } 
        }        
    
    }
    

    
    public void sell(JTextArea information, String symbol, int quantity, double price){
         

        int counter = 0;
        double temp = 0;
              
        for(Investment I : list){
            
            // Symbol match and quantity less so changes the quantity
            if(I.getsymbol().equals(symbol) == true && I.getquantity() >= quantity){
                        
                temp = I.getquantity();
                I.setquantity(I.getquantity() - quantity);
                I.setbookValue(I.getbookValue()*(I.getquantity()/temp));
                I.setprice(price);
                
                information.append("Investment Sold\n");
                
            }
            // Selling amount is greater than what you have
            else if (I.getquantity() < quantity){
                information.append("Sorry but unfortunely you don't have that many Investments to sell\n");
            }
                    
            // If all sold, then stock is deleted
            if(I.getquantity() == 0){
                deletekey(I.getname(), counter);
                list.remove(counter);
                break;
                        
            }
                    
            counter++; 
        }
       
    }
    
    
    public void update(JTextArea information, int index, double price){
        
        double newprice = price;
        
        
            if(newprice == 0 || newprice < 0){
                
                information.append("Please select another price\n");
                
            }
            else{
                
                list.get(index).setprice(newprice);
                
                information.append("Price updated to " + newprice + "\n");
            }
      
    }
    
    
    
    public void getGain(JTextArea totalGain){
        
        double gain1 = 0;
        double tempamount;
        double gain2 = 0;
        double tempamount2;
        double totalgain = 0;
        
        for(Investment I : list){
            
            
            if(I.gettype().equals("Stock")){
                tempamount = (((I.getquantity()*I.getprice()) - 9.99) - I.getbookValue());
                gain1 = gain1 + tempamount;   
            }
            
            if(I.gettype().equals("MutualFund")){
                tempamount2 = (((I.getquantity()*I.getprice()) - 45) - I.getbookValue());
                gain2 = gain2 + tempamount2;    
            }
            
        }
        
        totalgain = gain1 + gain2;
        
        
        totalGain.setText(Double.toString(totalgain));
        
    }
      
    public void search(JTextArea information, String symbol, String word, int low, int high){
        

        for (Investment I : list){
            
           
            if((I.getsymbol().equals(symbol) == false) && ((I.getname().equalsIgnoreCase(word)) == false) && (((low <= I.getprice()) && (high >= I.getprice())) == true)){
                
                information.append(I.toString());
            }
            else if((I.getsymbol().equals(symbol) == false) && ((I.getname().equalsIgnoreCase(word)) == true) && (((low <= I.getprice()) && (high >= I.getprice())) == false)){
                
                information.append(I.toString());
            }
            else if((I.getsymbol().equals(symbol) == false) && ((I.getname().equalsIgnoreCase(word)) == true) && (((low <= I.getprice()) && (high >= I.getprice())) == true)){
                
                information.append(I.toString());
            }
            else if((I.getsymbol().equals(symbol) == true) && ((I.getname().equalsIgnoreCase(word)) == false) && (((low <= I.getprice()) && (high >= I.getprice())) == false)){
                
                information.append(I.toString());
            }
            else if((I.getsymbol().equals(symbol) == true) && ((I.getname().equalsIgnoreCase(word)) == false) && (((low <= I.getprice()) && (high >= I.getprice())) == true)){
                
                information.append(I.toString());
            }
            else if((I.getsymbol().equals(symbol) == true) && ((I.getname().equalsIgnoreCase(word)) == true) && (((low <= I.getprice()) && (high >= I.getprice())) == false)){
                
                information.append(I.toString());
            }
            else if((I.getsymbol().equals(symbol) == true) && ((I.getname().equalsIgnoreCase(word)) == true) && (((low <= I.getprice()) && (high >= I.getprice())) == true)){
                
                information.append(I.toString());
            }
        }
        
        
    }
    

    
    public void FileWriter(){
        
        
        BufferedWriter inscription;
        
        try{
            inscription = new BufferedWriter(new FileWriter("Investments.txt"));
            
            for(int p = 0; p < list.size(); p++){
                if(list.get(p) instanceof Stock){
                    Stock s = (Stock)list.get(p);
                    inscription.write(s.toString());
                }
                else if(list.get(p) instanceof MutualFund){
                    MutualFund m = (MutualFund)list.get(p);
                    inscription.write(m.toString());
                }
            }
            
            inscription.close();
            
        } catch (IOException e){
            System.out.println("Error writing to file\n");
        }
        
    }
    
    
    public void FileReader(){
        
        
        BufferedReader inscription;
        
        try{
            
            inscription = new BufferedReader(new FileReader("Investments.txt"));
            
            String line = null;
            String type = null;
            String symbol = null;
            String name = null;
            double quantity = 0;
            double price = 0;
            double bookValue = 0;
            
            boolean eof = false;
            
            
            while(eof == false)
            {
    
                
                
                line = inscription.readLine();
                
                if (line == null)
                {
                    eof = true;
                    break;
                }
                
                type = line;
                
                line = inscription.readLine();
                
                if (line == null)
                {
                    eof = true;
                    break;
                }
                
                symbol = line;
                
                line = inscription.readLine();
                
                if (line == null)
                {
                    eof = true;
                    break;
                }
                
                name = line;
                
                String[] temp = new String[15];
              
                temp = name.split("\\s+");
                
                
                for(int i = 0; i < 15; i++){
                  try{ 
                    if(temp[i] == null){
                        break;
                    }
                    else {
                        insertHashMap(temp[i], list.size());
                    }
                  }catch(ArrayIndexOutOfBoundsException a){
                        break;
                    } 
                }
                
                line = inscription.readLine();
                
                if (line == null)
                {
                    eof = true;
                    break;
                }
                
                quantity = Double.parseDouble(line);
                
                line = inscription.readLine();
                
                if (line == null)
                {
                    eof = true;
                    break;
                }
                
                price = Double.parseDouble(line);
                
                line = inscription.readLine();
                
                if (line == null)
                {
                    eof = true;
                    break;
                }
                
                bookValue = Double.parseDouble(line);
                  

                if(type.equals("Stock")){
                    list.add(new Stock(type, symbol, name, quantity, price, bookValue));
                }
                
                if(type.equals("MutualFund")){
                    list.add(new MutualFund(type, symbol, name, quantity, price, bookValue)); 
                }
            }

            inscription.close();
            
        } catch (IOException e){
            System.out.println("Error reading to file\n");
        }
        
        
    }
    
    
    
    public void insertHashMap(String key, Integer value){
        
        
        if(map.containsKey(key)){
            map.get(key).add(value);
        }
        else{
            ArrayList<Integer> maplist = new ArrayList<Integer>();
            maplist.add(value);
            
            map.put(key,maplist);
        }
        
    }
    
    public void getHashMap(String key){
        
        String [] temp = key.split(" ");

            for (int i = 0; i < temp.length; i++) {
                if (map.containsKey(temp[i])) {
                    System.out.println(list.get(i));
                }
            }
    }
    
    
    public void deletekey(String key, int counter){
        
        String [] temp = key.split(" ");
        
        for (int i = 0; i < temp.length; i++) {
            if (map.containsKey(temp[i])) {
                
                for (int x = 0; x < map.get(temp[i]).size(); x++) {
                    if (map.get(temp[i]).get(x) == counter){
                        map.get(temp[i]).remove(x);
                    }
                }

            }
        }
               
    }
    
    public void printmap(){
        
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
    }
    
    
    
}
