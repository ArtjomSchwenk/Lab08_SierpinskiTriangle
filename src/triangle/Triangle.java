package triangle;

import resizable.ResizableImage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static resizable.Debug.print;

/**
 * Implement your Sierpinski Triangle here.
 *
 *
 * You only need to change the drawTriangle
 * method!
 *
 *
 * If you want to, you can also adapt the
 * getResizeImage() method to draw a fast
 * preview.
 *
 */
public class Triangle implements ResizableImage {
    int drawTriangle = 0;
    /**
     * change this method to implement the triangle!
     * @param size the outer bounds of the triangle
     * @return an Image containing the Triangle
     */
    private BufferedImage drawTriangle(Dimension size) {
        print("drawTriangle: " + ++drawTriangle + "size: " + size);
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.black);
        int border = 2;
        //gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        //gBuffer.setColor(Color.darkGray);
        //border = 8;
        //gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        //gBuffer.drawString("Triangle goes here", border * 2, border * 4);
        int h = size.height;
        int s = (int) (h * 2 / Math.sqrt(3));
        int x = size.width / 2;
        int y = 0;

        gBuffer.fillPolygon(new int[]{x, x - s / 2, x + s / 2}, new int[]{y, (int) (y + Math.sqrt(3) / 2 * s), (int) (y + Math.sqrt(3) / 2 * s)}, 3);
        gBuffer.setColor(Color.blue);
        return bufferedImage;
    }

    BufferedImage bufferedImage;
    Dimension bufferedImageSize;

    @Override
    public Image getImage(Dimension triangleSize) {
        if (triangleSize.equals(bufferedImageSize))
            return bufferedImage;
        bufferedImage = drawTriangle(triangleSize);
        bufferedImageSize = triangleSize;
        return bufferedImage;
    }
    @Override
    public Image getResizeImage(Dimension size) {
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.pink);
        int border = 2;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        return bufferedImage;
    }
}
