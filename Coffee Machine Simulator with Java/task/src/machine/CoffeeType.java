package machine;

public class CoffeeType {
    private int id;
    private String name;
    private int water;
    private int milk;
    private int coffee;
    private int cost;

    public CoffeeType(int id, String name, int water, int milk, int coffee, int cost) {
        this.id = id;
        this.name = name;
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public CoffeeType setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoffeeType setName(String name) {
        this.name = name;
        return this;
    }

    public int getWater() {
        return water;
    }

    public CoffeeType setWater(int water) {
        this.water = water;
        return this;
    }

    public int getMilk() {
        return milk;
    }

    public CoffeeType setMilk(int milk) {
        this.milk = milk;
        return this;
    }

    public int getCoffee() {
        return coffee;
    }

    public CoffeeType setCoffee(int coffee) {
        this.coffee = coffee;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public CoffeeType setCost(int cost) {
        this.cost = cost;
        return this;
    }
}