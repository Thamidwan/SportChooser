/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Student
 */
public class Simple extends JFrame{
    private JPanel massegePnl,namePnl,surnamePnl,sportPnl,mainPnl;
    
    
    private JLabel nameLbl,surnameLbl,sportLbl;
    
    private JTextField nameTxt,surnameTxt;
    
    private JComboBox sportCombo;
    
     private JMenuBar fileMenu;
    
    private JMenu menu;
    
    private JMenuItem displayItwem,writeItem,exitItem,clearItem;
  
    private JTextArea messageTxt,plainTxt;
    private JScrollPane scroll,scrollPane;
   
    
    public void Sip(){
        setTitle("My App");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        massegePnl = new JPanel(new FlowLayout());
        namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        surnamePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sportPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPnl = new JPanel(new GridLayout(3,1,1,1));
        
        messageTxt = new JTextArea(10,25);
        scroll = new JScrollPane(messageTxt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        plainTxt = new JTextArea(10,25);
        plainTxt.setEditable(false);
        scrollPane = new JScrollPane(plainTxt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        nameLbl = new JLabel("Name :");
        nameTxt = new JTextField(20);
        
        surnameLbl = new JLabel("Surname :");
        surnameTxt = new JTextField(20);
        
        sportLbl = new JLabel("Sport :");
        sportCombo =  new JComboBox();
        sportCombo.addItem("Soccer");
        sportCombo.addItem("Hockey");
         sportCombo.addItem("Rubgy");
        
        fileMenu = new JMenuBar();
        menu = new JMenu("File");
        
        displayItwem = new JMenuItem("Display Details");
        displayItwem.addActionListener(new display());
        writeItem = new JMenuItem("Write Details");
        writeItem.addActionListener(new write());
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new exit());
        clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(new clear());
        
        //ADD 
        menu.add(displayItwem);
        menu.add(writeItem);
         menu.add(exitItem);
         menu.add(clearItem);
        fileMenu.add(menu);
        
        namePnl.add(nameLbl);
        namePnl.add(nameTxt);
        surnamePnl.add(surnameLbl);
        surnamePnl.add(surnameTxt);
        sportPnl.add(sportLbl);
        sportPnl.add(sportCombo);
        
        massegePnl.add(scroll);
        mainPnl.add(namePnl);
        mainPnl.add(surnamePnl);
        mainPnl.add(sportPnl);
        
        
        add(fileMenu,BorderLayout.NORTH);
        add(mainPnl,BorderLayout.CENTER);
        add(massegePnl,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    private class display implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc;
            File myFile;
            int val ;
            BufferedReader br;
            String d, results="";
            fc = new JFileChooser();
            val = fc.showOpenDialog(Simple.this);
            if(val == JFileChooser.APPROVE_OPTION){
                
                try {
                    myFile = fc.getSelectedFile();
                    br = new BufferedReader(new FileReader(myFile));
                    while((d =br.readLine())!=null){
                       String[] lineSplit = d.split(",");
                       Massenger np = new Massenger(lineSplit[0],lineSplit[1],lineSplit[2]);
                       results = results + np.toString()+"\n";
                        
                    }
                    br.close();
                    messageTxt.setText(results);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Simple.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Simple.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    
    private class write implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc;
            BufferedWriter bw;
            File myFile;
            int val;
            
            fc = new JFileChooser();
            val = fc.showSaveDialog(Simple.this);
            
            if(val == JFileChooser.APPROVE_OPTION){
                
                try {
                    myFile = fc.getSelectedFile();
                    bw = new BufferedWriter(new FileWriter(myFile,true));
                    String name = nameTxt.getText();
                    String surname = surnameTxt.getText();
                    String sport = (String)sportCombo.getSelectedItem();
                    Massenger np = new Massenger(name, surname, sport);
                    bw.write(np.toString());
                    bw.newLine();
                    bw.close();
                    JOptionPane.showMessageDialog(null,"Saved Succesfully");
                   
                } catch (IOException ex) {
                    Logger.getLogger(Simple.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private class exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
    }
    private class clear implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            messageTxt.setText("");
            nameTxt.setText("");
            surnameTxt.setText("");
            sportCombo.setSelectedIndex(0);
        }
    }
    
}
