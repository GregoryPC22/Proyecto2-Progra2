
package visual;

import domain.Square;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Fabian
 */
public class LandFrame extends JFrame {

    //variables
    public ArrayList<Square> mySquares;
    public int lanes;

    //constructor
    public LandFrame(ArrayList<Square> mySquares, int lanes) {
        super("Shape");

        this.mySquares = mySquares;
        this.lanes = lanes;
        this.setSize(1300, 750);
        this.setVisible(true);
        
        JTextField tFSpeed = new JTextField();
        tFSpeed.setBounds(1000, 100, 100, 50);
        this.add(tFSpeed);      
        
        JTextField tFValue = new JTextField();
        tFValue.setName("Value");
        tFValue.setBounds(1110, 100, 100, 50);
        this.add(tFValue);
        
        JButton buttonCreate = new JButton("Create");
        buttonCreate.setBounds(1000, 170, 210, 50);
        this.add(buttonCreate);
        
        JButton buttonBarrier = new JButton("Barrier");
        buttonBarrier.setBounds(1000, 240, 100, 50);
        this.add(buttonBarrier);
        
        JButton buttonRevert = new JButton("Revert");
        buttonRevert.setBounds(1110, 240, 100, 50);
        this.add(buttonRevert); 
        
        JTextField tFCarriles = new JTextField();
        tFCarriles.setName("Carriles");
        tFCarriles.setBounds(1000, 310, 100, 50);
        this.add(tFCarriles);
        
        JButton buttonSimulation = new JButton("Simulation");
        buttonSimulation.setBounds(1110, 310, 100, 50);
        this.add(buttonSimulation);
        
        JButton buttonInterrupt = new JButton("Interrupt");
        buttonInterrupt.setBounds(1000, 380, 210, 50);
        this.add(buttonInterrupt);
        
       
        
        
        
        
    }//LandFrame


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //iterate over all squares
        for (Square mySquare : mySquares) {
//            g.setColor(Color.blue);
//            g.fillRect(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(), mySquare.getSizeX(), mySquare.getSizeY());
        } //end for
        int x = 100;
        for (int i = 0; i < lanes; i++) {
            if(i%2 == 0)
                g.setColor(new Color(21, 67, 96));
            else
                g.setColor(new Color(172, 15, 120));
            g.fillRect(x, 100, 100, 600);
            x+=100;
        }
    }

    public void paintAgain() {
        repaint();
    }

}
