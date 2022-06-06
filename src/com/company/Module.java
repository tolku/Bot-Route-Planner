package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Module {
    private char type;
    private int coordX, coordY, number, n, gridWidthX, gridLengthY;
    private String[] products;
    private static List<Module> moduleList = new ArrayList<>();

    public void initialize() {
        String gridFileName = Main.getGridFileName();
        File grid = new File(gridFileName);
        try (Scanner fileScanner = new Scanner(grid)) {
            gridWidthX = fileScanner.nextInt();
            gridLengthY = fileScanner.nextInt();
            n = fileScanner.nextInt();
            int moduleCounter = 0;

            for (int counter = 0; counter < gridLengthY; ++counter) {
                String temp;
                temp = fileScanner.next();
                for (int innerCounter = 0; innerCounter < temp.length(); ++innerCounter, ++moduleCounter) {
                    moduleList.add(new Module());
                    moduleList.get(moduleCounter).coordX = innerCounter;
                    moduleList.get(moduleCounter).coordY = counter;
                    moduleList.get(moduleCounter).products = new String[n];
                    moduleList.get(moduleCounter).type = temp.charAt(innerCounter);
                }
            }

            while (fileScanner.hasNext()) {
                int tempX, tempY, tempN;
                String productName = fileScanner.next();
                tempX = fileScanner.nextInt();
                tempY = fileScanner.nextInt();
                tempN = fileScanner.nextInt();
                for (int counter = 0; counter < moduleCounter; ++counter) {
                    if (moduleList.get(counter).coordX == tempX && moduleList.get(counter).coordY == tempY) {
                        moduleList.get(counter).products[tempN] = productName;
                    }
                }

            }

        } catch (FileNotFoundException x) {
            throw new RuntimeException(x);
        }
    }

    static List<Module> getModuleList() {
        return moduleList;
    }

    char getType() {
        return type;
    }

    int getCoordX() {
        return coordX;
    }

    int getCoordY() {
        return coordY;
    }

    int getN() {
        return n;
    }

    int getNumber() {
        return number;
    }

    int getGridWidthX() {
        return gridWidthX;
    }

    int getGridLengthY() {
        return gridLengthY;
    }

    String getProducts(int n, Module grid, int moduleNumber) { //gdzie n to poziom, z kto
        return grid.getModuleList().get(moduleNumber).products[n];
    }

    void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    void setNumber(int number) {
        this.number = number;
    }
}
