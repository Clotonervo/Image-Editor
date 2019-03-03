package com.company;


import java.io.*;


public class ImageEditor
{
    private static IOStream ioStream;
    private static Image currImage;
    private static Image copyImage;
    private static String command;

//----------------------------------------- Main ------------------------------------------------------------
    public static void main(String[] args)
    {
        for (int i = 0; i < args.length; ++i){
            System.out.println("arg[" + i + "] = " + args[i]);
        }
        String fileName = args[0];
        String outputFileName = args[1];

        if (args.length < 3){
            command = "no command";
        }
        else {
            command = args[2];
        }


        try {
            currImage = ioStream.getInput(fileName);
            copyImage = ioStream.getInput(fileName);
        }
        catch (IOException x){
            x.printStackTrace();
        }

        if(command.contentEquals("invert")){
            invert(currImage);
        }
        else if (command.contentEquals("grayscale")){
            grayScale(currImage);
        }
        else if (command.contentEquals("emboss")){
            emboss(currImage);
        }
        else if (command.contentEquals("motionblur")){
            int n = Integer.parseInt(args[3]);
            motionBlur(currImage, n);
        }


        try {
            outputFile(outputFileName);
        }
        catch (IOException x){
            x.printStackTrace();
        }
        System.out.println(command);
        System.out.println("Success!");


    }

//----------------------------------------- Outputting to a File ------------------------------------------------------------
    public static void outputFile(String outFileName) throws IOException
    {
        String outputFileString = ioStream.getOutput(currImage);

        PrintWriter pw = new PrintWriter(new File(outFileName));
        pw.println(outputFileString);

        pw.close();
    }


//----------------------------------------- Transformation Algorithms ---------------------------------------------------------
    public static Image invert(Image editImage)
    {
        for (int i = 0; i < editImage.imageHeight; ++i){
            for (int j = 0; j < editImage.imageWidth; ++j){
                editImage.pixelArray[i][j].invertPixel();
            }
        }
        return editImage;
    }


    public static Image grayScale(Image editImage)
    {
        for (int i = 0; i < editImage.imageHeight; ++i){
            for (int j = 0; j < editImage.imageWidth; ++j){
                editImage.pixelArray[i][j].grayScalePixel();
            }
        }
        return editImage;
    }


    public static Image emboss(Image editImage)
    {
        for (int i = 0; i <= editImage.imageHeight - 1; ++i){
            for (int j = 0; j <= editImage.imageWidth - 1; ++j){
                int redDif = 0;
                int greenDif = 0;
                int blueDif = 0;
                if (i == 0 || j == 0){
                    editImage.pixelArray[i][j].redValue = 128;
                    editImage.pixelArray[i][j].greenValue = 128;
                    editImage.pixelArray[i][j].blueValue = 128;

                }
                else {
                    redDif = (copyImage.pixelArray[i][j].redValue - copyImage.pixelArray[i - 1][j - 1].redValue);
                    greenDif = (copyImage.pixelArray[i][j].greenValue - copyImage.pixelArray[i - 1][j - 1].greenValue);
                    blueDif = (copyImage.pixelArray[i][j].blueValue - copyImage.pixelArray[i - 1][j - 1].blueValue);

                    if (i == 0 && j == 750){
                        i = 0;
                    }
                    int difMax = redDif;

                    if(java.lang.Math.abs(difMax) < java.lang.Math.abs(greenDif)){
                        difMax = greenDif;
                    }
                    if(java.lang.Math.abs(difMax) < java.lang.Math.abs(blueDif)){
                        difMax = blueDif;
                    }

                    int v = 128 + difMax;

                    if (v > 255){
                        v = 255;
                    }
                    else if (v < 0){
                        v = 0;
                    }

                    editImage.pixelArray[i][j].embossPixel(v);

                }
            }
        }

        return editImage;
    }


    public static Image motionBlur(Image editImage, int n)
    {

        for (int i = 0; i < editImage.imageHeight; ++i){
            for (int j = 0; j < editImage.imageWidth; ++j){
                int numBlur = n;
                int averageRed = 0;
                int averageGre = 0;
                int averageBlu = 0;

                if (i == 97){
                    i = 97;
                }
                if (numBlur + j >= editImage.imageWidth){

                    numBlur = numBlur - (j + numBlur - editImage.imageWidth);
                }

                for (int q = 1; q <= numBlur; ++q){
                    averageRed = averageRed + copyImage.pixelArray[i][j + q - 1].redValue;
                    averageGre = averageGre + copyImage.pixelArray[i][j + q - 1].greenValue;
                    averageBlu = averageBlu + copyImage.pixelArray[i][j + q - 1].blueValue;

                }
                averageRed = averageRed/numBlur;
                averageGre = averageGre/numBlur;
                averageBlu = averageBlu/numBlur;

                editImage.pixelArray[i][j].redValue = averageRed;
                editImage.pixelArray[i][j].greenValue = averageGre;
                editImage.pixelArray[i][j].blueValue = averageBlu;


            }
        }


        return editImage;
    }






}
