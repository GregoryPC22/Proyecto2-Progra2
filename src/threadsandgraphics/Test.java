/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsandgraphics;

import data.XMLStudentManager;
import domain.Point;
import domain.Square;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import org.jdom.JDOMException;
import threads.RepaintLandThread;
import threads.SquareThread;
import visual.LandFrame;
import static visual.LandFrame.lanes;

public class Test {

    public static ArrayList<Square> squareList = new ArrayList<>();
    public static LandFrame myLand = new LandFrame();
    int ns = 0;

    public void test1() throws JDOMException, IOException {
        XMLStudentManager xml = new XMLStudentManager("./data/Square.xml");

        //create the domain squares
        Square square1 = new Square("Square-0", new Point(125, 850), 50, 50);
        Square square2 = new Square("Square-1", new Point(225, 850), 50, 50);
        Square square3 = new Square("Square-2", new Point(325, 850), 50, 50);
        Square square4 = new Square("Square-3", new Point(425, 850), 50, 50);
        Square square5 = new Square("Square-4", new Point(525, 850), 50, 50);

        Square[] squares = xml.getAllSquares();

        if (squares.length == 0) {
            xml.insertSquare(square1);
            xml.insertSquare(square2);
            xml.insertSquare(square3);
            xml.insertSquare(square4);
            xml.insertSquare(square5);
            squares = xml.getAllSquares();
        }

        for (int i = 0; i < squares.length; i++) {
            squareList.add(squares[i]);
        }
        
        //create the new frame and send the square list
        myLand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for (Square squareList1 : squareList) {
            new SquareThread(squareList1, 8).start();
        }

        //thread for repainting
        RepaintLandThread repaintThread = new RepaintLandThread(100);
        repaintThread.start();

    }//end main

    public void createThreads(int quantity) {
        //thread for controlling the movement of the squares
        int value = 125;
        for (int i = 0; i < quantity; i++) {
//            int random = (int) Math.floor(Math.random()*(lanes-1+1)+1);
//            squareList.add(new Square("Square"+(ns), new Point((random*100+25), 850), 50, 50));
            
            squareList.add(new Square("Square"+(ns), new Point((value), 850), 50, 50));
            ns++;
            value+=100;
        }
    }

    public void startThreads(String velocity) {
        //thread for controlling the movement of the squares
        int vel = 0;
        switch (velocity) {
            case "Lento":
                vel = 20;
                break;
            case "Normal":
                vel = 15;
                break;
            case "Rápido":
                vel = 10;
                break;
            case "Muy rápido":
                vel = 5;
                break;            
        }
        for (Square squareList1 : squareList) {
            if (squareList1.getPointPosition().getY() == 850) {
                new SquareThread(squareList1, vel).start();
            }
        }
    }
}
