# ImageColorAnalyzer

Java library to grab color palettes from image.

## How to use

First of all you need to create an ImageColorAnalyzer object:

```java
ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer("path/to/image.png");
```

Available constructors:

```java
public ImageColorAnalyzer();
public ImageColorAnalyzer(String path);
public ImageColorAnalyzer(int ALG_QUALITY);
public ImageColorAnalyzer(String path, int ALG_QUALITY);
```

After you created object, you can use this methods:

```java
public void getImageData();
public ArrayList<int[]> getPalette(int colors, ANALYZER_ALGORITHM algorithm)
public int getQuality();
public boolean setQuality(int ALG_QUALITY);
public void changeImage(String path);
public int[] getAverageColor();
public int[] getDominantColor();
```

## Method description

```java
public void getImageData()
```
> Get image pixels RGB data. You need to call it right before  `public ArrayList<int[]> getPalette(int colors);` only one time.
 
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
>  Getter for ALG_QUALITY.
 
```java
public boolean setQuality(int ALG_QUALITY);
```
> Setter for ALG_QUALITY. Range is from `1` to `10`. The less numbers - the better results you will get.
 
```java
public void changeImage(String path);
```
> Used to change image.
 
```java
public int[] getAverageColor();
```
> Get image average color. First of all you need to call `public void getImageData();`

```java
public int[] getDominantColor();
```
> Get image dominant color. First of all you need to call `public ArrayList<int[]> getPalette(int colors, ANALYZER_ALGORITHM algorithm);`
## Tests
 
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

### Test #1

<img src="/testImage/1.jpg" alt="Image used for test" width="400"/>

#### Returned result:

```java
K-Mean          |          Median-Cut
[7, 8, 5] | [12, 13, 11]
[12, 12, 10] | [17, 18, 15]
[15, 16, 14] | [21, 19, 17]
[19, 18, 16] | [19, 21, 23]
[13, 21, 29] | [22, 23, 19]
[22, 22, 20] | [34, 22, 22]
[26, 25, 24] | [25, 25, 24]
[48, 25, 25] | [15, 25, 35]
[7, 26, 46] | [31, 28, 26]
[33, 30, 31] | [35, 29, 30]
[3, 34, 66] | [56, 31, 34]
[49, 34, 40] | [5, 32, 59]
[68, 37, 41] | [40, 33, 38]
[87, 48, 51] | [76, 37, 38]
[143, 85, 68] | [68, 42, 50]
[207, 166, 137] | [110, 67, 63]

Dominant color  |  Average color
[26, 25, 24] | [37, 29, 32]
```

#### K-Mean:

<img src="https://via.placeholder.com/15/070805/070805.png" width="25px"/> <img src="https://via.placeholder.com/15/0c0c0a/0c0c0a.png" width="25px"/> <img src="https://via.placeholder.com/15/0f100e/0f100e.png" width="25px"/> <img src="https://via.placeholder.com/15/131210/131210.png" width="25px"/> <img src="https://via.placeholder.com/15/0d151d/0d151d.png" width="25px"/> <img src="https://via.placeholder.com/15/161614/161614.png" width="25px"/> <img src="https://via.placeholder.com/15/1a1918/1a1918.png" width="25px"/> <img src="https://via.placeholder.com/15/301919/301919.png" width="25px"/> <img src="https://via.placeholder.com/15/071a2e/071a2e.png" width="25px"/> <img src="https://via.placeholder.com/15/211e1f/211e1f.png" width="25px"/> <img src="https://via.placeholder.com/15/032242/032242.png" width="25px"/> <img src="https://via.placeholder.com/15/312228/312228.png" width="25px"/> <img src="https://via.placeholder.com/15/442529/442529.png" width="25px"/> <img src="https://via.placeholder.com/15/573033/573033.png" width="25px"/> <img src="https://via.placeholder.com/15/8f5544/8f5544.png" width="25px"/> <img src="https://via.placeholder.com/15/cfa689/cfa689.png" width="25px"/> 

#### Median-cut:

