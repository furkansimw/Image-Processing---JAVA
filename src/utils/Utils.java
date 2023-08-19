package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    private static BufferedImage image = null;

    public void getSizes() {
        int height = Utils.image.getHeight();
        int width = Utils.image.getWidth();
        System.out.println("\nImage" + height + "x" + width + "\n");
    }

    public void setColor(ArrayList<Colorized> colors) {
        BufferedImage originalImage = Utils.image;

        int tintAmount = 40;

        BufferedImage tintedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (Colorized colorized : colors) {
            Color currentColor = colorized.color;
            String name = colorized.name;
            try {
                for (int y = 0; y < originalImage.getHeight(); y++) {
                    for (int x = 0; x < originalImage.getWidth(); x++) {
                        Color originalColor = new Color(originalImage.getRGB(x, y));

                        int red = Math.min(originalColor.getRed() + tintAmount, currentColor.getRed());
                        int green = Math.min(originalColor.getGreen() + tintAmount, currentColor.getGreen());
                        int blue = Math.min(originalColor.getBlue() + tintAmount, currentColor.getBlue());

                        Color newColor = new Color(red, green, blue);
                        tintedImage.setRGB(x, y, newColor.getRGB());
                    }
                }

                File output = new File("images/" + "colorized_" + name + ".png");
                ImageIO.write(tintedImage, "png", output);

                System.out.println("Image colorized. Created new image images/" + name + ".png");
            } catch (Exception e) {
                throw new Error(e.toString());
            }
        }
    }

    public void setShape(int[] radius) {
        System.out.println("\n");

        for (int i = 0; i < radius.length; i++) {
            int _radius = radius[i];
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = output.createGraphics();

            g2.setComposite(AlphaComposite.Src);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, w, h, _radius, _radius));
            g2.setComposite(AlphaComposite.SrcAtop);
            g2.drawImage(image, 0, 0, null);
            g2.dispose();

            String name = "images/shape_" + _radius + ".png";

            try {
                File outputImageFile = new File("./" + name);
                ImageIO.write(output, "png", outputImageFile);
                System.out.println("Image shaped. " + name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void crop(ArrayList<Crop> cropList) {

        System.out.println("\n");

        for (int i = 0; i < cropList.size(); i++) {
            try {
                BufferedImage originalImage = ImageIO.read(new File("test.png"));

                int x = cropList.get(i).x;
                int y = cropList.get(i).y;
                int width = cropList.get(i).width;
                int height = cropList.get(i).height;

                String path = "images/cropped_" + x + "_" + y + "_" + width + "_" + height + ".png";

                BufferedImage croppedImage = originalImage.getSubimage(x, y, width, height);

                File output = new File(path);
                ImageIO.write(croppedImage, "png", output);
                System.out.println("Image cropped and saved successfully. " + path);
            } catch (Exception e) {
                throw new Error(e.toString());
            }
        }
    }

    public void deleteImages(boolean delete) {

        if (!delete) {
            return;
        }
        System.out.println("\n");
        File imagesFolder = new File("./images");
        if (imagesFolder.exists()) {
            File[] files = imagesFolder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            System.out.println("Images folder deleted successfully");
        } else {
            throw new Error("Images folder does not exist");
        }
    }

    public Utils(BufferedImage image) {
        Utils.image = image;
    }
}