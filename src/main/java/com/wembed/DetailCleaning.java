package com.wembed;

public class DetailCleaning {
    private static final int INITIAL_WATER_AMOUNT = 2000;
    private static final int SECONDARY_WATER_AMOUNT = 300;
    private static final double DEGREASER_AMOUNT = 5;

    private int waterTankCapacity;
    private double degreaserStock;

    public DetailCleaning(int initialWaterTankCapacity, double initialDegreaserStock) {
        this.waterTankCapacity = initialWaterTankCapacity;
        this.degreaserStock = initialDegreaserStock;
    }

    public int getWaterTankCapacity() {
        return waterTankCapacity;
    }

    public void setWaterTankCapacity(int waterTankCapacity) {
        this.waterTankCapacity = waterTankCapacity;
    }

    public double getDegreaserStock() {
        return degreaserStock;
    }

    public void setDegreaserStock(double degreaserStock) {
        this.degreaserStock = degreaserStock;
    }

    public void performDetailCleaning() {
        System.out.println("\nDetail cleaning started.");

        if (ensureWaterInTank(INITIAL_WATER_AMOUNT)) {
            pumpWaterThroughMachine(INITIAL_WATER_AMOUNT);
        } else {
            getAndBoilWater(INITIAL_WATER_AMOUNT);
            pumpWaterThroughMachine(INITIAL_WATER_AMOUNT);
        }

        if (ensureWaterInTank(SECONDARY_WATER_AMOUNT)) {
            if (ensureDegreaserInMachine(DEGREASER_AMOUNT)) {
                pumpWaterWithDegreaserThroughMilkPipe(SECONDARY_WATER_AMOUNT, DEGREASER_AMOUNT);
            } else {
                if (waitForDegreaserInsertion()) {
                    pumpWaterWithDegreaserThroughMilkPipe(SECONDARY_WATER_AMOUNT, DEGREASER_AMOUNT);
                } else {
                    System.out.println("Skipping degreaser steps due to timeout.");
                }
            }
        } else {
            getAndBoilWater(SECONDARY_WATER_AMOUNT);
            if (ensureDegreaserInMachine(DEGREASER_AMOUNT)) {
                pumpWaterWithDegreaserThroughMilkPipe(SECONDARY_WATER_AMOUNT, DEGREASER_AMOUNT);
            }
        }

        spraySteamToDripTray();
        System.out.println("Detail cleaning completed.");
    }

    public boolean ensureWaterInTank(int amount) {
        return waterTankCapacity >= amount;
    }

    public void getAndBoilWater(int amount) {
        System.out.println("Getting and boiling " + amount + "ml of water.");
        waterTankCapacity += amount;
    }

    public void pumpWaterThroughMachine(int amount) {
        System.out.println("Pumping " + amount + "ml of hot water through the machine.");
        waterTankCapacity -= amount;
    }

    public boolean ensureDegreaserInMachine(double amount) {
        return degreaserStock >= amount;
    }

    public boolean waitForDegreaserInsertion() {
        System.out.println("Waiting for degreaser insertion...");
        long waitTime = 1 * 3 * 1000;
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < waitTime) {
            if (degreaserStock >= DEGREASER_AMOUNT) {
                return true;
            }
        }
        return false;
    }

    public void pumpWaterWithDegreaserThroughMilkPipe(int waterAmount, double degreaserAmount) {
        System.out.println("Pumping " + waterAmount + "ml of hot water with " + degreaserAmount + "cl degreaser through the milk pipe.");
        degreaserStock -= degreaserAmount;
    }

    public void spraySteamToDripTray() {
        System.out.println("Spraying steam to the drip tray.");
    }
}

