package threads;

import domain.Point;
import domain.Figure;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static visual.LandFrame.barrierCheck;
import static visual.LandFrame.revertCheck;

public class SquareThread extends Thread {

    //variables
    private Figure myFigure;
    private int delayTime;
    public static boolean play = true;
    public static int xmlInit = 0;
    private ArrayList<Figure> figureList;
    
    public SquareThread(Figure myFigure, int delayTime, ArrayList<Figure> squareList) {
        super(myFigure.identification);
        this.myFigure = myFigure;
        this.delayTime = delayTime;
        this.figureList = squareList;
    }

    public SquareThread() {
    }

    @Override
    public void run() {
        int i = myFigure.getPointPosition().getY();
        while (i >= 100) {
            if (play == true) {
                try {
                    //bandera que indica si la posicion esta libre para moverse o no
                    Boolean checkPos = false;
                    //sleep for the animation
                    sleep(delayTime);
                    //comprueba la posicion de las otras figuras en la lista
                    for (int j = 0; j < figureList.size(); j++) {
                        if (figureList.get(j) != myFigure) { //la figura se ignora a si misma en la lista
                            if(revertCheck==false){
                                if ((figureList.get(j).getPointPosition().getX()) == (myFigure.getPointPosition().getX())
                                        && (figureList.get(j).getPointPosition().getY()) == (myFigure.getPointPosition().getY() - (figureList.get(j).getSizeY()+10) )) //comprueba posiciones
                                {
                                    checkPos = true; //si cambia a true hay alguien en esa posicion
                                }
                            }else{ //comprueba cuando reverse esta activo
                                if ((figureList.get(j).getPointPosition().getX()) == (myFigure.getPointPosition().getX())
                                    && (figureList.get(j).getPointPosition().getY()) == (myFigure.getPointPosition().getY() + (myFigure.getSizeY()+10) )) //comprueba posiciones en reversa
                                {
                                checkPos = true; //si cambia a true hay alguien en esa posicion
                                }
                            }//else if revertCheck
                        }//if comprobacion de posicion entre figuras
                    }//for
                    
                    if(barrierCheck == true){
                        if(revertCheck==true){
                            if(myFigure.getPointPosition().getY()+(myFigure.getSizeY())==270)
                                checkPos = true;
                        }//comprueba cuando la figura va en reversa y toca la barrera
                        else if(myFigure.getPointPosition().getY()==280)
                            checkPos = true; //si cambia es porque choca contra la barrera
                    }//comprueba estado de la barrera
                    
                    if (checkPos == true) { //duerme e imprime en el mismo lugar
                        sleep(delayTime * 4);
                        myFigure.setPointPosition(new Point(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY()));
                    }else if(revertCheck==true){ //si revert es true lo dibuja al contrario
                        if(i!=700){//diferente al punto de partida
                            myFigure.setPointPosition(new Point(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY() + 1));
                            i++;
                        }else //si la figura esta en la posicion inicial la deja ahi hasta que revert se desactive
                            myFigure.setPointPosition(new Point(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY()));
                    }else{ //imprime en la siguiente posicion y avanza el ciclo
                        myFigure.setPointPosition(new Point(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY() - 1));
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
