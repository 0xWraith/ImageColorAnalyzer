package com.wraith.imagecoloranalyzer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * ImageColorAnalyzer class
 */
public class ImageColorAnalyzer
{
    /**
     * Used for algorithm selection
     */
    enum ANALYZER_ALGORITHM
    {
        ALG_MEDIAN_CUT,
        ALG_K_MEANS
    }

    /**
     * Color data for K-Mean algorithm
     */
    private class CentroidData
    {
        /**
         * R color - X position
         */
        private final int R;
        /**
         * G color - Y position
         */
        private final int G;
        /**
         * B color - Z position
         */
        private final int B;

        /**
         * Color centroid
         */
        private Centroid centroid;

        /**
         * Constructor
         * @param color Color array [R, G, B]
         */
        public CentroidData(int[] color)
        {
            R = color[0];
            G = color[1];
            B = color[2];

            centroid = null;
        }

        /**
         * Getter
         * @return X position (R color)
         */
        public int getR() { return R; }

        /**
         * Getter
         * @return Y position (G color)
         */
        public int getG() { return G; }

        /**
         * Getter
         * @return Z position (B color)
         */
        public int getB() { return B; }

        /**
         * Getter
         * @return Color Centroid
         */
        public Centroid getCentroid() { return centroid; }

        /**
         * Set color centroid
         * @param centroid Centroid
         */
        public void setCentroid(Centroid centroid) { this.centroid = centroid; }
    }

    /**
     * Centroid class
     */
    private class Centroid
    {
        /**
         * X position of centroid
         */
        private int X;

        /**
         * Y position of centroid
         */
        private int Y;

        /**
         * Z position of centroid
         */
        private int Z;


        /**
         * Get distance between centroid and color
         * @param centroidData Color to find distance
         * @return Distance between points
         */
        public double getDistance(CentroidData centroidData)
        {
            return Math.sqrt(
                    (Math.pow(X - centroidData.getR(), 2) +
                            Math.pow(Y - centroidData.getG(), 2) +
                            Math.pow(Z - centroidData.getB(), 2)));

        }

        /**
         * Setter
         * @param X X position
         */
        public void setX(int X) { this.X = X; }

        /**
         * @param Y Y position
         */
        public void setY(int Y) { this.Y = Y; }

        /**
         * @param Z Z Position
         */
        public void setZ(int Z) { this.Z = Z; }
    }

    /**
     * Image
     */
    private BufferedImage IMG;
    /**
     * Quality of color selection
     */
    private int ALG_QUALITY;
    /**
     * Image average color
     */
    private int[] IMG_AVERAGE_COLOR;
    /**
     * Image dominant color
     */
    private int[] IMG_DOMINANT_COLOR;
    /**
     * Image pixels RGB data
     */
    private final ArrayList<int[]> IMG_DATA;
    /**
     * Image color palettes
     */
    private final ArrayList<List<int[]>> IMG_PALETTES;

    /**
     * Constructor
     * @throws IOException Image does not found
     */
    public ImageColorAnalyzer() throws IOException { this(null, 10); }

    /**
     * Constructor
     * @param ALG_QUALITY Quality of color selection
     * @throws IOException Image does not found
     */
    public ImageColorAnalyzer(int ALG_QUALITY) throws IOException { this(null, ALG_QUALITY); }

    /**
     * Constructor
     * @param path Path to file
     * @throws IOException Image does not found
     */
    public ImageColorAnalyzer(String path) throws IOException { this(path, 10); }

    /**
     * Constructor
     * @param path Path to file
     * @param ALG_QUALITY Quality of color selection
     * @throws IOException Image does not found
     */
    public ImageColorAnalyzer(String path, int ALG_QUALITY) throws IOException
    {
        IMG_DATA = new ArrayList<>();
        this.ALG_QUALITY = ALG_QUALITY;
        IMG_PALETTES = new ArrayList<>();

        if(path != null)
            openImage(path);
    }

