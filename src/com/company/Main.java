package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static String gridFileName, jobFileName;

    public static void main(String[] args) {
        gridFileName = args[0];
        jobFileName = args[1];
        List<Module> wantedProdGrid = new ArrayList<>();
        List<Module> unactiveFields = new ArrayList<>();
        Bot jacek = new Bot();
        Module grid = new Module();
        Main solver = new Main();
        jacek.initialize();
        grid.initialize();
        solver.productPlaceFinder(jacek, grid, wantedProdGrid);
        solver.unactivePlaceFinder(jacek, grid, unactiveFields);
        solver.numberingModules(jacek, grid);

    }

    public static String getGridFileName(){
        return gridFileName;
    }

    public static String getJobFileName(){
        return jobFileName;
    }

    public void productPlaceFinder(Bot jacek, Module grid, List<Module> wantedProdGrid){
        int foundProducts = 0;
        for (int counter = 0; counter < moduleNumber(grid); ++counter){
            for (int innerCounter = 0; innerCounter < grid.getN(); ++innerCounter){
                if (Objects.equals(jacek.getObjective(), grid.getProducts(innerCounter, grid, counter))){
                    wantedProdGrid.add(new Module());
                    wantedProdGrid.get(foundProducts).setCoordX(grid.getModuleList().get(counter).getCoordX());
                    wantedProdGrid.get(foundProducts).setCoordY(grid.getModuleList().get(counter).getCoordY());
                    ++foundProducts;
                }
            }
        }
    }

    public void unactivePlaceFinder(Bot jacek, Module grid, List<Module> unactiveFields){
        int unactiveFieldsCounter = 0;
        for (int counter = 0; counter < moduleNumber(grid); ++counter){
            if (grid.getModuleList().get(counter).getType() == 'O'){
                unactiveFields.add(new Module());
                unactiveFields.get(unactiveFieldsCounter).setCoordX(grid.getModuleList().get(counter).getCoordX());
                unactiveFields.get(unactiveFieldsCounter).setCoordY(grid.getModuleList().get(counter).getCoordY());
                ++unactiveFieldsCounter;
            }
        }
    }

    public void numberingModules(Bot jacek, Module grid){
        for (int counter = 0; counter < moduleNumber(grid); ++counter){
            int xDist = Math.abs(jacek.getCoordX() - grid.getModuleList().get(counter).getCoordX());
            int yDist = Math.abs(jacek.getCoordY() - grid.getModuleList().get(counter).getCoordY());
            int distance = xDist + yDist;
            grid.getModuleList().get(counter).setNumber(distance);
        }
    }

    private int moduleNumber(Module grid){
        return grid.getGridWidthX() * grid.getGridLengthY();
    }
}
