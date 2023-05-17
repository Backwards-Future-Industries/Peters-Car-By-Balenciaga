package utilities;

import java.awt.*;

public class Vector2D {
    //to make this class we have drawn inspiration from this repository
    //https://gist.github.com/gunvirranu/6816d65c0231981787ebefd3bdb61f98

    private double x;
    private double y;

    public Vector2D(){
    }

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

    /** Find the dot product of current vector and the other vector
     * @param otherVector the other vector
     * @return the dot product
     */
    public double dot(Vector2D otherVector){
        return this.x * otherVector.x + this.y * otherVector.y;
    }

    /** Due to both Classes simply containing a x and y value
     * this method converts a point to a vector to avoid type problems
     * @param point the point to be converted
     * @return the angle between the two vectors
     */
    public static Vector2D pointToVector(Point point){
        return new Vector2D(point.getX(),point.getY());
    }

    /**
     * Rotates the vector by the given angle around the origin
     * Credit to GamesWithGabe for the rotation method
     * https://github.com/codingminecraft/MarioYoutube/
     * @param radian the angle to rotate by
     * @param origin the origin of the rotation
     * @return the rotated vector
     */
    public Vector2D rotateVector(double radian, Vector2D origin){
        this.x -= origin.getX();
        this.y -= origin.getY();

        double cos = Math.cos(radian);
        double sin = Math.sin(radian);

        double xprime = (this.x * cos) - (this.y * sin);
        double yprime = (this.x * sin) + (this.y * cos);

        xprime += origin.getX();
        yprime += origin.getY();

        return new Vector2D(xprime,yprime);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
