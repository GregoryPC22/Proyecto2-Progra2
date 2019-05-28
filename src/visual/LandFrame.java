package visual;

import domain.Figure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import static threads.SquareThread.play;

/**
 *
 * @author Fabian
 */
public class LandFrame extends JFrame implements ActionListener {

    //variables
    public ArrayList<Figure> MyFigures;
    public int lanes;
    private int count;
    private JButton buttonInterrupt;
    private JButton buttonBarrier;
    private JButton buttonRevert;
    private JCheckBox checkBoxImages;
    public static boolean barrierCheck;
    public static boolean revertCheck;
    public static boolean imagesCheck;
    private ArrayList <BufferedImage> ultraSpeedList;
    private ArrayList <BufferedImage> fastSpeedList;
    private ArrayList <BufferedImage> mediumSpeedList;
    private ArrayList <BufferedImage> slowSpeedList;
    private ArrayList <BufferedImage> barrierList;
    private int imageMovement;

    //constructor
    public LandFrame(ArrayList<Figure> MyFigures, int lanes) throws IOException {
        super("Shape");
        this.MyFigures = MyFigures;
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
        
        checkBoxImages = new JCheckBox("Images");
        checkBoxImages.addActionListener(this);
        checkBoxImages.setBounds(1000, 450, 210, 50);
        this.add(checkBoxImages);

        this.addMouseListener(new IH());
        
        //Imagenes para objetos con velocidad ultraFast
        ultraSpeedList = new ArrayList<>();
        ultraSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/police1.png")));
        ultraSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/police2.png")));
        ultraSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/police3.png")));
        
        //Imagenes para objetos con velocidad fast
        fastSpeedList = new ArrayList<>();
        fastSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/car1.png")));
        fastSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/car1.png")));
        fastSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/car1.png")));
        
        
        //Imagenes para objetos con velocidad medium
        mediumSpeedList = new ArrayList<>();
        mediumSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/trash.png")));
        mediumSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/trash.png")));
        mediumSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/trash.png")));
        
        //Imagenes para objetos con velocidad slow
        slowSpeedList = new ArrayList<>();
        slowSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/truck1.png")));
        slowSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/truck1.png")));
        slowSpeedList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/truck1.png")));
        
        barrierList = new ArrayList<>();
        barrierList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/laser1.png")));
        barrierList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/laser2.png")));
        barrierList.add(ImageIO.read(getClass().getResourceAsStream("/imgs/laser3.png")));
        
        imageMovement = 0;
        
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
        if(imagesCheck==false){
            if(barrierCheck){
                g.setColor(Color.magenta);
                g.fillRect(80, 270, 540, 10);
            }
            else{
                g.setColor(Color.black);
                g.drawRect(80, 270, 540, 10);
            }
        }//imagesCheck==false
        else{
            if(barrierCheck){
                g.drawImage(barrierList.get(imageMovement), 80, 270, 540, 10, null);
            }
            else{
                g.setColor(Color.black);
                g.drawRect(80, 270, 540, 10);
            }
        }//imagesCheck true
        
        for (Figure myFigure : MyFigures) {
            if(imagesCheck==false){
                if (myFigure.getSpeed()==25) {
                    g.setColor(Color.blue);
                    g.fillOval(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY());
                }
                if (myFigure.getSpeed()==50) {
                    g.setColor(Color.red);
                    g.fillOval(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY());
                }
                if (myFigure.getSpeed()==75) {
                    g.setColor(Color.green);
                    g.fillRect(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY());
                }
                if (myFigure.getSpeed()==100) {
                    g.setColor(Color.yellow);
                    g.fillRect(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY());
                }
            }//if imagesCheck
            else if(imagesCheck==true){
                if (myFigure.getSpeed()==25) {
                    g.drawImage(ultraSpeedList.get(imageMovement), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY(), null);
                }
                if (myFigure.getSpeed()==50) {
                    g.drawImage(fastSpeedList.get(imageMovement), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY(), null);
                }
                if (myFigure.getSpeed()==75) {
                    g.drawImage(mediumSpeedList.get(imageMovement), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY(), null);
                }
                if (myFigure.getSpeed()==100) {
                    g.drawImage(slowSpeedList.get(imageMovement), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), myFigure.getSizeX(), myFigure.getSizeY(), null);
                }
            }// else if imagesCheck=true
            imageMovement++;
            
            if(imageMovement==3)
                imageMovement=0;
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
        
        if (ae.getSource() == checkBoxImages) {
            if (imagesCheck==false) {
                imagesCheck=true;
            } else {
                imagesCheck = false;
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