<img src="https://via.placeholder.com/15/0c0d0b/0c0d0b.png" width="25px"/> <img src="https://via.placeholder.com/15/11120f/11120f.png" width="25px"/> <img src="https://via.placeholder.com/15/151311/151311.png" width="25px"/> <img src="https://via.placeholder.com/15/131517/131517.png" width="25px"/> <img src="https://via.placeholder.com/15/161713/161713.png" width="25px"/> <img src="https://via.placeholder.com/15/221616/221616.png" width="25px"/> <img src="https://via.placeholder.com/15/191918/191918.png" width="25px"/> <img src="https://via.placeholder.com/15/0f1923/0f1923.png" width="25px"/> <img src="https://via.placeholder.com/15/1f1c1a/1f1c1a.png" width="25px"/> <img src="https://via.placeholder.com/15/231d1e/231d1e.png" width="25px"/> <img src="https://via.placeholder.com/15/381f22/381f22.png" width="25px"/> <img src="https://via.placeholder.com/15/05203b/05203b.png" width="25px"/> <img src="https://via.placeholder.com/15/282126/282126.png" width="25px"/> <img src="https://via.placeholder.com/15/4c2526/4c2526.png" width="25px"/> <img src="https://via.placeholder.com/15/442a32/442a32.png" width="25px"/> <img src="https://via.placeholder.com/15/6e433f/6e433f.png" width="25px"/> 

#### Average color:

<img src="https://via.placeholder.com/15/251d20/251d20.png" width="25px"/> 

#### Dominant color:

<img src="https://via.placeholder.com/15/1a1918/1a1918.png" width="25px"/> 

### Test #2

<img src="/testImage/2.jpg" alt="Image used for test" width="400"/>

#### Returned result:

```java
K-Mean          |          Median-Cut
[14, 24, 18] | [16, 27, 20]
[20, 30, 24] | [21, 32, 26]
[11, 50, 29] | [27, 34, 27]
[26, 36, 29] | [15, 50, 31]
[34, 44, 36] | [28, 37, 32]
[59, 51, 37] | [32, 44, 34]
[22, 63, 40] | [34, 52, 42]
[40, 67, 50] | [54, 57, 46]
[84, 68, 52] | [31, 71, 47]
[58, 75, 61] | [57, 79, 62]
[74, 84, 73] | [69, 76, 64]
[89, 95, 86] | [86, 76, 62]
[129, 102, 84] | [81, 87, 81]
[110, 117, 109] | [89, 102, 91]
[138, 138, 132] | [121, 117, 107]
[167, 165, 160] | [154, 154, 149]

Dominant color  |  Average color
[26, 36, 29] | [57, 68, 58]
```

#### K-Mean:

<img src="https://via.placeholder.com/15/0e1812/0e1812.png" width="25px"/> <img src="https://via.placeholder.com/15/141e18/141e18.png" width="25px"/> <img src="https://via.placeholder.com/15/0b321d/0b321d.png" width="25px"/> <img src="https://via.placeholder.com/15/1a241d/1a241d.png" width="25px"/> <img src="https://via.placeholder.com/15/222c24/222c24.png" width="25px"/> <img src="https://via.placeholder.com/15/3b3325/3b3325.png" width="25px"/> <img src="https://via.placeholder.com/15/163f28/163f28.png" width="25px"/> <img src="https://via.placeholder.com/15/284332/284332.png" width="25px"/> <img src="https://via.placeholder.com/15/544434/544434.png" width="25px"/> <img src="https://via.placeholder.com/15/3a4b3d/3a4b3d.png" width="25px"/> <img src="https://via.placeholder.com/15/4a5449/4a5449.png" width="25px"/> <img src="https://via.placeholder.com/15/595f56/595f56.png" width="25px"/> <img src="https://via.placeholder.com/15/816654/816654.png" width="25px"/> <img src="https://via.placeholder.com/15/6e756d/6e756d.png" width="25px"/> <img src="https://via.placeholder.com/15/8a8a84/8a8a84.png" width="25px"/> <img src="https://via.placeholder.com/15/a7a5a0/a7a5a0.png" width="25px"/> 

#### Median-cut:

<img src="https://via.placeholder.com/15/101b14/101b14.png" width="25px"/> <img src="https://via.placeholder.com/15/15201a/15201a.png" width="25px"/> <img src="https://via.placeholder.com/15/1b221b/1b221b.png" width="25px"/> <img src="https://via.placeholder.com/15/0f321f/0f321f.png" width="25px"/> <img src="https://via.placeholder.com/15/1c2520/1c2520.png" width="25px"/> <img src="https://via.placeholder.com/15/202c22/202c22.png" width="25px"/> <img src="https://via.placeholder.com/15/22342a/22342a.png" width="25px"/> <img src="https://via.placeholder.com/15/36392e/36392e.png" width="25px"/> <img src="https://via.placeholder.com/15/1f472f/1f472f.png" width="25px"/> <img src="https://via.placeholder.com/15/394f3e/394f3e.png" width="25px"/> <img src="https://via.placeholder.com/15/454c40/454c40.png" width="25px"/> <img src="https://via.placeholder.com/15/564c3e/564c3e.png" width="25px"/> <img src="https://via.placeholder.com/15/515751/515751.png" width="25px"/> <img src="https://via.placeholder.com/15/59665b/59665b.png" width="25px"/> <img src="https://via.placeholder.com/15/79756b/79756b.png" width="25px"/> <img src="https://via.placeholder.com/15/9a9a95/9a9a95.png" width="25px"/> 

