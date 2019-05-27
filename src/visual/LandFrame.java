package visual;

import domain.Square;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static threads.SquareThread.play;
import threadsandgraphics.Test;
import static threadsandgraphics.Test.squareList;

/**
 *
 * @author Fabian
 */
public class LandFrame extends JFrame implements ActionListener {

    public static int lanes = 5;
    private int count;
    Test t = new Test();

    private JComboBox cbVelocity = new JComboBox();
    private JTextField tfQuantity = new JTextField("quantity");
    private JTextField tFleans = new JTextField("Carriles");
    private JButton buttonCreate = new JButton("Create");
    private JTextField tfBarrier = new JTextField("Carriles");
    private JButton buttonBarrier = new JButton("Barrier");
    private JButton buttonSimulation = new JButton("Simulation");
    private JButton buttonRevert = new JButton("Revert");
    private JButton buttonInterrupt = new JButton("Interrupt");
    private JLabel button = new JLabel("");

    //constructor
    public LandFrame() {
        super("Shape");

        this.setSize(1820, 980);
        this.setLocation(50, 50);
        this.setVisible(true);

        cbVelocity.setBounds(1400, 100, 75, 50);
        cbVelocity.addItem("Lento");
        cbVelocity.addItem("Normal");
        cbVelocity.addItem("Rápido");
        cbVelocity.addItem("Muy rápido");
        this.add(cbVelocity);

        tfQuantity.setBounds(1480, 100, 75, 50);
        this.add(tfQuantity);

        tFleans.setBounds(1560, 100, 75, 50);
        this.add(tFleans);

        buttonCreate.addActionListener(this);
        buttonCreate.setBounds(1400, 170, 235, 50);
        this.add(buttonCreate);

        tfBarrier.setBounds(1400, 240, 115, 50);
        this.add(tfBarrier);

        buttonBarrier.addActionListener(this);
        buttonBarrier.setBounds(1520, 240, 115, 50);
        this.add(buttonBarrier);

        buttonSimulation.addActionListener(this);
        buttonSimulation.setBounds(1400, 300, 115, 50);
        this.add(buttonSimulation);

        buttonRevert.addActionListener(this);
        buttonRevert.setBounds(1520, 300, 115, 50);
        this.add(buttonRevert);

        buttonInterrupt.addActionListener(this);
        buttonInterrupt.setBounds(1400, 370, 235, 50);
        this.add(buttonInterrupt);

        button.setBounds(1400, 440, 235, 50);
        this.add(button);

        this.addMouseListener(new IH());

        count = 0;

    }//LandFrame

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 100;
        for (int i = 0; i < lanes; i++) {
            if (i % 2 == 0) {
                g.setColor(new Color(0, 142, 19));
            } else {
                g.setColor(new Color(162, 162, 162));
            }
            g.fillRect(x, 100, 100, 800);
            x += 100;
        }
        for (Square mySquare : squareList) {
            //el azul es el lento
            if (mySquare.identification.equals("Square")) {
                g.setColor(Color.blue);
            }
            //el rojo es el rapido
            if (mySquare.identification.equals("Thread-2")) {
                g.setColor(Color.red);
            }
            //el rojo es el rapido
            if (mySquare.identification.equals("Thread-3")) {
                g.setColor(Color.YELLOW);
            }
            //el rojo es el rapido
            if (mySquare.identification.equals("Thread-4")) {
                g.setColor(Color.black);
            }
            else
                g.setColor(Color.red);
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

        }

        if (ae.getSource() == buttonCreate) {
            if (!tFleans.getText().equals("Carriles")) {
                int variable = Integer.parseInt(tFleans.getText());
                if (variable >= 5 && variable < 13) {
                    lanes = variable;
                }
            }
            if (!tfQuantity.getText().equals("quantity")) {
                t.createThreads(Integer.parseInt(tfQuantity.getText()));
                t.startThreads((String) cbVelocity.getSelectedItem());
            }
        }

        if (ae.getSource() == buttonRevert) {

        }

        if (ae.getSource() == buttonSimulation) {

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
