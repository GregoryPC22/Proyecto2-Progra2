package threads;

import domain.Point;
import domain.Square;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SquareThread extends Thread {

    //variables
    private Square mySquare;
    private int delayTime;
    public static boolean play = true;
    public static int xmlInit = 0;
    private ArrayList<Square> squareList;

    public SquareThread(Square mySquare, int delayTime, ArrayList<Square> squareList) {
        super(mySquare.identification);
        this.mySquare = mySquare;
        this.delayTime = delayTime;
        this.squareList = squareList;
    }

    public SquareThread() {
    }

    @Override
    public void run() {
        int i = mySquare.getPointPosition().getY();
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
                    Logger.getLogger(SquareThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                interrupt();
            }
        }
    }//end run
}
