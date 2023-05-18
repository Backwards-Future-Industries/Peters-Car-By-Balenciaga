package utilities;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

class Shape {
    Point2D[] sourceRectangle;
    Point2D[] transformedRectangle;
    int height;
    int width;

    public Shape(int width, int height) {
        this(width,height,new int[]{0,0});
    }

    public Shape(int width, int height, int[] position) {
        this.width = width;
        this.height = height;
        this.sourceRectangle = new Point[4];

        sourceRectangle[0] = new Point(position[0],position[1]);
        sourceRectangle[1] = new Point(position[0], width);
        sourceRectangle[2] = new Point(width,height);
        sourceRectangle[3] = new Point(height,position[1]);

        this.transformedRectangle = sourceRectangle.clone();
    }


    void rotate(AffineTransform affineTransform){
        affineTransform.transform(sourceRectangle,0,transformedRectangle,0,4);
    }

    public Point[] getPoints() {
        return (Point[]) transformedRectangle;
    }

    @Override
    public String toString() {
        return "Shapes{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
