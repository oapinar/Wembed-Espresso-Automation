package com.wembed;

import java.util.Random;
import java.util.Scanner;

public class FinalApp {
    private Menu menu;
    private IngredientStock ingredientStock;
    private Scanner scanner;
    private Random random = new Random();
    private DetailCleaning detailCleaning;

    public FinalApp() {
        detailCleaning = new DetailCleaning(2000, 10);
        ingredientStock = new IngredientStock();
        menu = new Menu("JavaBeans Café", ingredientStock);
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n*** JavaBeans Café ***");
            System.out.println("1. Display Menu");
            System.out.println("2. Get Coffee");
            System.out.println("3. Machine Settings");
            System.out.println("4. Reset and Turn Off");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    menu.displayMenu();
                    break;
                case 2:
                    getCoffee();
                    break;
                case 3:
                    machineSettings();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            generateRandomWarning();
        }
    }

    private void machineSettings() {
        boolean back = false;
        while (!back) {
            System.out.println("\n*** Machine Settings ***");
            System.out.println("1. Display Ingredient Stocks");
            System.out.println("2. Update Stocks");
            System.out.println("3. Add Coffee");
            System.out.println("4. Detail Cleaning");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(ingredientStock.displayIngredientStock());
                    break;
                case 2:
                    updateStocks();
                    break;
                case 3:
                    addCoffeeToMenu();
                    break;
                case 4:
                    detailCleaning.performDetailCleaning();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void generateRandomWarning() {
        if (random.nextDouble() < 0.1) {
            Danger danger = Danger.values()[random.nextInt(Danger.values().length)];
            warn(danger);
        }
    }

    private void warn(Danger danger) {
        switch (danger) {
            case NONE:
                break;
            case FIRE:
                System.out.println("\nWarning: FIRE detected! \nPlease UNPLUG the Machine!");
                break;
            case ELECTRIC_LEAK:
                System.out.println("\nWarning: ELECTRIC LEAK detected! \nPlease UNPLUG the Machine!");
                break;
            case WATER_LEAK:
                System.out.println("\nWarning: WATER LEAK detected! \nPlease UNPLUG the Machine!");
                break;
        }
    }

    private void getCoffee() {
        menu.displayMenu();
        System.out.println("Enter the coffee number or type 'cancel' to return to the main menu:");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("cancel")) {
            return;
        }

        try {
            int coffeeNumber = Integer.parseInt(input);
            if (coffeeNumber > 0 && coffeeNumber <= menu.getMenuSize()) {
                Coffee selectedCoffee = menu.getCoffeeAtIndex(coffeeNumber - 1);
                menu.getCoffee(selectedCoffee);
            } else {
                System.out.println("Invalid coffee number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'cancel'.");
        }
    }

    private void addCoffeeToMenu() {

        System.out.print("Enter coffee name (or type 'cancel' to return): ");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("cancel")) {
            return;
        }

        double espresso = getDoubleInput("Enter espresso amount in grams (or type 'cancel' to return): ");
        if (espresso == -1) return;

        double water = getDoubleInput("Enter water amount in ml (or type 'cancel' to return): ");
        if (water == -1) return;

        double milk = getDoubleInput("Enter milk amount in ml (or type 'cancel' to return): ");
        if (milk == -1) return;

        double syrup = getDoubleInput("Enter syrup amount in ml (or type 'cancel' to return): ");
        if (syrup == -1) return;

        System.out.print("Enter syrup name (or type 'cancel' to return): ");
        String syrupName = scanner.nextLine();
        if (syrupName.equalsIgnoreCase("cancel")) {
            return;
        }

        double ice = getDoubleInput("Enter ice amount in grams (or type 'cancel' to return): ");
        if (ice == -1) return;

        int waterTemp = getIntInput("Enter water temperature in Celsius (or type 'cancel' to return): ");
        if (waterTemp == -1) return;

        int milkTemp = getIntInput("Enter milk temperature in Celsius (or type 'cancel' to return): ");
        if (milkTemp == -1) return;

        System.out.print("Steam milk? (yes/no or type 'cancel' to return): ");
        String steamMilkInput = scanner.nextLine();
        if (steamMilkInput.equalsIgnoreCase("cancel")) {
            return;
        }
        boolean steamMilk = steamMilkInput.trim().equalsIgnoreCase("yes");

        Coffee newCoffee = new Coffee(name, espresso, water, milk, syrup, syrupName, ice, waterTemp, milkTemp, steamMilk);
        menu.addCoffee(newCoffee);
        System.out.println("New coffee added: " + name);
    }

    private double getDoubleInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("cancel")) {
                return -1;
            }

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private int getIntInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("cancel")) {
                return -1;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }
    }

    private void updateStocks() {
        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println(ingredientStock.displayIngredientStock());
            System.out.println("\n*** Update Ingredient Stocks ***");
            System.out.println("1. Update Espresso Stock");
            System.out.println("2. Update Water Stock");
            System.out.println("3. Update Milk Stock");
            System.out.println("4. Update Syrup Stock");
            System.out.println("5. Update Ice Stock");
            System.out.println("6. Cancel and Return to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateSpecificStock("Espresso");
                    break;
                case 2:
                    updateSpecificStock("Water");
                    break;
                case 3:
                    updateSpecificStock("Milk");
                    break;
                case 4:
                    updateSpecificStock("Syrup");
                    break;
                case 5:
                    updateSpecificStock("Ice");
                    break;
                case 6:
                    returnToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void updateSpecificStock(String stockType) {
        scanner.nextLine();
        System.out.print("Enter new " + stockType + " stock (or type 'cancel' to return): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("cancel")) {
            return;
        }

        double newStock;
        try {
            newStock = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
            return;
        }

        switch (stockType) {
            case "Espresso":
                ingredientStock.setEspressoStock(newStock);
                break;
            case "Water":
                ingredientStock.setWaterStock(newStock);
                break;
            case "Milk":
                ingredientStock.setMilkStock(newStock);
                break;
            case "Syrup":
                ingredientStock.setSyrupStock(newStock);
                break;
            case "Ice":
                ingredientStock.setIceStock(newStock);
                break;
            default:
                System.out.println("Invalid stock type.");
                return;
        }

        System.out.println(stockType + " stock updated successfully!");
    }

    public static void main(String[] args) {
        FinalApp app = new FinalApp();
        app.start();
    }
}