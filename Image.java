package com.company;


public class Image {

    Image()
    {
        imageWidth = 0;
        imageHeight = 0;
    }

    Image(int height, int width, Pixel[][] inputPixelArray)
    {
        imageHeight = height;
        imageWidth = width;
        pixelArray = inputPixelArray;

    }

    public int imageHeight;
    public int imageWidth;
    public Pixel[][] pixelArray;
}
