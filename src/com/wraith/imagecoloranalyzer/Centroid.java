package com.wraith.imagecoloranalyzer;

import java.util.Random;

public class Centroid
{
    private int X;
    private int Y;
    private int Z;
    public int number;

    public Centroid(int number)
    {
        Random random = new Random();

        this.number = number;
        X = random.nextInt(256);
        Y = random.nextInt(256);
        Z = random.nextInt(256);

        System.out.printf("Created centroid #%d at %d, %d, %d\n", number, X, Y, Z);
    }

    public double getDistance(CentroidData centroidData)
    {
        return Math.sqrt(
                (Math.pow(X - centroidData.getR(), 2) +
                        Math.pow(Y - centroidData.getG(), 2) +
                        Math.pow(Z - centroidData.getB(), 2)));

    }

    @Override
    public String toString()
    {
        return String.format("{%d, %d, %d, %d}", number, X, Y, Z);
    }

    public int getX() { return X; }
    public void setX(int X) { this.X = X; }

    public int getY() { return Y; }
    public void setY(int Y) { this.Y = Y; }

    public int getZ() { return Z; }
    public void setZ(int Z) { this.Z = Z; }
}
