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
    private BufferedImage drawTriangle(Dimension size, int levels) {
        print("drawSierpinskiTriangle: " + ++drawTriangle + "size: " + size + " levels: " + levels);
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();

        int h = size.height;
        int s = (int) (h * 2 / Math.sqrt(3));
        int x = size.width / 2;
        int y = 0;

        gBuffer.setColor(Color.black);
        drawSierpinski(gBuffer, x, y, x - s / 2, (int) (y + Math.sqrt(3) / 2 * s), x + s / 2, (int) (y + Math.sqrt(3) / 2 * s), levels, levels);

        return bufferedImage;
    }

    private void drawSierpinski(Graphics2D g, int x1, int y1, int x2, int y2, int x3, int y3, int level, int maxLevel) {
        if (level == 0) {
            return;
        }

        int x12 = (x1 + x2) / 2;
        int y12 = (y1 + y2) / 2;
        int x23 = (x2 + x3) / 2;
        int y23 = (y2 + y3) / 2;
        int x31 = (x3 + x1) / 2;
        int y31 = (y3 + y1) / 2;

        int colorPickerNumberR = (int) (Math.random() * 8);
        int colorPickerNumberG = (int) (Math.random() * 8);
        int colorPickerNumberB = (int) (Math.random() * 8);

        g.setColor(new Color(colorPickerNumberR*32, colorPickerNumberG*32, colorPickerNumberB*32));

        int[] xPoints = {x1, x2, x3, x12, x23, x31};
        int[] yPoints = {y1, y2, y3, y12, y23, y31};

        g.fillPolygon(xPoints, yPoints, 3);
        g.fillPolygon(xPoints, yPoints, 3 + 3);

        if (level > 1) {
            drawSierpinski(g, x1, y1, x12, y12, x31, y31, level - 1, maxLevel);
            drawSierpinski(g, x12, y12, x2, y2, x23, y23, level - 1, maxLevel);
            drawSierpinski(g, x23, y23, x3, y3, x31, y31, level - 1, maxLevel);
        }
    }

    BufferedImage bufferedImage;
    Dimension bufferedImageSize;

    @Override
    public Image getImage(Dimension triangleSize) {
        if (triangleSize.equals(bufferedImageSize))
            return bufferedImage;
        bufferedImage = drawTriangle(triangleSize, 2);
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