    /**
     * Used to change image
     * @param path Path to image
     * @throws IOException Thrown when image does not find
     */
    public void changeImage(String path) throws IOException
    {
        IMG = null;
        openImage(path);
        resetImageData();
    }

    /**
     * Get image pixels RGB data
     */
    public void getImageData() throws Exception
    {
        if(IMG == null)
            throw new Exception("Image doesn't loaded.");

        if(IMG_DATA.size() != 0)
            return;

        int bitColor, r, g, b;

        int IMG_WIDTH = IMG.getWidth();
        int IMG_HEIGHT = IMG.getHeight();

        for(int x = 0; x < IMG_WIDTH; x += ALG_QUALITY)
        {
            for(int y = 0; y < IMG_HEIGHT; y += ALG_QUALITY)
            {
                bitColor = IMG.getRGB(x, y);

                r = getRedChannel(bitColor);
                g = getGreenChannel(bitColor);
                b = getBlueChannel(bitColor);


                IMG_DATA.add(new int[] {r, g, b});
            }
        }
    }

    /**
     * Get color palettes from BufferedImage
     * @param colors Amount of colors to get
     * @param algorithm Algorithm to use
     * @return ArrayList of palettes
     */
    public ArrayList<int[]> getPalette(int colors, ANALYZER_ALGORITHM algorithm) throws Exception
    {
        if(IMG_DATA.isEmpty())
            throw new Exception("First call getImageData()");

        ArrayList<int[]> result = new ArrayList<>();

        if(algorithm == ANALYZER_ALGORITHM.ALG_MEDIAN_CUT)
            medianCut(IMG_DATA, getTwoPow(colors));

        else if(algorithm == ANALYZER_ALGORITHM.ALG_K_MEANS)
            kMeans(IMG_DATA, colors);

        int dominantColorIdx = computeDominantColor();

        for (List<int[]> palette: IMG_PALETTES)
            result.add(computeAverageColor(palette));

        setDominantColor(result.get(dominantColorIdx));
        result.sort((firstColor, secondColor) -> Integer.compare(firstColor[0], secondColor[0]) + Integer.compare(firstColor[1], secondColor[1]) + Integer.compare(firstColor[2], secondColor[2]));

        return result;
    }

    /**
     * Get image average color
     * @return Average color from image in format [R, G, B]
     * @throws Exception Thrown when getImageData was not called
     */
    public int[] getAverageColor() throws Exception
    {
        if(IMG_AVERAGE_COLOR != null)
            return IMG_AVERAGE_COLOR;

        if(IMG_DATA == null)
            throw new Exception("getImageData was not called yet!");

        IMG_AVERAGE_COLOR = computeAverageColor(IMG_DATA);

        return IMG_AVERAGE_COLOR;
    }

