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
public ArrayList<int[]> getPalette(int colors);
public int getQuality();
public boolean setQuality(int ALG_QUALITY);
```

## Method description
```java
public void getImageData()
```
> Get image pixels RGB data. You need to call it right before  `public ArrayList<int[]> getPalette(int colors);` only one time
 
```java 
public ArrayList<int[]> getPalette(int colors);
```
> Get color palettes from BufferedImage
 
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

        ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer("testImages/1.jpg");
        imageColorAnalyzer.setQuality(10);
        imageColorAnalyzer.getImageData();


        for (int[] color: imageColorAnalyzer.getPalette(16))
            System.out.println(Arrays.toString(color));

    }
}
```

### Test image:


### Returned result:
```java
[9, 17, 23] //Dominant color
[18, 19, 21]
[1, 22, 44]
[12, 27, 39]
[1, 28, 55]
[5, 33, 57]
[4, 36, 64]
[2, 39, 75]
[22, 22, 23]
[26, 24, 25]
[28, 29, 31]
[40, 31, 35]
[56, 29, 36]
[63, 39, 46]
[85, 43, 49]
[170, 108, 74]
```

### Returned results(colored):

- ![#091117](https://via.placeholder.com/15/091117/091117.png) `#091117`
- ![#121315](https://via.placeholder.com/15/121315/121315.png) `#121315`
- ![#01162c](https://via.placeholder.com/15/01162c/01162c.png) `#01162c`
- ![#0c1b27](https://via.placeholder.com/15/0c1b27/0c1b27.png) `#0c1b27`
- ![#011c37](https://via.placeholder.com/15/011c37/011c37.png) `#011c37`
- ![#052139](https://via.placeholder.com/15/052139/052139.png) `#052139`
- ![#042440](https://via.placeholder.com/15/042440/042440.png) `#042440`
- ![#02274b](https://via.placeholder.com/15/02274b/02274b.png) `#02274b`
- ![#161617](https://via.placeholder.com/15/161617/161617.png) `#161617`
- ![#1a1819](https://via.placeholder.com/15/1a1819/1a1819.png) `#1a1819`
- ![#1c1d1f](https://via.placeholder.com/15/1c1d1f/1c1d1f.png) `#1c1d1f`
- ![#281f23](https://via.placeholder.com/15/281f23/281f23.png) `#281f23`
- ![#381d24](https://via.placeholder.com/15/381d24/381d24.png) `#381d24`
- ![#3f272e](https://via.placeholder.com/15/3f272e/3f272e.png) `#3f272e`
- ![#552b31](https://via.placeholder.com/15/552b31/552b31.png) `#552b31`
- ![#aa6c4a](https://via.placeholder.com/15/aa6c4a/aa6c4a.png) `#aa6c4a`
