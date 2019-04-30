package com.example.image.util;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {


    public static void thumbnails(File fromPic, File toPic) throws IOException {
        Thumbnails.of(fromPic).size(80, 80).toFile(toPic);
    }


    /**
     * 读取一张图片的RGB值
     *
     * @throws Exception
     */
    public static int[] getImagePixel(File file) {
        int[] rgb = new int[3];
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                    int pixel = bi.getRGB(i, j);
                    rgb[0] += (pixel & 0xff0000) >> 16;
                    rgb[1] += (pixel & 0xff00) >> 8;
                    rgb[2] += (pixel & 0xff);
//                    System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
//                            + rgb[1] + "," + rgb[2] + ")");
                }
            }
            int size = width * height;
            rgb[0] = rgb[0] / size;
            rgb[1] = rgb[1] / size;
            rgb[2] = rgb[2] / size;
            return rgb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回屏幕色彩值
     *
     * @param x
     * @param y
     * @return
     * @throws AWTException
     */
    public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        rb = new Robot();
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
        System.out.println(di.width);
        System.out.println(di.height);
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        BufferedImage bi = rb.createScreenCapture(rec);
        int pixelColor = bi.getRGB(x, y);

        return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
    }

}
