/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investment.portfolio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author parth
 */
public class GUI extends JFrame{

    private JTextArea welcomeText;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JPanel updatePanel;
    private JPanel getGainPanel;
    private JPanel searchPanel;
    private JTextArea information;
    
    //private Portfolio list;
    private Portfolio list = new Portfolio();

    public GUI(){
       
        
        list.FileReader();
        
        //setting up the gui
        this.setSize(500, 400);//setting the size of frame
        this.setTitle("Investment Portfolio");//name of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //close when exit
        
        
        buyPanel = buyPanel();
        sellPanel = sellPanel();
        updatePanel = updatePanel();
        getGainPanel = getGainPanel();
        searchPanel = searchPanel();
        
        
        //create compoenet that we need
        JMenuBar commandBar = new JMenuBar();//bar
        JMenu commandMenu = new JMenu("Command");//the button at the bar
        //create the items inside the command button
        JMenuItem buyOption = new JMenuItem("Buy");
        JMenuItem sellOption = new JMenuItem("Sell");
        JMenuItem updateOption = new JMenuItem("Update");
        JMenuItem getGainOption = new JMenuItem("Get Gain");
        JMenuItem searchOption = new JMenuItem("Search");
        JMenuItem quitOption = new JMenuItem("Quit");
        
        commandBar.add(commandMenu);
        commandMenu.add(buyOption);
        commandMenu.add(sellOption);
        commandMenu.add(updateOption);
        commandMenu.add(getGainOption);
        commandMenu.add(searchOption);
        commandMenu.add(quitOption);
        
        buyOption.addActionListener(new openBuyPanelListener());
        sellOption.addActionListener(new openSellPanelListener());
        updateOption.addActionListener(new openUpdatePanelListener());
        getGainOption.addActionListener(new opengetGainPanelListener());
        searchOption.addActionListener(new openSearchPanelListener());
        quitOption.addActionListener(new openQuitPanelListener());
        
        
        welcomeText = new JTextArea("\n\n\n"
                + "   Welcome to Investment Portfolio\n"
                + "\n\n\n"
                + "   Choose a command from the 'Commands' menu to buy or sell\n"
                + "   an investment, update prices for all investments, get gain for\n"
                + "   the portfolio, search for relevent investment, or quit the\n"
                + "   program.\n");
        //adding the component to the window
        
        this.add(commandBar, BorderLayout.NORTH);
        this.add(welcomeText);
       
        this.setVisible(true);
        
    }//end func
   
   
    
    private JPanel buyPanel(){
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(2,1));
        
        
        // Buying Stuff Go Here
        JPanel buyField = new JPanel();
        buyField.setLayout(new GridLayout(1,2));
        
