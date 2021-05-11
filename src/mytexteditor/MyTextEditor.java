/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytexteditor;

/**
 *
 * @author User
 */
public class MyTextEditor {

    /**
     * @param args the command line arguments
     */
    //main Function
    public static void main(String[] args) {
        
        //constructing the frame of the UI
        MainFrame frame = new MainFrame();
        frame.setTitle("Editor");
        frame.setLocation(550, 150);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setResizable(true);
    }
    
}