#### Average color:

<img src="https://via.placeholder.com/15/39443a/39443a.png" width="25px"/> 

#### Dominant color:

<img src="https://via.placeholder.com/15/1a241d/1a241d.png" width="25px"/> 

### Test #3

<img src="/testImage/3.jpg" alt="Image used for test" width="400"/>

#### Returned result:

```java
K-Mean          |          Median-Cut
[1, 4, 9] | [1, 4, 9]
[0, 5, 9] | [1, 4, 10]
[2, 6, 12] | [1, 6, 10]
[1, 7, 13] | [2, 6, 12]
[23, 8, 13] | [2, 6, 13]
[3, 8, 14] | [2, 9, 15]
[5, 11, 18] | [4, 18, 27]
[14, 16, 26] | [3, 19, 28]
[3, 17, 26] | [11, 19, 29]
[57, 17, 28] | [54, 17, 31]
[103, 3, 29] | [3, 22, 32]
[4, 22, 32] | [5, 23, 33]
[25, 24, 35] | [6, 24, 34]
[6, 27, 38] | [5, 25, 36]
[159, 4, 44] | [5, 27, 38]
[196, 117, 117] | [6, 29, 39]

Dominant color  |  Average color
[6, 27, 38] | [7, 16, 25]
```

#### K-Mean:

<img src="https://via.placeholder.com/15/010409/010409.png" width="25px"/> <img src="https://via.placeholder.com/15/000509/000509.png" width="25px"/> <img src="https://via.placeholder.com/15/02060c/02060c.png" width="25px"/> <img src="https://via.placeholder.com/15/01070d/01070d.png" width="25px"/> <img src="https://via.placeholder.com/15/17080d/17080d.png" width="25px"/> <img src="https://via.placeholder.com/15/03080e/03080e.png" width="25px"/> <img src="https://via.placeholder.com/15/050b12/050b12.png" width="25px"/> <img src="https://via.placeholder.com/15/0e101a/0e101a.png" width="25px"/> <img src="https://via.placeholder.com/15/03111a/03111a.png" width="25px"/> <img src="https://via.placeholder.com/15/39111c/39111c.png" width="25px"/> <img src="https://via.placeholder.com/15/67031d/67031d.png" width="25px"/> <img src="https://via.placeholder.com/15/041620/041620.png" width="25px"/> <img src="https://via.placeholder.com/15/191823/191823.png" width="25px"/> <img src="https://via.placeholder.com/15/061b26/061b26.png" width="25px"/> <img src="https://via.placeholder.com/15/9f042c/9f042c.png" width="25px"/> <img src="https://via.placeholder.com/15/c47575/c47575.png" width="25px"/> 

#### Median-cut:

<img src="https://via.placeholder.com/15/010409/010409.png" width="25px"/> <img src="https://via.placeholder.com/15/01040a/01040a.png" width="25px"/> <img src="https://via.placeholder.com/15/01060a/01060a.png" width="25px"/> <img src="https://via.placeholder.com/15/02060c/02060c.png" width="25px"/> <img src="https://via.placeholder.com/15/02060d/02060d.png" width="25px"/> <img src="https://via.placeholder.com/15/02090f/02090f.png" width="25px"/> <img src="https://via.placeholder.com/15/04121b/04121b.png" width="25px"/> <img src="https://via.placeholder.com/15/03131c/03131c.png" width="25px"/> <img src="https://via.placeholder.com/15/0b131d/0b131d.png" width="25px"/> <img src="https://via.placeholder.com/15/36111f/36111f.png" width="25px"/> <img src="https://via.placeholder.com/15/031620/031620.png" width="25px"/> <img src="https://via.placeholder.com/15/051721/051721.png" width="25px"/> <img src="https://via.placeholder.com/15/061822/061822.png" width="25px"/> <img src="https://via.placeholder.com/15/051924/051924.png" width="25px"/> <img src="https://via.placeholder.com/15/051b26/051b26.png" width="25px"/> <img src="https://via.placeholder.com/15/061d27/061d27.png" width="25px"/> 

