package utilities.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class Image {

    private BufferedImage sourceImage;
    private BufferedImage image;
    private AffineTransform transform;
    private AffineTransform scaleTransform;

    private double rotation;

    Image(BufferedImage sourceImage, double[] scale){
        this.sourceImage = sourceImage;
        this.transform = new AffineTransform();
        this.rotation = 0.;
        scale(scale[0],scale[1]);
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
    public void scale(double sx, double sy){
        scaleTransform = AffineTransform.getScaleInstance(sx,sy);
        AffineTransformOp op = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(sourceImage,image);

    }
    public void rotate(double addition, int[] position){
        rotation = rotation + addition;
        transform = AffineTransform.getRotateInstance(rotation,position[0]+image.getWidth()/2,position[1]+image.getHeight()/2);

    }
    public void freshRotate(double radians, int[] position){
        rotation = radians;
        transform = AffineTransform.getRotateInstance(rotation, position[0]+image.getWidth()/2,position[1]+image.getHeight()/2);
    }

}
