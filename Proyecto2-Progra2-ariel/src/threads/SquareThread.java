package threads;

import domain.Point;
import domain.Square;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static visual.LandFrame.barrierCheck;
import static visual.LandFrame.revertCheck;

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
                            if(revertCheck==false){
                                if ((squareList.get(j).getPointPosition().getX()) == (mySquare.getPointPosition().getX())
                                        && (squareList.get(j).getPointPosition().getY()) == (mySquare.getPointPosition().getY() - 60)) //comprueba posiciones
                                {
                                    checkPos = true; //si cambia a true hay alguien en esa posicion
                                }
                            }else{ //comprueba cuando reverse esta activo
                                if ((squareList.get(j).getPointPosition().getX()) == (mySquare.getPointPosition().getX())
                                    && (squareList.get(j).getPointPosition().getY()) == (mySquare.getPointPosition().getY() + 60)) //comprueba posiciones en reversa
                                {
                                checkPos = true; //si cambia a true hay alguien en esa posicion
                                }
                            }//else if revertCheck
                        }//if comprobacion de posicion entre figuras
                    }//for
                    
                    if(barrierCheck == true){
                        if(revertCheck==true){
                            if(mySquare.getPointPosition().getY()+50==270)
                                checkPos = true;
                        }//comprueba cuando la figura va en reversa y toca la barrera
                        else if(mySquare.getPointPosition().getY()==270)
                            checkPos = true; //si cambia es porque choca contra la barrera
                    }//comprueba estado de la barrera
                    
                    if (checkPos == true) { //duerme e imprime en el mismo lugar
                        sleep(delayTime * 4);
                        mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY()));
                    }else if(revertCheck==true){ //si revert es true lo dibuja al contrario
                        if(i!=700){//diferente al punto de partida
                            mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY() + 1));
                            i++;
                        }else //si la figura esta en la posicion inicial la deja ahi hasta que revert se desactive
                            mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY()));
                    }else{ //imprime en la siguiente posicion y avanza el ciclo
                        mySquare.setPointPosition(new Point(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY() - 1));
                        i--;
                    }
                    
                } catch (InterruptedException ex) {
                    ex.getMessage();
                }
            } else {
                interrupt();
            }//if/else interrupt
        }//while
    }//end run
}
