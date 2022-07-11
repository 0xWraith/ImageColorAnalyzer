package com.wraith.imagecoloranalyzer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ImageColorAnalyzer class
 */
public class ImageColorAnalyzer
{
    /**
     * Image
     */
    private BufferedImage image;
    /**
     * Quality of color selection
     */
    private int ALG_QUALITY;
    /**
     * Image pixels RGB data
     */
    private List<int[]> IMG_DATA;
    /**
     * Image color palettes
     */
    private List<List<int[]>> IMG_PALETTES;

    /**
     * Constructor
     * @param path Path to file
     */
    public ImageColorAnalyzer(String path) { this(path, 10); }

    /**
     * Constructor
     * @param path Path to file
     * @param ALG_QUALITY Quality of color selection
     */
    public ImageColorAnalyzer(String path, int ALG_QUALITY)
    {
        try
        {
            this.ALG_QUALITY = ALG_QUALITY;
            IMG_DATA = new ArrayList<>();
            IMG_PALETTES = new ArrayList<>();

            image = ImageIO.read(new File(path));
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Get image pixels RGB data
     */
    public void getImageData()
    {
        if(IMG_DATA.size() != 0)
            return;

        int bitColor, r, g, b;

        int IMG_WIDTH = image.getWidth();
        int IMG_HEIGHT = image.getHeight();

        for(int x = 0; x < IMG_WIDTH; x += ALG_QUALITY)
        {
            for(int y = 0; y < IMG_HEIGHT; y += ALG_QUALITY)
            {
                bitColor = image.getRGB(x, y);

                r = getRedChannel(bitColor);
                g = getGreenChannel(bitColor);
                b = getBlueChannel(bitColor);

                IMG_DATA.add(new int[] {r, g, b});
            }
        }
    }

    /**
     * Get color palettes from BufferedImage
     * @param colors Amout of colors to get
     * @return ArrayList of palettes
     */
    public ArrayList<int[]> getPalette(int colors)
    {
        ArrayList<int[]> result = new ArrayList<>();

        if(IMG_DATA.isEmpty())
            return result;

        medianCut(IMG_DATA, getTwoPow(colors));

        for (List<int[]> palette: IMG_PALETTES)
        {
            if(colors-- == 0)
                return result;

            int r = 0, g = 0, b = 0;
            int size = palette.size();

            for(int[] color : palette)
            {
                r += color[0];
                g += color[1];
                b += color[2];
            }

            r /= size;
            g /= size;
            b /= size;

            result.add(new int[] {r, g, b});
        }

        return result;
    }

    /**
     * Median cut algorithm for color quantization
     * @param palettes Image palettes
     * @param colors Color left
     */
    private void medianCut(List<int[]> palettes, int colors)
    {

        if(colors == 0)
        {
            IMG_PALETTES.add(palettes);
            return;
        }

        int rMin = palettes.get(0)[0], rMax = 0, rRange;
        int gMin = palettes.get(0)[1], gMax = 0, gRange;
        int bMin = palettes.get(0)[2], bMax = 0, bRange;

        for (int[] color: palettes)
        {
            if(color[0] < rMin)
                rMin = color[0];
            else if(color[0] > rMax)
                rMax = color[0];

            if(color[1] < gMin)
                gMin = color[1];
            else if(color[1] > gMax)
                gMax = color[1];

            if(color[2] < bMin)
                bMin = color[2];
            else if(color[2] > bMax)
                bMax = color[2];
        }

        rRange = rMax - rMin;
        gRange = gMax - gMin;
        bRange = bMax - bMin;

        if(rRange >= gRange && rRange >= bRange)
            sortPalette(0, palettes, 0, palettes.size() - 1);

        else if(gRange >= rRange && gRange >= bRange)
            sortPalette(1, palettes, 0, palettes.size() - 1);

        else if(bRange >= rRange && bRange >= gRange)
            sortPalette(2, palettes, 0, palettes.size() - 1);

        int half = (palettes.size() + 1) / 2;


        medianCut(palettes.subList(0, half), colors - 1);
        medianCut(palettes.subList(half, palettes.size()), colors - 1);

    }

    /**
     * Custom QuickSort algorithm for sorting palettes
     * @param sortIDX Index to sort by(0 - R channel, 1 - B channel, 2 - G channel)
     * @param palettes Palettes to sort
     * @param low Start index
     * @param high End index
     */
    private void sortPalette(int sortIDX, List<int[]> palettes, int low, int high)
    {
        int i = low, j = high;
        int pivot = palettes.get(low + (high-low)/2)[sortIDX];

        while (i <= j)
        {
            while (palettes.get(i)[sortIDX] < pivot)
                i++;

            while (palettes.get(j)[sortIDX] > pivot)
                j--;

            if (i <= j)
            {
                Collections.swap(palettes, i, j);
                i++;
                j--;
            }
        }

        if (low < j)
            sortPalette(sortIDX, palettes, low, j);

        if (i < high)
            sortPalette(sortIDX, palettes, i, high);

    }

    /**
     * Get power of two
     * @param colors Number that isn't power of 2
     * @return Power of 2 that is greater than @param colors
     */
    private int getTwoPow(int colors)
    {
        int pow = 0;

        while(true)
        {
            if (Math.pow(2, pow) >= colors)
                return pow;

            pow++;
        }
    }

    /**
     * Getter for ALG_QUALITY
     * @return Color selection quality
     */
    public int getQuality() { return ALG_QUALITY; }

    /**
     * Setter for ALG_QUALITY
     * @param ALG_QUALITY Color selection quality
     * @return false/true
     */
    public boolean setQuality(int ALG_QUALITY)
    {
        if(ALG_QUALITY < 1 || ALG_QUALITY > 10)
            return false;

        this.ALG_QUALITY = ALG_QUALITY;

        return true;
    }

    /**
     * Get RED chanell
     * @param bitColor Color in bits
     * @return Red channel
     */
    private int getRedChannel(int bitColor) { return (bitColor & 0xFF0000) >> 16; }
    /**
     * Get GREEN channel
     * @param bitColor Color in bits
     * @return Green channel
     */
    private int getGreenChannel(int bitColor) { return (bitColor & 0xFF00) >> 8; }
    /**
     * Get BLUE channel
     * @param bitColor Color in bits
     * @return Blue channel
     */
    private int getBlueChannel(int bitColor) { return bitColor & 0xFF; }
}
