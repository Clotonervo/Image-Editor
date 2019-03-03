package com.company;


import java.io.*;
import java.util.Scanner;

public class IOStream
{
//----------------------------------------- Creating Image from Input ------------------------------------------------------------
    public static Image getInput(String inputFileName) throws IOException
    {

        Scanner input = new Scanner(new File(inputFileName));
        input.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");

        String startString = input.next();
        int imageWidth = input.nextInt();
        int imageHeight = input.nextInt();
        int maxColorValue = input.nextInt();

        // put pixel reading in separate method
        Pixel [][] pixelArray = new Pixel[imageHeight][imageWidth];

        int counterX = 0;
        int counterY = 0;
        while (input.hasNext()) {
            int redPixel = input.nextInt();
            int greenPixel = input.nextInt();
            int bluePixel = input.nextInt();

            Pixel newPixel = new Pixel(redPixel, greenPixel, bluePixel);
            pixelArray[counterY][counterX] = newPixel;
            counterX++;
            if (counterX == imageWidth) {
                counterY++;
                counterX = 0;
            }
        }
        input.close();

        return new Image(imageHeight,imageWidth, pixelArray);
    }

//----------------------------------------- Creating Output to convert into a file ---------------------------------------------

    public static String getOutput(Image inputImage)
    {
        StringBuilder outputStringBuilder = new StringBuilder();
        outputStringBuilder.append("P3" + '\n' + inputImage.imageWidth + " " + inputImage.imageHeight + '\n' + "255" + '\n');

        for (int i = 0; i < inputImage.imageHeight; ++i){
            for (int j = 0; j < inputImage.imageWidth; ++j){
                outputStringBuilder.append(inputImage.pixelArray[i][j].toString() + "\n");
            }
        }

        outputStringBuilder.deleteCharAt(outputStringBuilder.length() - 1);
        return outputStringBuilder.toString();
    }




}
