/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import domain.Point;
import domain.Square;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
public class SquareThread extends Thread {

    //variables
    private Square mySquare;
    private int delayTime;

    public SquareThread(Square mySquare, int delayTime) {
        super(mySquare.identification);
        this.mySquare = mySquare;
        this.delayTime = delayTime;
    }

    public SquareThread() {
    }

    @Override
    public void run() {
        for (int i = 0; mySquare.getSizeX() <= 600; i++) {

            try {
                //sleep for the animation
                sleep(delayTime);

                //move the object

                mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY()));
                mySquare.setSizeX(mySquare.getSizeX()+1);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(SquareThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//end run

}
