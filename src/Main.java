
import utils.Colorized;
import utils.Crop;
import utils.ReadLocalImage;
import utils.Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {

    private static BufferedImage image;
    static Utils utils;

    static ArrayList<Colorized> colors = new ArrayList<Colorized>() {
        {
            add(new Colorized("red", new Color(255, 0, 0)));
            add(new Colorized("blue", new Color(0, 0, 255)));
            add(new Colorized("white", new Color(255, 255, 255)));
            add(new Colorized("pink", new Color(255, 192, 203)));
            add(new Colorized("orange", new Color(255, 165, 0)));
        }
    };
    static int[] radius = { 25, 50, 100, 200, 250, 300, 500, 1000, 2000, 5000 };

    static ArrayList<Crop> cropList = new ArrayList<Crop>() {
        {
            add(new Crop(0, 0, 300, 300));
            add(new Crop(150, 150, 300, 500));
            add(new Crop(200, 200, 200, 200));
            add(new Crop(0, 0, 600, 600));
        }
    };

    public static void main(String[] args) {
        ReadLocalImage readLocalImage = new ReadLocalImage();
        image = readLocalImage.read("test.png");

        utils = new Utils(image);

        utils.getSizes();
        utils.setColor(colors);
        utils.setShape(radius);
        utils.crop(cropList);

        // i you want to delete true write
        utils.deleteImages(false);

    }

}