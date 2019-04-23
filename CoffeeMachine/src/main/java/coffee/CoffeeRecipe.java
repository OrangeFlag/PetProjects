package coffee;

public interface CoffeeRecipe {
    public interface Coffee {
    }

    public int howMuchWater();

    public int howMuchMilk();

    public int howMuchCoffeeBeans();

    public int howMuchDisposableCups();

    public int howMuchMoney();

    public Coffee getCoffee();
}
