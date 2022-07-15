# ImageColorAnalyzer
Java library to grab color palettes from image.

## How to use

First of all you need to create an ImageColorAnalyzer object:
```java
ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer("path/to/image.png");
```

Available constructors:
```java
public ImageColorAnalyzer(String path);
public ImageColorAnalyzer(String path, int ALG_QUALITY);
```

After you created object, you can use this methods:

```java
public void getImageData();
public ArrayList<int[]> getPalette(int colors, ANALYZER_ALGORITHM algorithm)
public int getQuality();
public boolean setQuality(int ALG_QUALITY);
```

## Method description
```java
public void getImageData()
```
> Get image pixels RGB data. You need to call it right before  `public ArrayList<int[]> getPalette(int colors);` only one time
 
```java 
public ArrayList<int[]> getPalette(int colors, ANALYZER_ALGORITHM algorithm)
```
> Get color palettes from BufferedImage. Algorithms you can use:
> `enum ANALYZER_ALGORITHM
    {
        ALG_MEDIAN_CUT,
        ALG_K_MEANS
    }`
 
```java
public int getQuality();
```
>  Getter for ALG_QUALITY
 
```java
public boolean setQuality(int ALG_QUALITY);
```
> Setter for ALG_QUALITY. Range is from `1` to `10`. The less numbers - the better results you will get.
 
## Test
 
### Code used
Code used for test:
```java
public class Main
{
    public static void main(String[] args)
    {

        ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer("testImage/1.jpg");
        imageColorAnalyzer.setQuality(10);
        imageColorAnalyzer.getImageData();


        //for (int[] color: imageColorAnalyzer.getPalette(16, ImageColorAnalyzer.ANALYZER_ALGORITHM.ALG_MEDIAN_CUT))
        for (int[] color: imageColorAnalyzer.getPalette(12, ImageColorAnalyzer.ANALYZER_ALGORITHM.ALG_K_MEANS))
            System.out.println(Arrays.toString(color));

    }
}
```

### Test image:

<img src="/testImage/1.jpg" alt="Image used for test" width="400"/>

### Returned result:

#### Median-cut
```java
[12, 13, 11]
[17, 18, 15]
[21, 19, 17]
[19, 21, 23]
[22, 23, 19]
[34, 22, 22]
[25, 25, 24]
[15, 25, 35]
[31, 28, 26]
[35, 29, 30]
[56, 31, 34]
[5, 32, 59]
[40, 33, 38]
[76, 37, 38]
[68, 42, 50]
[110, 67, 63]
```

#### K-Mean
```java
[9, 10, 7]
[14, 15, 13]
[20, 19, 18]
[10, 21, 33]
[25, 24, 23]
[34, 28, 29]
[9, 28, 49]
[55, 33, 37]
[3, 35, 67]
[81, 45, 48]
[140, 82, 67]
[206, 164, 135]
```
### Returned results(colored):

#### Median-cut
- ![#0c0d0b](https://via.placeholder.com/15/0c0d0b/0c0d0b.png) `#0c0d0b`
- ![#11120f](https://via.placeholder.com/15/11120f/11120f.png) `#11120f`
- ![#151311](https://via.placeholder.com/15/151311/151311.png) `#151311`
- ![#131517](https://via.placeholder.com/15/131517/131517.png) `#131517`
- ![#161713](https://via.placeholder.com/15/161713/161713.png) `#161713`
- ![#221616](https://via.placeholder.com/15/221616/221616.png) `#221616`
- ![#191918](https://via.placeholder.com/15/191918/191918.png) `#191918`
- ![#0f1923](https://via.placeholder.com/15/0f1923/0f1923.png) `#0f1923`
- ![#1f1c1a](https://via.placeholder.com/15/1f1c1a/1f1c1a.png) `#1f1c1a`
- ![#231d1e](https://via.placeholder.com/15/231d1e/231d1e.png) `#231d1e`
- ![#381f22](https://via.placeholder.com/15/381f22/381f22.png) `#381f22`
- ![#05203b](https://via.placeholder.com/15/05203b/05203b.png) `#05203b`
- ![#282126](https://via.placeholder.com/15/282126/282126.png) `#282126`
- ![#4c2526](https://via.placeholder.com/15/4c2526/4c2526.png) `#4c2526`
- ![#442a32](https://via.placeholder.com/15/442a32/442a32.png) `#442a32`
- ![#6e433f](https://via.placeholder.com/15/6e433f/6e433f.png) `#6e433f`
- ![#aa6c4a](https://via.placeholder.com/15/aa6c4a/aa6c4a.png) `#aa6c4a`

#### K-Mean
- ![#090a07](https://via.placeholder.com/15/090a07/090a07.png) `#090a07`
- ![#0e0f0d](https://via.placeholder.com/15/0e0f0d/0e0f0d.png) `#0e0f0d`
- ![#141312](https://via.placeholder.com/15/141312/141312.png) `#141312`
- ![#0a1521](https://via.placeholder.com/15/0a1521/0a1521.png) `#0a1521`
- ![#191817](https://via.placeholder.com/15/191817/191817.png) `#191817`
- ![#221c1d](https://via.placeholder.com/15/221c1d/221c1d.png) `#221c1d`
- ![#091c31](https://via.placeholder.com/15/091c31/091c31.png) `#091c31`
- ![#372125](https://via.placeholder.com/15/372125/372125.png) `#372125`
- ![#032343](https://via.placeholder.com/15/032343/032343.png) `#032343`
- ![#512d30](https://via.placeholder.com/15/512d30/512d30.png) `#512d30`
- ![#8c5243](https://via.placeholder.com/15/8c5243/8c5243.png) `#8c5243`
- ![#cea487](https://via.placeholder.com/15/cea487/cea487.png) `#cea487`
