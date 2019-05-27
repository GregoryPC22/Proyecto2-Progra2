package threads;

import domain.Point;
import domain.Square;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static threadsandgraphics.Test.squareList;

public class SquareThread extends Thread {

    //variables
    private Square mySquare;
    private int delayTime;
    public static boolean play = true;
    public static int xmlInit = 0;

    public SquareThread(Square mySquare, int delayTime) {
        super(mySquare.identification);
        this.mySquare = mySquare;
        this.delayTime = delayTime;
    }

    public SquareThread() {
    }

    @Override
    public void run() {
        int i = mySquare.getPointPosition().getY();
        if (!squareList.isEmpty()) {
            while (i >= 100) {
                if (play == true) {
                    try {
                        //bandera que indica si la posicion esta libre para moverse o no
                        Boolean checkPos = false;
                        //sleep for the animation
                        sleep(delayTime);
                        //comprueba la posicion de las otras figuras en la lista
                        for (int j = 0; j < squareList.size(); j++) {
                            if (squareList.get(j) != mySquare) { //la figura se ignora a si misma en la lista
                                if ((squareList.get(j).getPointPosition().getX()) == (mySquare.getPointPosition().getX())
                                        && (squareList.get(j).getPointPosition().getY()) == (mySquare.getPointPosition().getY() - 60)) //comprueba posiciones
                                {
                                    checkPos = true; //si cambia a true hay alguien en esa posicion
                                }
                            }
                        }
                        if (checkPos == true) { //duerme e imprime en el mismo lugar
                            sleep(delayTime * 4);
                            mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY()));
                        } else { //imprime en la siguiente posicion y avanza el ciclo
                            mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY() - 1));
                            i--;
                        }
                    } catch (InterruptedException ex) {
                        ex.getMessage();
                    }
                } else {
                    interrupt();
                }
            }
            squareList.remove(mySquare);
        }
    }//end run

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

}
