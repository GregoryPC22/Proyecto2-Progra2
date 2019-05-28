/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsandgraphics;

import data.XMLStudentManager;
import visual.LandFrame;
import threads.RepaintLandThread;
import threads.SquareThread;
import domain.Point;
import domain.Figure;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jdom.JDOMException;

public class ThreadsAndGraphics {

    //public static ArrayList<Figure> squareList = new ArrayList<>();

    public static void main(String[] args) throws JDOMException, IOException {
        XMLStudentManager xml = new XMLStudentManager("./data/Square.xml");
        
        //Speeds
        int speedSlow = 100, speedMedium = 75, speedFast = 50, speedUltraFast = 25;
        
        //create the domain squares
        Figure figure1 = new Figure("Thread-1", new Point(125, 600), 50, 75, 75);
        Figure figure2 = new Figure("Thread-2", new Point(125, 700), 50, 75, 50);
        Figure figure3 = new Figure("Thread-3", new Point(325, 700), 50, 125, 100);
        Figure figure4 = new Figure("Thread-4", new Point(425, 600), 50, 75, 25);
        Figure figure5 = new Figure("Thread-5", new Point(325, 600), 50, 75, 25);

        Figure[] squares = xml.getAllSquares();

        if (squares.length == 0) {
            xml.insertSquare(figure1);
            xml.insertSquare(figure2);
            xml.insertSquare(figure3);
            xml.insertSquare(figure4);
            xml.insertSquare(figure5);
        }
        
        ArrayList<Figure> squareList = new ArrayList<>();

        squares = xml.getAllSquares();

        for (int i = 0; i < squares.length; i++) {
            squareList.add(squares[i]);
        }

        //create the new frame and send the square list
        LandFrame myLand = new LandFrame(squareList, 5);
        myLand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //thread for repainting
        RepaintLandThread repaintThread = new RepaintLandThread(myLand, 1);
        repaintThread.start();
        
        //thread for controlling the movement of the squares
        SquareThread squareThread1 = new SquareThread(squares[0], speedMedium, squareList);
        SquareThread squareThread2 = new SquareThread(squares[1], speedFast, squareList);
        SquareThread squareThread3 = new SquareThread(squares[2], speedSlow, squareList);
        SquareThread squareThread4 = new SquareThread(squares[3], speedUltraFast, squareList);
        SquareThread squareThread5 = new SquareThread(squares[4], speedUltraFast, squareList);

        squareThread1.start();
        squareThread2.start();
        squareThread3.start();
        squareThread4.start();
        squareThread5.start();
    }//end main

}
