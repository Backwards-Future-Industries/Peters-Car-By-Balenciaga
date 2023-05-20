package utilities.image;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Image {

    private BufferedImage sourceImage;
    private BufferedImage image;
    private AffineTransform transform;
    private double rotation;
    Point2D[] sourceRectangle;
    Point2D[] transformedRectangle;

    Image(BufferedImage sourceImage, double[] scale) {
        this.sourceImage = sourceImage;
        this.transform = new AffineTransform();
        this.rotation = 0.;
        scale(scale[0], scale[1]);
        this.sourceRectangle = new Point[4];

        sourceRectangle[0] = new Point(0, 0);
        sourceRectangle[1] = new Point(image.getWidth(), 0);
        sourceRectangle[2] = new Point(image.getWidth(), image.getHeight());
        sourceRectangle[3] = new Point(0, image.getHeight());

        this.transformedRectangle = sourceRectangle.clone();

    }

    public AffineTransform getTransform() {
        return transform;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void scale(double sx, double sy) {
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(sx, sy);
        AffineTransformOp op = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(sourceImage, image);

    }

    public void rotate(double addition, int[] position) {
        rotation = rotation + addition;
        transform = AffineTransform.getRotateInstance(rotation, position[0] + image.getWidth() / 2, position[1] + image.getHeight() / 2);
        transform.transform(sourceRectangle, 0, transformedRectangle, 0, 4);
    }

    public void freshRotate(double radians, Point position) {
        rotation = radians;
        transform = AffineTransform.getRotateInstance(rotation, position.x + image.getWidth() / 2, position.y + image.getHeight() / 2);
        transform.transform(sourceRectangle, 0, transformedRectangle, 0, 4);
    }

    public double getRotation() {
        return rotation;
    }

    /**
     * Redo the source rectangle to the given x and y in point [0]
     */
    public void redoSourceRectangle(int x, int y) {

        sourceRectangle[0] = new Point(x, y);
        sourceRectangle[1] = new Point(x + image.getWidth(), y);
        sourceRectangle[2] = new Point(x + image.getWidth(), y + image.getHeight());
        sourceRectangle[3] = new Point(x, y + image.getHeight());

        this.transformedRectangle = sourceRectangle.clone();
    }

    public Point[] updateTransformedRectangle(int x, int y, double radians) {
        this.redoSourceRectangle(x, y);
        rotation = radians;
        transform = AffineTransform.getRotateInstance(rotation, x + (double) image.getWidth() / 2, y + (double) image.getHeight() / 2);
        transform.transform(sourceRectangle, 0, transformedRectangle, 0, 4);
        return (Point[]) transformedRectangle;
    }
}
