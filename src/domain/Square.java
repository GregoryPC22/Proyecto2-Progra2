/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Fabian
 */
public class Square {
    public String identification;
    private Point pointPosition;
    private int sizeX;
    private int sizeY;

    public Square(String identification, Point pointPosition, int sizeX, int sizeY) {
        this.identification = identification;
        this.pointPosition = pointPosition;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public Square() {
    }
    public Point getPointPosition() {
        return pointPosition;
    }

    public void setPointPosition(Point pointPosition) {
        this.pointPosition = pointPosition;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    
    
    
    
    
}
