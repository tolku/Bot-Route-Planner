package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Bot {
    private int coordX, coordY;
    private String objective;
    public int objCoordX, objCoordY;

    public void initialize(){
        String jobFileName = Main.getJobFileName();
        File job = new File(jobFileName);
        try (Scanner fileScanner = new Scanner(job)){
            coordX = fileScanner.nextInt();
            coordY = fileScanner.nextInt();
            objCoordX = fileScanner.nextInt();
            objCoordY = fileScanner.nextInt();
            objective = fileScanner.next();
        } catch (FileNotFoundException x){
            throw new RuntimeException(x);
        }
    }

    int getCoordX(){
        return coordX;
    }

    int getCoordY(){
        return coordY;
    }

    String getObjective(){
        return objective;
    }

    int getObjCoordX(){
        return objCoordX;
    }

    int getObjCoordY(){
        return objCoordY;
    }
}
