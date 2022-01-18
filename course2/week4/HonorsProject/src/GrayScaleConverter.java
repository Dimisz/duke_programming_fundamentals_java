import edu.duke.*;

import java.io.File;
import java.io.IOException;

public class GrayScaleConverter {
    public static void main(String[] args) {
        //testGray();

        selectConvertAndSave();
        }

    public static ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) /3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public static void selectConvertAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);

            String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            ImageResource gray = makeGray(inImage);
            gray.setFileName(newName);
            //gray.draw();
            gray.save();
        }
    }

//    public static void doSave(){
//        DirectoryResource dr = new DirectoryResource();
//        for(File f : dr.selectedFiles()){
//            ImageResource image = new ImageResource(f);
//            String fname = image.getFileName();
//            String newName = "copy-" + fname;
//            image.setFileName(newName);
//            image.draw();
//            image.save();
//        }
//    }

    public static void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
}
