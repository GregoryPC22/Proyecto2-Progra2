package visual;

import domain.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import threads.SquareThread;
import static threads.SquareThread.play;

/**
 *
 * @author Fabian
 */
public class LandFrame extends JFrame implements ActionListener {

    //variables
    public ArrayList<Square> mySquares;
    public int lanes;
    private int count;
    private JButton buttonInterrupt;
    private JButton buttonBarrier;
    private JButton buttonRevert;
    public static boolean barrierCheck;
    public static boolean revertCheck;

    //constructor
    public LandFrame(ArrayList<Square> mySquares, int lanes) {
        super("Shape");

        this.mySquares = mySquares;
        this.lanes = lanes;
        this.setSize(1300, 750);
        this.setVisible(true);
        
        this.barrierCheck = false;
        this.revertCheck = false;

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

        buttonBarrier = new JButton("Barrier");
        buttonBarrier.addActionListener(this);
        buttonBarrier.setBounds(1000, 240, 100, 50);
        this.add(buttonBarrier);

        buttonRevert = new JButton("Revert");
        buttonRevert.addActionListener(this);
        buttonRevert.setBounds(1110, 240, 100, 50);
        this.add(buttonRevert);

        JTextField tFCarriles = new JTextField();
        tFCarriles.setName("Carriles");
        tFCarriles.setBounds(1000, 310, 100, 50);
        this.add(tFCarriles);

        JButton buttonSimulation = new JButton("Simulation");
        buttonSimulation.setBounds(1110, 310, 100, 50);
        this.add(buttonSimulation);
        
        buttonInterrupt = new JButton("Interrupt");
        buttonInterrupt.addActionListener(this);
        buttonInterrupt.setBounds(1000, 380, 210, 50);
        this.add(buttonInterrupt);
        
        

        this.addMouseListener(new IH());

        count = 0;

    }//LandFrame

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 100;
        //dibuja las lineas
        for (int i = 0; i < lanes; i++) {
            if (i % 2 == 0) {
                g.setColor(new Color(0, 142, 19));
            } else {
                g.setColor(new Color(162, 162, 162));
            }
            g.fillRect(x, 100, 100, 600);
            x += 100;
        }
        
        //dibuja la barrera
        if(barrierCheck){
            g.setColor(Color.magenta);
            g.drawLine(615, 270, 80, 270);
        }
        else{
            g.setColor(Color.black);
            g.drawLine(615, 270, 80, 270);
        }
        
        for (Square mySquare : mySquares) {
            //el azul es el lento
            if (mySquare.identification.equals("Thread-1")) {
                g.setColor(Color.blue);
            }
            //el rojo es el rapido
            if (mySquare.identification.equals("Thread-2")) {
                g.setColor(Color.red);
            }
            //el rojo es el rapido
            if (mySquare.identification.equals("Thread-3")) {
                g.setColor(Color.green);
            }
            //el rojo es el rapido
            if (mySquare.identification.equals("Thread-4")) {
                g.setColor(Color.YELLOW);
            }
            g.fillRect(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(), mySquare.getSizeX(), mySquare.getSizeY());
        } //end for
    }

    public void paintAgain() {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == buttonInterrupt) {
            if (count % 2 == 0) {
                play = false;
            } else {
                play = true;
            }
            count++;
        }
        
        if (ae.getSource() == buttonBarrier) {
            if (barrierCheck==false) {
                barrierCheck=true;
            } else {
                barrierCheck = false;
            }
        }
        
        if (ae.getSource() == buttonRevert) {
            if (revertCheck==false) {
                revertCheck=true;
            } else {
                revertCheck = false;
            }
        }
    }

    //Mouse events obtener coordenadas
    public static class IH implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            System.out.println("x: " + x + ",y:" + y);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }//IH
}
