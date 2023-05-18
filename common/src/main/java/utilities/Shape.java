package utilities;

import java.awt.*;

public class Shape {

    int[] position;
    Point[] rectangle;
    int height;
    int width;
    Type type;
    int[] center;

    public Shape(int width, int height) {
        this(width,height,new int[]{0,0}, Type.UNDEFINED);
    }

    public Shape(int width, int height, int[] position, Type type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
        this.rectangle = new Point[4];
        this.center = new int[]{position[0]+width/2,position[1]+height/2};

        rectangle[0] = new Point(position[0],position[1]);
        rectangle[1] = new Point(position[0], width);
        rectangle[2] = new Point(width,height);
        rectangle[3] = new Point(height,position[1]);

    }


    public Point[] getPositions(double rotation) {
        for(int i = 0; i<rectangle.length; i++){
            double doubleNewX = rectangle[i].x + Math.cos(rotation) * (rectangle[i].x-center[0]) - Math.sin(rotation) * (rectangle[i].y-center[0]);
            double doubleNewY = rectangle[i].y + Math.sin(rotation) * (rectangle[i].x-center[0]) - Math.cos(rotation) * (rectangle[i].y-center[0]);
            int newX = (int) Math.round(doubleNewX);
            int newY = (int) Math.round(doubleNewY);
            rectangle[i].setLocation(new Point(newX,newY));
        }
        return rectangle;
    }


    public void setPosition(int[] position) {
        this.position = position;
        this.center = new int[]{position[0]+width/2,position[1]+height/2};
        rectangle[0] = new Point(position[0],position[1]);
    }


    public void setType(Type type){
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Shapes{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
