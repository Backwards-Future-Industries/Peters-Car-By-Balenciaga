package utilities;

import java.awt.*;

public class Vector2D {
    //to make this class we have drawn inspiration from this repository
    //https://gist.github.com/gunvirranu/6816d65c0231981787ebefd3bdb61f98

    private double x;
    private double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}

    public double[] getComponents(){
        return new double[]{this.x,this.y};
    }
    public double getLength(){
        return Math.sqrt(x * x + y * y);
    }

    public double dot(Vector2D otherVector){
        return this.x * otherVector.x + this.y * otherVector.y;
    }

    public static Vector2D pointToVector(Point point){
        return new Vector2D(point.getX(),point.getY());
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
