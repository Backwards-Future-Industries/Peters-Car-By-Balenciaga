package utilities.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Image {

    private BufferedImage sourceImage;
    private BufferedImage transformedImage;
    private AffineTransform transform;
    private AffineTransformOp transformOp;

    private double rotation;

    Image(BufferedImage sourceImage){
        this.sourceImage = sourceImage;
        this.transform = new AffineTransform();
        this.transformOp = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
        this.rotation = 0.;
    }

    public AffineTransform getTransform() {
        return transform;
    }
    public BufferedImage getSourceImage() {
        return sourceImage;
    }
    public BufferedImage getTransformedImage() {
        return transformedImage;
    }

    public void rotate(double addition){
        rotation = rotation + addition;
        transform.rotate(rotation,sourceImage.getWidth()/2,sourceImage.getHeight()/2);
        transformOp.filter(sourceImage,transformedImage);
    }
    public void freshRotate(double radians){
        rotation = radians;
        transform.rotate(rotation,sourceImage.getWidth()/2,sourceImage.getHeight()/2);
        transformOp.filter(sourceImage,transformedImage);
    }
}
