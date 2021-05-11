/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytexteditor;

//Library imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class MainFrame extends JFrame implements ActionListener {
    
    //variables 
    private JTextArea ta;
    private JTextField pt;
    private File selectedFile;
    JFrame fr;
   
    //constructor
    public MainFrame(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        fr = new JFrame("MainFrame");
        
        createUI();

    }
    
    //create user interface
    private void createUI(){
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        
        
        //Menu items
        JMenuItem openChoice = new JMenuItem("Open");
        JMenuItem newChoice = new JMenuItem("New");
        JMenuItem saveChoice = new JMenuItem("Save"); 
        JMenuItem copyChoice = new JMenuItem("Copy (Save As)");
        JMenuItem exitChoice = new JMenuItem("Exit");
        JMenuItem clearChoice = new JMenuItem("Clear");
        JMenuItem statChoice = new JMenuItem("Statistics");
        
        //adding manu items
        fileMenu.add(newChoice);
        fileMenu.add(openChoice);
        fileMenu.add(saveChoice);
        fileMenu.add(copyChoice);
        fileMenu.add(exitChoice);
        editMenu.add(clearChoice);
        editMenu.add(statChoice);
        
        //adding the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);
        
        
        //Creating the text area of the editor
        ta = new JTextArea();
        this.add(ta);
    
        //creating the text field that displays the file's path
        JPanel pathPanel = new JPanel();
        pathPanel.setLayout(new FlowLayout());
        pt = new JTextField();        
        pathPanel.add(pt);
        pt.setEnabled(false);
        pt.setDisabledTextColor(Color.black);
        this.add(pt, BorderLayout.NORTH);
        
       
        //adding actionListener for every button of the menu
        saveChoice.addActionListener(this);
        openChoice.addActionListener(this);
        clearChoice.addActionListener(this);
        newChoice.addActionListener(this);
        copyChoice.addActionListener(this);
        statChoice.addActionListener(this);
        exitChoice.addActionListener(this);
        
        
    }
        
    //main actionListener for all the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String acn = e.getActionCommand();
        
        //Save functionality
        if(acn.equals("Save")){

            JFileChooser jf = new JFileChooser("f:");

            int r = jf.showSaveDialog(ta);

            if(r == JFileChooser.APPROVE_OPTION){
                
               File fi = new File(jf.getSelectedFile().getAbsolutePath());
                if(fi.exists()){
                        JOptionPane.showConfirmDialog(fr, "File excists! Do you want to overwrite it?");

                        try { 
                            // Create a file writer 
                            FileWriter wr = new FileWriter(fi, false); 

                            // Write
                            try ( // Create buffered writer to write
                                    BufferedWriter w = new BufferedWriter(wr)) {
                                // Write
                                w.write(ta.getText());
                                
                                w.flush();
                            } 
                        } catch (IOException evt) { 
                            JOptionPane.showMessageDialog(fr, evt.getMessage()); 
                        } 

                }else{

                     try { 
                        // Create a file writer 
                        FileWriter wr = new FileWriter(fi, false); 

                         // Write
                         try ( // Create buffered writer to write
                                 BufferedWriter w = new BufferedWriter(wr)) {
                             // Write
                             w.write(ta.getText());
                             
                             w.flush();
                         } 
                    } catch (IOException evt) { 
                        JOptionPane.showMessageDialog(fr, evt.getMessage()); 
                    } 
                } 
            } 

        } else if(acn.equals("Open")) { //Open functionality
            
            JFileChooser jf = new JFileChooser("f:"); //Choosing file from disk 
           
            int r = jf.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(jf.getSelectedFile().getAbsolutePath());
                try { 
                    
                    //Getting absolyte path of the opened file in order to display it in the JTextField
                    selectedFile = jf.getSelectedFile();
                    File f= selectedFile;
                    String path= f.getAbsolutePath();
                    pt.setText(path);
                    
                    // String 
                    String s1 = "", sl = ""; 

                    // File reader 
                    FileReader fReader = new FileReader(fi); 

                    // Buffered reader 
                    BufferedReader br = new BufferedReader(fReader); 

                    // Initilize sl 
                    sl = br.readLine(); 

                    // Take the input from the file 
                    while ((s1 = br.readLine()) != null) { 
                        sl = sl + "\n" + s1; 
                    } 

                    // Set the text 
                    ta.setText(sl); 
                } 
                catch (IOException evt) { 
                    JOptionPane.showMessageDialog(fr, evt.getMessage()); 
                } 
            }
        } else if(acn.equals("Clear")){ //Clear functionality
            ta.setText("");
        } else if(acn.equals("New")){   //New functionality
            
            //closing the open file and creating a new one
            dispose();
            MainFrame newFrame = new MainFrame();
            newFrame.setTitle("Editor");
            newFrame.setLocation(550, 150);
            newFrame.setSize(800, 800);
            newFrame.setVisible(true);
            newFrame.setResizable(true);
            
        } else if(acn.equals("Copy (Save As)")){    //SaveAs functionality
            
            
            
            JFileChooser jf = new JFileChooser("f:");

            int r = jf.showSaveDialog(ta);
            
            if(r == JFileChooser.APPROVE_OPTION){

                File fi = new File(jf.getSelectedFile().getAbsolutePath());
                if(fi.exists()){
                    JOptionPane.showConfirmDialog(fr, "File excists! Do you want to overwrite it?");
                    
                     try { 
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fi, false); 

                        // Write
                        try ( // Create buffered writer to write
                                BufferedWriter w = new BufferedWriter(wr)) {
                            // Write
                            w.write(ta.getText());
                            
                            w.flush();
                        } 
                } 
                catch (IOException evt) { 
                    JOptionPane.showMessageDialog(fr, evt.getMessage()); 
                } 
                    
                }else{
                    
                    try { 
                       // Create a file writer 
                       FileWriter wr = new FileWriter(fi, false); 

                        // Write
                        try ( // Create buffered writer to write
                                BufferedWriter w = new BufferedWriter(wr)) {
                            // Write
                            w.write(ta.getText());
                            
                            w.flush();
                        } 
                    } catch (IOException evt) { 
                       JOptionPane.showMessageDialog(fr, evt.getMessage()); 
                   } 
                } 
            }
        }else if(acn.equals("Statistics")){ //Statistics functionality
            
            //Counting and displaying words, characters, sentences-paragraphs
            int letterCount = ta.getText().length();
            int wordCount = ta.getText().split("\\s").length;
            int wordCountNospace = ta.getText().split("").length;
            int paragraphCount = ta.getText().split("\\n").length;
            JOptionPane.showMessageDialog( null ,"The file has:\n" + letterCount + " characters \n" + wordCount + " word(s) \n" + paragraphCount + (" paragraph(s) \n") 
            + wordCountNospace + " characters without spaces\n" + "File size: " + ta.getText().getBytes().length + "Bytes" , "Statistics" ,JOptionPane.INFORMATION_MESSAGE);
            
        } else if(acn.equals("Exit")){  ////Exit functionality
            int i=JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit the application?");
            if(i==0) System.exit(0);
        }
    }
}


       

