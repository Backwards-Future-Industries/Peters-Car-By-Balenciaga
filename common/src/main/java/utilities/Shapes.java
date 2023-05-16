package utilities;

import abstractClasses.Entity;

import java.awt.*;

public class Shapes {

    int[] position;

    Point[] rectangle;
    int height;
    int width;

    Types type;

    public Shapes(int width, int height) {
        this(width,height,new int[]{0,0},Types.UNDEFINED);
    }

    public Shapes(int width, int height, int[] position, Types type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
        this.rectangle = new Point[4];

        rectangle[0] = new Point(position[0],position[1]);
        rectangle[1] = new Point(position[0], width);
        rectangle[2] = new Point(height,position[1]);
        rectangle[3] = new Point(width,height);

    }




    //
    public Point[] getPositions(float rotation) {
        for(int i = 1; i<rectangle.length; i++){
            double doubleNewX = rectangle[i].x + Math.cos(rotation) * (rectangle[i].x-rectangle[0].x) - Math.sin(rotation) * (rectangle[i].y-rectangle[0].y);
            double doubleNewY = rectangle[i].y + Math.sin(rotation) * (rectangle[i].x-rectangle[0].x) - Math.cos(rotation) * (rectangle[i].y-rectangle[0].y);
            int newX = (int) Math.round(doubleNewX);
            int newY = (int) Math.round(doubleNewY);
            rectangle[i].setLocation(new Point(newX,newY));
            }

        return rectangle;
    }


    public void setPosition(int[] position) {
        this.position = position;
    }





}
