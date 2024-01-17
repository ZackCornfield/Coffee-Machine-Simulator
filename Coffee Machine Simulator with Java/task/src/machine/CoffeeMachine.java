package machine;

import java.util.ArrayList;

public class CoffeeMachine {
    private int availableWater;
    private int availableMilk;
    private int availableBeans;
    private int money;
    private int disposableCups;
    private ArrayList<CoffeeType> coffeeTypes;
    private State currentState;

    public CoffeeMachine(int water, int milk, int coffee, int cups, int money) {
        this.availableMilk = milk;
        this.availableWater = water;
        this.availableBeans = coffee;
        this.money = money;
        this.disposableCups = cups;
        this.currentState = State.MAIN_MENU;
        initCoffeeTypes();
        setMainState();
    }

    private void initCoffeeTypes() {
        coffeeTypes = new ArrayList<>();
        coffeeTypes.add(new CoffeeType(1, "espresso", 250, 0, 16, 4));
        coffeeTypes.add(new CoffeeType(2, "latte", 350, 75, 20, 7));
        coffeeTypes.add(new CoffeeType(3, "cappuccino", 200, 100, 12, 6));
    }

    private void setMainState() {
        currentState = State.MAIN_MENU;
        System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
    }

    public void processInput(String input) {
        switch (currentState) {
            case MAIN_MENU:
                setState(input);
                break;
            case BUYING:
                processBuying(input);
                setMainState();
                break;
            case FILLING_SUPPLIES_WATER:
                fillSuppliesWater(input);
                System.out.println("Write how many ml of milk you want to add:");
                currentState = State.FILLING_SUPPLIES_MILK;
                break;
            case FILLING_SUPPLIES_MILK:
                fillSuppliesMilk(input);
                System.out.println("Write how many grams of coffee beans you want to add:");
                currentState = State.FILLING_SUPPLIES_COFFEE;
                break;
            case FILLING_SUPPLIES_COFFEE:
                fillSuppliesCoffee(input);
                System.out.print("Write how many disposable cups of coffee do you want to add:\n> ");
                currentState = State.FILLING_SUPPLIES_CUPS;
                break;
            case FILLING_SUPPLIES_CUPS:
                fillSuppliesCups(input);
                setMainState();
                break;
            default:
                System.out.println("Invalid state.");
        }
    }

    private void setState(String input) {
        switch (input) {
            case "buy":
                currentState = State.BUYING;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case "fill":
                currentState = State.FILLING_SUPPLIES_WATER;
                break;
            case "take":
                takeMoney();
                setMainState();
                break;
            case "remaining":
                showStatus();
                setMainState();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid action. Please try again.");
        }
    }

    private void processBuying(String input) {
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= coffeeTypes.size()) {
                makeCoffee(choice);
            } else {
                System.out.println("Invalid coffee choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        currentState = State.MAIN_MENU;
    }

    private void fillSuppliesWater(String input) {
        int waterToAdd = parseInput(input);
        availableWater += waterToAdd;
    }

    private void fillSuppliesMilk(String input) {
        int milkToAdd = parseInput(input);
        availableMilk += milkToAdd;
    }

    private void fillSuppliesCoffee(String input) {
        int coffeeToAdd = parseInput(input);
        availableBeans += coffeeToAdd;
    }

    private void fillSuppliesCups(String input) {
        int cupsToAdd = parseInput(input);
        disposableCups += cupsToAdd;
    }

    private int parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return 0;
        }
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void makeCoffee(int choice) {
        CoffeeType selectedCoffeeType = coffeeTypes.get(choice - 1);
        if (checkResources(selectedCoffeeType)) {
            System.out.println("I have enough resources, making you a coffee!");
            updateResources(selectedCoffeeType);
        } else {
            System.out.println("Not enough resources to make coffee!");
        }
    }

    private boolean checkResources(CoffeeType coffeeType) {
        return availableWater >= coffeeType.getWater() &&
                availableMilk >= coffeeType.getMilk() &&
                availableBeans >= coffeeType.getCoffee() &&
                disposableCups > 0;
    }

    private void updateResources(CoffeeType coffeeType) {
        availableWater -= coffeeType.getWater();
        availableMilk -= coffeeType.getMilk();
        availableBeans -= coffeeType.getCoffee();
        disposableCups--;
        money += coffeeType.getCost();
    }

    private void showStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(availableWater + " ml of water");
        System.out.println(availableMilk + " ml of milk");
        System.out.println(availableBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }

    public enum State {
        MAIN_MENU,
        BUYING,
        FILLING_SUPPLIES_WATER,
        FILLING_SUPPLIES_MILK,
        FILLING_SUPPLIES_COFFEE,
        FILLING_SUPPLIES_CUPS
    }
}
