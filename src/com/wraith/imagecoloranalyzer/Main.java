package com.wraith.imagecoloranalyzer;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {

        ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer("testImage/1.jpg");
        imageColorAnalyzer.getImageData();

        for (int[] color: imageColorAnalyzer.getPalette(16))
            System.out.println(Arrays.toString(color));

    }
}
