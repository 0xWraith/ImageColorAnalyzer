package com.wraith.imagecoloranalyzer;

public class CentroidData
{
    private int R;
    private int G;
    private int B;

    private Centroid centroid;

    public CentroidData(int[] color)
    {
        R = color[0];
        G = color[1];
        B = color[2];

        centroid = null;
    }

    public int getR() { return R; }
    public void setR(int r) { R = r; }

    public int getG() { return G; }
    public void setG(int g) { G = g; }

    public int getB() { return B; }
    public void setB(int b) { B = b; }

    public Centroid getCentroid() { return centroid; }
    public void setCentroid(Centroid centroid) { this.centroid = centroid; }
}
