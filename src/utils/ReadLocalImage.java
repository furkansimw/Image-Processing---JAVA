package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadLocalImage {

    public BufferedImage read(String imgName) {
        String currentPath = System.getProperty("user.dir");
        String path = currentPath + "/" + imgName;

        try {
            BufferedImage image = ImageIO.read(new File(path));
            if (image != null) {
                return image;
            } else {
                throw new Error("Failed to read the image.");
            }
        } catch (IOException e) {
            throw new Error("Failed to read the image.");
        }
    }

}