        // Option Field
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(6,2));
        JLabel title = new JLabel("Buying an Investment\n");
        JLabel space = new JLabel(" ");
        JLabel Type = new JLabel("  Type\n");
        JLabel Symbol = new JLabel("  Symbol\n");
        JLabel Name = new JLabel("  Name\n");
        JLabel Quantity = new JLabel("  Quantity\n");
        JLabel Price = new JLabel("  Price\n");
        
        JComboBox typeselect = new JComboBox();
        typeselect.addItem("Stock");
        typeselect.addItem("MutualFund");

        JTextField buysymbol = new JTextField();
        JTextField buyname = new JTextField();
        JTextField buyquantity = new JTextField();
        JTextField buyprice = new JTextField();
       
        
        options.add(title);
        options.add(space);
        options.add(Type);
        options.add(typeselect);
        options.add(Symbol);
        options.add(buysymbol);
        options.add(Name);
        options.add(buyname);
        options.add(Quantity);
        options.add(buyquantity);
        options.add(Price);
        options.add(buyprice);
        
        
        // Buttons Field 
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(2,1));
        JPanel resetPanel = new JPanel();
        JPanel buyPanel = new JPanel();
        
        JButton ResetBuy = new JButton("Reset");
        JButton Buy = new JButton("Buy");
        
        ResetBuy.setPreferredSize(new Dimension(100, 30));
        Buy.setPreferredSize(new Dimension(100, 30));
        
        resetPanel.add(ResetBuy);
        buyPanel.add(Buy);
        
        button.add(resetPanel);
        button.add(buyPanel);
        

        buyField.add(options);
        buyField.add(button);
       
        // Messages Stuff Go Here
        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messagetitle = new JLabel(" Messages\n");
        JTextArea information = new JTextArea("Messages...\n");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
       
        messageField.add(messagetitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);
        
        
        panel.add(buyField);
        panel.add(messageField);
        
        
        //Button Stuff
        
        ResetBuy.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                typeselect.setSelectedIndex(0);
                buysymbol.setText("");
                buyname.setText("");
                buyquantity.setText("");
                buyprice.setText("");
                
            }
        });
        
        
        Buy.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
            
                try{        
                    if((!buysymbol.getText().equals("")) && (!buyname.getText().equals("")) && (!buyquantity.getText().equals("")) && (!(buyprice.getText().equals("")))){

                        if((Integer.parseInt(buyquantity.getText()) > 0 && Double.parseDouble(buyprice.getText()) > 0)){
                            list.buy(information, (String)typeselect.getSelectedItem(), buysymbol.getText(), buyname.getText(), Integer.parseInt(buyquantity.getText()), Double.parseDouble(buyprice.getText()));
                        }
                        else{

                            information.append("Invalid Quantity or Price\n");
                        }
                    }
                    else{
                        information.append("Please fill all fields\n");
                    }

                }catch(Exception e){

                    information.append("Please correct Quantity and Price field\n");
                }
            }
        });
       
        
        return panel;
    }
    
    
    
    private JPanel sellPanel(){
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(2,1));
        
        
        // Selling Stuff Go Here
        JPanel sellField = new JPanel();
        sellField.setLayout(new GridLayout(1,2));
        
        // Option Field
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(4,2));
        JLabel title = new JLabel("Selling an Investment\n");
        JLabel space = new JLabel(" ");
        JLabel Symbol = new JLabel("  Symbol\n");
        JLabel Quantity = new JLabel("  Quantity\n");
        JLabel Price = new JLabel("  Price\n");
        
        JTextField sellsymbol = new JTextField();
        JTextField sellquantity = new JTextField();
        JTextField sellprice = new JTextField();
        
        options.add(title);
        options.add(space);
        options.add(Symbol);
        options.add(sellsymbol);
        options.add(Quantity);
        options.add(sellquantity);
        options.add(Price);
        options.add(sellprice);
        
        
        // Buttons Field 
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(2,1));
        JPanel resetPanel = new JPanel();
        JPanel sellPanel = new JPanel();
        
        JButton ResetSell = new JButton("Reset");
        JButton Sell = new JButton("Sell");
        
        ResetSell.setPreferredSize(new Dimension(100, 30));
        Sell.setPreferredSize(new Dimension(100, 30));
        
        resetPanel.add(ResetSell);
        sellPanel.add(Sell);
        
        button.add(resetPanel);
        button.add(sellPanel);
                
        sellField.add(options);
        sellField.add(button);
       
        // Messages Stuff Go Here
        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messagetitle = new JLabel(" Messages\n");
        JTextArea information = new JTextArea("Messages...\n");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        messageField.add(messagetitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);
        
        
        panel.add(sellField);
        panel.add(messageField);

        

        //Button Stuff
        
        ResetSell.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                sellsymbol.setText("");
                sellquantity.setText("");
                sellprice.setText("");
                
            }
        });
        
        
        Sell.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try{
                    if((!sellsymbol.getText().equals("")) && (!sellquantity.getText().equals("")) && (!(sellprice.getText().equals("")))){

                        if((Integer.parseInt(sellquantity.getText()) > 0 && Double.parseDouble(sellprice.getText()) > 0)){
                            list.sell(information, sellsymbol.getText(), Integer.parseInt(sellquantity.getText()), Double.parseDouble(sellprice.getText()));
                        }
                        else{

                            information.append("Invalid Quantity or Price\n");
                        }
                    }
                    else{
                        information.append("Please fill all fields\n");
                    }  
                }catch(Exception e){
                    information.append("Please correct Price and Quantity field\n");
                }
            }
        });
        
        
       
        
        return panel;
    }
    
    private int index;
    private JPanel updatePanel(){
        
        
        index = 0;
                  
                
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(2,1));
        
        
        // Updating Stuff Go Here
        JPanel updateField = new JPanel();
        updateField.setLayout(new GridLayout(1,2));
        
        // Option Field
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(4,2));
        JLabel title = new JLabel("Updating Investment\n");
        JLabel space = new JLabel(" ");
        JLabel Symbol = new JLabel("  Symbol\n");
        JLabel Name = new JLabel("  Name\n");
        JLabel Price = new JLabel("  Price\n");
        
        JTextField symbolselect = new JTextField(list.getlist().get(index).getsymbol().toString());
        JTextField nameselect = new JTextField(list.getlist().get(index).getname().toString());
        JTextField priceselect = new JTextField();
        
        symbolselect.setEditable(false);
        nameselect.setEditable(false);

        priceselect.setText(Double.toString(list.getlist().get(index).getprice()));
                    
        options.add(title);
        options.add(space);
        options.add(Symbol);
        options.add(symbolselect);
        options.add(Name);
        options.add(nameselect);
        options.add(Price);
        options.add(priceselect);
        
        
        // Buttons Field 
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(3,1));
        JPanel prevPanel = new JPanel();
        JPanel nextPanel = new JPanel();
        JPanel savePanel = new JPanel();
        
        JButton Prev = new JButton("Prev");
        JButton Next = new JButton("Next");
        JButton Save = new JButton("Save");
        
        Prev.setPreferredSize(new Dimension(100, 30));
        Next.setPreferredSize(new Dimension(100, 30));
        Save.setPreferredSize(new Dimension(100, 30));
        
        prevPanel.add(Prev);
        nextPanel.add(Next);
        savePanel.add(Save);
        
        button.add(prevPanel);
        button.add(nextPanel);
        button.add(savePanel);
        
                
        updateField.add(options);
        updateField.add(button);
       
        // Messages Stuff Go Here
        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messagetitle = new JLabel(" Messages\n");
        JTextArea information = new JTextArea("Messages...\n");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        messageField.add(messagetitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);
        
        
        panel.add(updateField);
        panel.add(messageField);
        
        
        Prev.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                
        
                if(index > 0){
                    
                    index = index - 1;
                    symbolselect.setText(list.getlist().get(index).getsymbol().toString());
                    nameselect.setText(list.getlist().get(index).getname().toString());
                    priceselect.setText(Double.toString(list.getlist().get(index).getprice()));
                    Prev.setEnabled(true);
                    Next.setEnabled(true);
                }
                else if (index == 0){
                    symbolselect.setText(list.getlist().get(index).getsymbol().toString());
                    nameselect.setText(list.getlist().get(index).getname().toString());
                    priceselect.setText(Double.toString(list.getlist().get(index).getprice()));
                    Prev.setEnabled(false);
                    Next.setEnabled(true);
                }
                 
                
            }
        });
                
        Next.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent ae) {
                

                if(index < list.getlist().size()-1){
                    
                    index = index + 1;
                    symbolselect.setText(list.getlist().get(index).getsymbol().toString());
                    nameselect.setText(list.getlist().get(index).getname().toString());
                    priceselect.setText(Double.toString(list.getlist().get(index).getprice()));
                    Prev.setEnabled(true);
                    Next.setEnabled(true);
                }
                else if(index == list.getlist().size()-1){
                    Next.setEnabled(false);
                    Prev.setEnabled(true);
                }
                
            }
        });
                        
        Save.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try{
                    if(Double.parseDouble(priceselect.getText()) > 0){
                        list.update(information, index, Double.parseDouble(priceselect.getText()));
                    }
                    else{   
                        information.append("Invalid price\n");
                    }
                }catch(Exception e){
                    information.append("Please correct price field\n");
                }
            }
        });
        
        
        return panel;
    }
    
    private JTextArea totalGain;
    
    private JPanel getGainPanel(){
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(2,1));
        
        
        // Updating Stuff Go Here
        JPanel getGainField = new JPanel();
        getGainField.setLayout(new GridLayout(1,2));
        JPanel text = new JPanel();
        
        // Option Field
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(2,1));
        JLabel title = new JLabel("Getting Total Gain\n");
        JLabel space = new JLabel(" ");
        JLabel gain = new JLabel("  Total Gain\n");
        
        totalGain = new JTextArea();
        totalGain.setEditable(false);
        totalGain.setPreferredSize(new Dimension(100, 30));
                    
        text.add(totalGain);
       
        
        options.add(title);
        options.add(space);
        options.add(gain);
        options.add(text);
       
        JPanel nothing = new JPanel();
        JLabel space2 = new JLabel(" ");
        nothing.setLayout(new GridLayout());
        
        nothing.add(space2);
        
        getGainField.add(options);
        getGainField.add(nothing);
        
        // Messages Stuff Go Here
        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messagetitle = new JLabel("Individual Gains\n");
        information = new JTextArea();
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        messageField.add(messagetitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);
        
        
        panel.add(getGainField);
        panel.add(messageField);

        
        return panel;
       
    }
    
    
    private JPanel searchPanel() {
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(2,1));
        
        
        // Search Stuff Go Here
        JPanel searchField = new JPanel();
        searchField.setLayout(new GridLayout(1,2));
        
        // Option Field
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(6,2));
        JLabel title = new JLabel("Search Investment\n");
        JLabel space = new JLabel(" ");
        JLabel Symbol = new JLabel("  Symbol\n");
        JLabel Keyword = new JLabel("  Name Keywords\n");
        JLabel Low = new JLabel("  Low Price\n");
        JLabel High = new JLabel("  High Price\n");
        
        
        JTextField searchsymbol = new JTextField();
        JTextField searchkeyword = new JTextField();
        JTextField searchlow = new JTextField();
        JTextField searchhigh = new JTextField();
        
        options.add(title);
        options.add(space);
        options.add(Symbol);
        options.add(searchsymbol);
        options.add(Keyword);
        options.add(searchkeyword);
        options.add(Low);
        options.add(searchlow);
        options.add(High);
        options.add(searchhigh);
        
        
        // Buttons Field 
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(2,1));
        JPanel resetPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        
        JButton ResetSearch = new JButton("Reset");
        JButton Search = new JButton("Search");
        
        ResetSearch.setPreferredSize(new Dimension(100, 30));
        Search.setPreferredSize(new Dimension(100, 30));
        
        resetPanel.add(ResetSearch);
        searchPanel.add(Search);
        
        button.add(resetPanel);
        button.add(searchPanel);
                
        searchField.add(options);
        searchField.add(button);
       
        // Messages Stuff Go Here
        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messagetitle = new JLabel(" Search Results\n");
        JTextArea information = new JTextArea("Results...\n");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        messageField.add(messagetitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);
        
        
        panel.add(searchField);
        panel.add(messageField);

        
        //Button Stuff
        
        ResetSearch.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                searchsymbol.setText("");
                searchkeyword.setText("");
                searchlow.setText("");
                searchhigh.setText("");
                
            }
        });
        
        Search.addActionListener(new ActionListener(){
        

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try{
                    if(searchsymbol.getText().equals("") && searchkeyword.getText().equals("") && searchlow.getText().equals("") && searchhigh.getText().equals("")){

                        information.append("No Investment Found\n");
                    }

                    if(searchlow.getText().equals("")){
                        searchlow.setText("0");
                    }
                    if(searchhigh.getText().equals("")){
                        searchhigh.setText("0");
                    }

                    list.search(information, searchsymbol.getText(), searchkeyword.getText(), Integer.parseInt(searchlow.getText()), Integer.parseInt(searchhigh.getText()));
                }catch(Exception e){
                    information.append("Please correct the fields\n");
                }
            }
        });
        
        
        
        return panel;

    }
    
    private class openBuyPanelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //any action that calls this will run this code
            
            
            welcomeText.setVisible(false);
            sellPanel.setVisible(false);
            updatePanel.setVisible(false);
            getGainPanel.setVisible(false);
            searchPanel.setVisible(false);
            
            add(buyPanel);
            buyPanel.setVisible(true);
            
           
        }
        
    }
    
    
    private class openSellPanelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //any action that calls this will run this code
            
            welcomeText.setVisible(false);
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            getGainPanel.setVisible(false);
            searchPanel.setVisible(false);
            
            add(sellPanel);
            sellPanel.setVisible(true);
           
        }
        
    }
    
    
    private class openUpdatePanelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //any action that calls this will run this code
            

            welcomeText.setVisible(false);
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            getGainPanel.setVisible(false);
            searchPanel.setVisible(false);
            
            add(updatePanel);
            updatePanel.setVisible(true);
           
        }
        
    }
    
    private class opengetGainPanelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //any action that calls this will run this code

            welcomeText.setVisible(false);
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            sellPanel.setVisible(false);
            searchPanel.setVisible(false);
            
            add(getGainPanel);
            getGainPanel.setVisible(true);
            
           
            list.getGain(totalGain);
            
            
        }
    }
  
    private class openSearchPanelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //any action that calls this will run this code
            

            welcomeText.setVisible(false);
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            getGainPanel.setVisible(false);
            sellPanel.setVisible(false);
            
            add(searchPanel);
            searchPanel.setVisible(true);
           
        }
        
    }
        
    
    private class openQuitPanelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //any action that calls this will run this code
            
            list.FileWriter();
            exit(0);
        }
        
    }
    
        
}//end class
