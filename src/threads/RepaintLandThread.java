/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import visual.LandFrame;

/**
 *
 * @author root
 */
public class RepaintLandThread extends Thread{
    
    //variables
    private LandFrame landFrame;
    private int paintTime;
    
    public RepaintLandThread(LandFrame landFrame, int paintTime){
        this.landFrame = landFrame;
        this.paintTime = paintTime;
    }
    
    @Override
    public void run(){
        while(true){
            
            try {
                //sleep until next repaint
                this.sleep(paintTime);
                landFrame.paintAgain();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(RepaintLandThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }//end while
    }//end run
}
