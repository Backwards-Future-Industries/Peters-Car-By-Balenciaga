package utilities.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Image {

    private BufferedImage sourceImage;
    private BufferedImage transformedImage;
    private AffineTransform transform;
    private AffineTransformOp transformOp;

    private double rotation;

    Image(BufferedImage sourceImage){
        this.sourceImage = sourceImage;
        this.transformedImage = copy(this.sourceImage);
        this.transform = new AffineTransform();
        this.transformOp = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
        this.rotation = 0.;
        scale();
    }

    private BufferedImage copy(BufferedImage bufferedImage){
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlpha = cm.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);
        return new BufferedImage(cm, raster, isAlpha,null);
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

    public void scale(){
        transform = AffineTransform.getScaleInstance(0.2,0.2);
        transformOp = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
        transformOp.filter(sourceImage,transformedImage);
    }

    public void rotate(double addition){
        rotation = rotation + addition;
        transform = AffineTransform.getRotateInstance(rotation,sourceImage.getWidth()/2,sourceImage.getHeight()/2);
        transformOp = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
        transformOp.filter(sourceImage,transformedImage);
    }
    public void freshRotate(double radians){
        rotation = radians;
        transform = AffineTransform.getRotateInstance(rotation,sourceImage.getWidth()/2,sourceImage.getHeight()/2);
        transformOp = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
        transformOp.filter(sourceImage,transformedImage);
    }

    public AffineTransformOp getTransformOp() {
        return transformOp;
    }
}