#### Average color:

<img src="https://via.placeholder.com/15/071019/071019.png" width="25px"/> 

#### Dominant color:

<img src="https://via.placeholder.com/15/061b26/061b26.png" width="25px"/> 

### Test #4

<img src="/testImage/4.jpg" alt="Image used for test" width="400"/>

#### Returned result:

```java
K-Mean          |          Median-Cut
[0, 0, 0] | [8, 1, 1]
[3, 0, 0] | [12, 4, 2]
[6, 1, 0] | [17, 4, 4]
[9, 1, 1] | [17, 7, 4]
[13, 3, 2] | [18, 7, 6]
[18, 6, 5] | [21, 7, 6]
[27, 11, 9] | [25, 7, 7]
[54, 22, 4] | [19, 11, 10]
[92, 30, 3] | [27, 10, 10]
[2, 42, 51] | [30, 16, 5]
[138, 53, 14] | [31, 13, 13]
[4, 63, 87] | [48, 19, 4]
[2, 85, 132] | [75, 23, 1]
[206, 101, 40] | [90, 37, 9]
[1, 124, 195] | [0, 80, 118]
[250, 214, 186] | [170, 82, 36]

Dominant color  |  Average color
[27, 11, 9] | [38, 20, 15]
```

#### K-Mean:

<img src="https://via.placeholder.com/15/000000/000000.png" width="25px"/> <img src="https://via.placeholder.com/15/030000/030000.png" width="25px"/> <img src="https://via.placeholder.com/15/060100/060100.png" width="25px"/> <img src="https://via.placeholder.com/15/090101/090101.png" width="25px"/> <img src="https://via.placeholder.com/15/0d0302/0d0302.png" width="25px"/> <img src="https://via.placeholder.com/15/120605/120605.png" width="25px"/> <img src="https://via.placeholder.com/15/1b0b09/1b0b09.png" width="25px"/> <img src="https://via.placeholder.com/15/361604/361604.png" width="25px"/> <img src="https://via.placeholder.com/15/5c1e03/5c1e03.png" width="25px"/> <img src="https://via.placeholder.com/15/022a33/022a33.png" width="25px"/> <img src="https://via.placeholder.com/15/8a350e/8a350e.png" width="25px"/> <img src="https://via.placeholder.com/15/043f57/043f57.png" width="25px"/> <img src="https://via.placeholder.com/15/025584/025584.png" width="25px"/> <img src="https://via.placeholder.com/15/ce6528/ce6528.png" width="25px"/> <img src="https://via.placeholder.com/15/017cc3/017cc3.png" width="25px"/> <img src="https://via.placeholder.com/15/fad6ba/fad6ba.png" width="25px"/> 

#### Median-cut:

<img src="https://via.placeholder.com/15/080101/080101.png" width="25px"/> <img src="https://via.placeholder.com/15/0c0402/0c0402.png" width="25px"/> <img src="https://via.placeholder.com/15/110404/110404.png" width="25px"/> <img src="https://via.placeholder.com/15/110704/110704.png" width="25px"/> <img src="https://via.placeholder.com/15/120706/120706.png" width="25px"/> <img src="https://via.placeholder.com/15/150706/150706.png" width="25px"/> <img src="https://via.placeholder.com/15/190707/190707.png" width="25px"/> <img src="https://via.placeholder.com/15/130b0a/130b0a.png" width="25px"/> <img src="https://via.placeholder.com/15/1b0a0a/1b0a0a.png" width="25px"/> <img src="https://via.placeholder.com/15/1e1005/1e1005.png" width="25px"/> <img src="https://via.placeholder.com/15/1f0d0d/1f0d0d.png" width="25px"/> <img src="https://via.placeholder.com/15/301304/301304.png" width="25px"/> <img src="https://via.placeholder.com/15/4b1701/4b1701.png" width="25px"/> <img src="https://via.placeholder.com/15/5a2509/5a2509.png" width="25px"/> <img src="https://via.placeholder.com/15/005076/005076.png" width="25px"/> <img src="https://via.placeholder.com/15/aa5224/aa5224.png" width="25px"/> 

#### Average color:

<img src="https://via.placeholder.com/15/26140f/26140f.png" width="25px"/> 

#### Dominant color:

<img src="https://via.placeholder.com/15/1b0b09/1b0b09.png" width="25px"/> 
