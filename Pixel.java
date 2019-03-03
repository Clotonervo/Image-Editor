package com.company;

public class Pixel
{
    public int redValue;
    public int greenValue;
    public int blueValue;

    Pixel()
    {
        redValue = 0;
        greenValue = 0;
        blueValue = 0;
    }

    Pixel(int redInput, int greenInput, int blueInput)
    {
        redValue = redInput;
        greenValue = greenInput;
        blueValue = blueInput;
    }

    public void invertPixel()
    {
        redValue = java.lang.Math.abs(redValue - 255);
        greenValue = java.lang.Math.abs(greenValue - 255);
        blueValue = java.lang.Math.abs(blueValue - 255);

    }

    public void grayScalePixel()
    {
        int averageVal = (redValue + greenValue + blueValue)/3;
        redValue = averageVal;
        greenValue = averageVal;
        blueValue = averageVal;
    }

    public void embossPixel(int v)
    {
        redValue = v;
        greenValue = v;
        blueValue = v;
    }

    @Override
    public  String toString()
    {
        return (redValue + "\n" + greenValue + "\n" + blueValue);
    }

}
