/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Fabian
 */
public class Figure {
    public String identification;
    private Point pointPosition;
    private int sizeX;
    private int sizeY;
    private int speed;
    private int imgNum;
    private BufferedImage image;
    private ArrayList<BufferedImage > sprite;

    public Figure(String identification, Point pointPosition, int sizeX, int sizeY, int speed/*, int imgNum*/) {
        this.identification = identification;
        this.pointPosition = pointPosition;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.speed = speed;
        this.sprite = new ArrayList<>();
    }

    public Figure() {
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getImgNum() {
        return imgNum;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public ArrayList<BufferedImage> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<BufferedImage> sprite) {
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return "Figure{" + "identification=" + identification + ", pointPosition=" + pointPosition + ", sizeX=" + sizeX + ", sizeY=" + sizeY + ", speed=" + speed + '}';
    }
}
