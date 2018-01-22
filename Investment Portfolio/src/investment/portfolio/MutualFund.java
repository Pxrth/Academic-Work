/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investment.portfolio;

/**
 *
 * @author parth
 */
public class MutualFund extends Investment {   
    
    //Constructor
    public MutualFund(String type, String symbol, String name, double quantity, double price, double bookValue) {
        
        super(type, symbol, name, quantity, price, bookValue);
        
    }
    
    
    @Override
    public String toString() {
        return gettype() + "\n" + getsymbol() + "\n" + getname() + "\n" + getquantity() + "\n" + getprice() + "\n" + getbookValue() + "\n";
    }
}
