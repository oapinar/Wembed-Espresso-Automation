package com.wembed;

public class Coffee {
    private String name;
    private double espresso;
    private double water;
    private double milk;
    private double syrup;
    private String syrupName;
    private double ice;
    private int waterTemp;
    private int milkTemp;
    private boolean steamMilk;

    public Coffee(String name, double espresso, double water, double milk, double syrup, String syrupName, double ice, int waterTemp, int milkTemp, boolean steamMilk) {
        this.name = name;
        this.espresso = espresso;
        this.water = water;
        this.milk = milk;
        this.syrup = syrup;
        this.syrupName = syrupName;
        this.ice = ice;
        this.waterTemp = waterTemp;
        this.milkTemp = milkTemp;
        this.steamMilk = steamMilk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEspresso() {
        return espresso;
    }

    public void setEspresso(double espresso) {
        this.espresso = espresso;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getMilk() {
        return milk;
    }

    public void setMilk(double milk) {
        this.milk = milk;
    }

    public double getSyrup() {
        return syrup;
    }

    public void setSyrup(double syrup) {
        this.syrup = syrup;
    }

    public String getSyrupName() {
        return syrupName;
    }

    public void setSyrupName(String syrupName) {
        this.syrupName = syrupName;
    }

    public double getIce() {
        return ice;
    }

    public void setIce(double ice) {
        this.ice = ice;
    }

    public int getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(int waterTemp) {
        this.waterTemp = waterTemp;
    }

    public int getMilkTemp() {
        return milkTemp;
    }

    public void setMilkTemp(int milkTemp) {
        this.milkTemp = milkTemp;
    }

    public boolean isSteamMilk() {
        return steamMilk;
    }

    public void setSteamMilk(boolean steamMilk) {
        this.steamMilk = steamMilk;
    }

    public double getAmount(String ingredientName) {
        switch (ingredientName.toLowerCase()) {
            case "espresso":
                return espresso;
            case "water":
                return water;
            case "milk":
                return milk;
            case "syrup":
                return syrup;
            case "ice":
                return ice;
            default:
                return 0;
        }
    }
    public void makeCoffee(Coffee coffee) {
        String a = "\n" + "Your " + name + " will be served soon...";
        String b;
        String c;
        String d = "";

        if (ice > 0) {
            b = "\n" + ice + " ice is being added to the cup.";
        } else {
            b = "";
        }

        if (syrup > 0) {
            c = "\n" + syrup + " ml " + syrupName + " syrup is being poured to the cup.";
        } else {
            c = "";
        }

        if (milk > 0) {
            if (steamMilk) {
                d = "\n" + milkTemp + " celcius " + milk + " ml milk is being whisked and poured to the cup.";
            } else {
                d = "\n" + milkTemp + " celcius " + milk + " ml milk is being poured to the cup.";
            }
        }

        String e = "\n" + espresso + " gr espresso is being brewed.";

        String f = "\n" + "Espresso is being poured into the cup with " + waterTemp + " celcius " + water + " ml water.";

        String g = "\n" + "Your " + name + " is ready!";

        System.out.println(a + b + c + d + e + f + g);
    }

    public boolean checkIngredients(double[] ingredientStocks) {
        if (ingredientStocks[0] < espresso) return false;
        if (ingredientStocks[1] < water) return false;
        if (ingredientStocks[2] < milk) return false;
        if (ingredientStocks[3] < syrup) return false;
        if (ingredientStocks[4] < ice) return false;

        return true;
    }
}
