package com.wembed;

import java.util.LinkedList;

public class Menu {
    private String cafeName;
    private LinkedList<Coffee> menu;
    private IngredientStock ingredientStock;
    private Coffee singleEspresso = new Coffee("Single Espresso", 5, 100, 0, 0, "", 0, 75, 0, false);
    private Coffee latte = new Coffee("Hazelnut Latte", 10, 100, 300,  10, "hazelnut", 0, 75, 60, true);
    private Coffee mocha = new Coffee("Mocha", 10, 200, 200, 20, "chocolate", 0, 75, 60, false);

    public Menu(String cafeName, IngredientStock ingredientStock) {
        this.cafeName = cafeName;
        menu = new LinkedList<>();
        this.ingredientStock = ingredientStock;
        menu.add(singleEspresso);
        menu.add(latte);
        menu.add(mocha);
    }

    public void addCoffee(Coffee coffee) {
        menu.add(coffee);
    }
    public void displayMenu() {
        System.out.println("\n*** " + cafeName + " Menu ***");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getName());
        }
    }

    public int getMenuSize() {
        return menu.size();
    }

    public Coffee getCoffeeAtIndex(int index) {
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        } else {
            System.out.println("Invalid coffee index.");
            return null;
        }
    }
    public void getCoffee(Coffee coffee) {
        if (menu.isEmpty()) {
            System.out.println("\nMenu is empty now, you need to prepare the menu.");
        } else {
            if (!coffee.checkIngredients(ingredientStock.getIngredientsList())) {
                System.out.println("\nPlease check which ingredient is missing and refill to get your coffee.");
            } else {
                coffee.makeCoffee(coffee);
                ingredientStock.setEspressoStock(ingredientStock.getEspressoStock() - coffee.getEspresso());
                ingredientStock.setWaterStock(ingredientStock.getWaterStock() - coffee.getWater());
                ingredientStock.setMilkStock(ingredientStock.getMilkStock() - coffee.getMilk());
                ingredientStock.setSyrupStock(ingredientStock.getSyrupStock() - coffee.getSyrup());
                ingredientStock.setIceStock(ingredientStock.getIceStock() - coffee.getIce());
            }
        }
    }
}
