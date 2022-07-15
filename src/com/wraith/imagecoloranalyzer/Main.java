package com.wraith.imagecoloranalyzer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {

        ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer("testImage/1.jpg", 10);
        imageColorAnalyzer.getImageData();


        ArrayList<int[]> result = imageColorAnalyzer.getPalette(16, ImageColorAnalyzer.ANALYZER_ALGORITHM.ALG_K_MEANS);

        System.out.println("```java");

        for (int[] color: result)
            System.out.println(Arrays.toString(color));

        System.out.println("```");

        for(int[] color: result)
        {
            Color your_color = new Color(color[0], color[1],color[2]);
            String hex = Integer.toHexString(your_color.getRGB()).substring(2);
            System.out.printf("- ![#%s](https://via.placeholder.com/15/%s/%s.png) `#%s`\n", hex, hex, hex, hex);
        }

    }
}
