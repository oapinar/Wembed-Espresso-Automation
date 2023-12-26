package com.wembed;

public class IngredientStock {
    private double espressoStock = 300;
    private double waterStock = 2000;
    private double milkStock = 1000;
    private double syrupStock = 500;
    private double iceStock = 30;

    public IngredientStock() {}
    public IngredientStock(double espressoStock, double waterStock, double milkStock, double syrupStock, double iceStock) {
        this.espressoStock = espressoStock;
        this.waterStock = waterStock;
        this.milkStock = milkStock;
        this.syrupStock = syrupStock;
        this.iceStock = iceStock;
    }

    public double getEspressoStock() {
        return espressoStock;
    }

    public void setEspressoStock(double espressoStock) {
        this.espressoStock = espressoStock;
    }

    public double getWaterStock() {
        return waterStock;
    }

    public void setWaterStock(double waterStock) {
        this.waterStock = waterStock;
    }

    public double getMilkStock() {
        return milkStock;
    }

    public void setMilkStock(double milkStock) {
        this.milkStock = milkStock;
    }

    public double getSyrupStock() {
        return syrupStock;
    }

    public void setSyrupStock(double syrup) {
        this.syrupStock = syrup;
    }

    public double getIceStock() {
        return iceStock;
    }

    public void setIceStock(double ice) {
        this.iceStock = ice;
    }

    public double[] getIngredientsList() {
        double[] arr = {espressoStock, waterStock, milkStock, syrupStock, iceStock};
        return arr;
    }

    public String displayIngredientStock() {
        return "\n*** Ingredient Stock ***" + "\nEspresso: " + espressoStock + "\nWater: " + waterStock + "\nMilk: " + milkStock + "\nSyrup: " + syrupStock + "\nIce: " + iceStock + "\n";
    }
}
