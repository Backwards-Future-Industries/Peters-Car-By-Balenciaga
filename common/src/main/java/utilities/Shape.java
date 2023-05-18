package utilities;

import java.awt.*;

public class Shape {

    private int[] position;
    private Point[] rectangle;
    private int height;
    private int width;
    private Type type;
    private int[] center;

    public Shape(int width, int height) {
        this(width, height, new int[]{0, 0}, Type.UNDEFINED);
    }

    public Shape(int width, int height, int[] position, Type type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
        this.rectangle = new Point[4];
        this.center = new int[]{position[0] + width / 2, position[1] + height / 2};

        rectangle[0] = new Point(position[0], position[1]);
        rectangle[1] = new Point(position[0] + width, position[1]);
        rectangle[2] = new Point(position[0], position[1] + height);
        rectangle[3] = new Point(position[0] + width, position[1] + height);
    }


    public Point[] getRectangle() {
        return rectangle;
    }


    public void setPosition(int[] position, double rotation) {
        this.position = position;
        this.center = new int[]{position[0] + width / 2, position[1] + height / 2};
        rectangle[0] = new Point(position[0], position[1]);
        this.rotate(rotation);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public void rotate(double rotation) {
        rectangle[0] = new Point();
        for (int i = 0; i < rectangle.length; i++) {
            double doubleNewX = rectangle[i].x + Math.cos(rotation) * (rectangle[i].x - center[0]) - Math.sin(rotation) * (rectangle[i].y - center[0]);
            double doubleNewY = rectangle[i].y + Math.sin(rotation) * (rectangle[i].x - center[0]) - Math.cos(rotation) * (rectangle[i].y - center[0]);
            int newX = (int) Math.round(doubleNewX);
            int newY = (int) Math.round(doubleNewY);
            rectangle[i].setLocation(new Point(newX, newY));
        }
    }
}