    /**
     * Get image dominant color
     * @return Dominant color from image in format [R, G, B]
     * @throws Exception Thrown when getPalette was not called
     */
    public int[] getDominantColor() throws Exception
    {
        if(IMG_DOMINANT_COLOR == null)
            throw new Exception("getPalette need to be called first!");

        return IMG_DOMINANT_COLOR;
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
     * Call k-mean algorithm
     * @param palettes Initial colors dataset
     * @param colors K - Colors amount
     */
    private void kMeans(List<int[]> palettes, int colors)
    {
        if(colors < 2)
            colors = 2;

        HashMap<Centroid, List<CentroidData>> model = computeKMeans(palettes, colors);
        IMG_PALETTES.clear();

        for (Map.Entry<Centroid, List<CentroidData>> mapElement : model.entrySet())
        {
            ArrayList<int[]> palette = new ArrayList<>();

            for (CentroidData color : mapElement.getValue())
                palette.add(new int[] { color.getR(), color.getG(), color.getB()});

            IMG_PALETTES.add(palette);
        }
    }

    /**
     * K-mean algorithm
     * @param imgData Initial data-set
     * @param colors K
     * @return HashMap of centroids with data
     */
    private HashMap<Centroid, List<CentroidData>> computeKMeans(List<int[]> imgData, int colors)
    {

        boolean exit = true;

        Random random = new Random();
        Centroid[] centroids = new Centroid[colors];
        List<CentroidData> dataList = new ArrayList<>();
        HashMap<Centroid, List<CentroidData>> model = new HashMap<>();

        for(int i = 0; i < colors; i++)
        {
            int randIdx = random.nextInt(imgData.size());

            centroids[i] = new Centroid();
            centroids[i].setX(imgData.get(randIdx)[0]);
            centroids[i].setY(imgData.get(randIdx)[1]);
            centroids[i].setZ(imgData.get(randIdx)[2]);
            model.put(centroids[i], new ArrayList<>());
        }

        for (int[] color: imgData)
            dataList.add(new CentroidData(color));

        while(true)
        {
            for (CentroidData data: dataList)
            {
                int minIdx = 0;
                double distance;
                double minDistance = centroids[0].getDistance(data);

                for(int i = 1; i < colors; i++)
                {
                    distance = centroids[i].getDistance(data);

                    if(distance < minDistance)
                    {
                        minDistance = distance;
                        minIdx = i;
                    }
                }

                if(data.getCentroid() != centroids[minIdx])
                {
                    exit = false;

                    if(data.getCentroid() != null)
                        model.get(data.getCentroid()).remove(data);


                    data.setCentroid(centroids[minIdx]);
                    model.get(centroids[minIdx]).add(data);
                }
            }

            if(exit)
                break;

            Centroid mapCentroid;
            int sumX, sumY, sumZ, size;

            for (Map.Entry<Centroid, List<CentroidData>> mapElement : model.entrySet())
            {
                size = mapElement.getValue().size();

                if(size == 0)
                    continue;

                sumX = sumY = sumZ = 0;

                for(CentroidData color : mapElement.getValue())
                {
                    sumX += color.getR();
                    sumY += color.getG();
                    sumZ += color.getB();
                }

                mapCentroid = mapElement.getKey();

                sumX /= size;
                sumY /= size;
                sumZ /= size;

                mapCentroid.setX(sumX);
                mapCentroid.setY(sumY);
                mapCentroid.setZ(sumZ);
            }

            exit = true;
        }

        return model;
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

        else
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
     * Get average color from palette
     * @param palette Palette to find average color
     * @return Average color from palette
     */
    private int[] computeAverageColor(List<int[]> palette)
    {
        int R = 0, G = 0, B = 0, size = palette.size();

        if(size == 0)
            return new int[] {-1, -1, -1};


        for (int[] color: palette)
        {
            R += color[0];
            G += color[1];
            B += color[2];
        }

        R /= size;
        G /= size;
        B /= size;

        return new int[] {R, G, B};
    }

    /**
     * Used to compute dominant color palette
     * @return Index of dominant color palette
     */
    private int computeDominantColor()
    {
        List<int[]> maxPalette = IMG_PALETTES.stream().max(Comparator.comparingInt(List::size)).orElse(null);
        return (maxPalette == null ? -1 : IMG_PALETTES.indexOf(maxPalette));
    }

    /**
     * Get power of two. Used for median-cut algorithm
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
     * Set dominant color
     * @param IMG_DOMINANT_COLOR RGB Array of dominant color
     */
    private void setDominantColor(int[] IMG_DOMINANT_COLOR) { this.IMG_DOMINANT_COLOR = IMG_DOMINANT_COLOR; }

    /**
     * Open image
     * @param path Path to the image
     * @throws IOException Thrown when image doesn't find
     */
    private void openImage(String path) throws IOException { IMG = ImageIO.read(new File(path)); }

    /**
     * Used to reset all variables
     */
    private void resetImageData()
    {
        IMG_AVERAGE_COLOR =
        IMG_DOMINANT_COLOR = null;

        IMG_DATA.clear();
        IMG_PALETTES.clear();
    }

    /**
     * Get RED channel
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
