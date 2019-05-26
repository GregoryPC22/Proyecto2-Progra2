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
import domain.Square;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jdom.JDOMException;

public class ThreadsAndGraphics {

    public static ArrayList<Square> squareList = new ArrayList<>();

    public static void main(String[] args) throws JDOMException, IOException {
        XMLStudentManager xml = new XMLStudentManager("./data/Square.xml");
        
        //Speeds
        int speedSlow = 100, speedMedium = 75, speedFast = 50;
        
        //create the domain squares
        Square square1 = new Square("Thread-1", new Point(125, 600), 50, 50);
        Square square2 = new Square("Thread-2", new Point(125, 700), 50, 50);
        Square square3 = new Square("Thread-3", new Point(325, 700), 50, 50);
        Square square4 = new Square("Thread-4", new Point(425, 700), 50, 50);

        Square[] squares = xml.getAllSquares();

        if (squares.length == 0) {
            xml.insertSquare(square1);
            xml.insertSquare(square2);
            xml.insertSquare(square3);
            xml.insertSquare(square4);
        }

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
        SquareThread squareThread4 = new SquareThread(squares[3], speedMedium, squareList);

        squareThread1.start();
        squareThread2.start();
        squareThread3.start();
        squareThread4.start();
    }//end main

}
