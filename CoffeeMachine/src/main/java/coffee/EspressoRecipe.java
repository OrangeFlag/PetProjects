package coffee;

public class EspressoRecipe implements CoffeeRecipe {
    @Override
    public int howMuchWater() {
        return 0;
    }

    @Override
    public int howMuchMilk() {
        return 0;
    }

    @Override
    public int howMuchCoffeeBeans() {
        return 0;
    }

    @Override
    public int howMuchDisposableCups() {
        return 0;
    }

    @Override
    public int howMuchMoney() {
        return 0;
    }

    private class Espresso implements Coffee {
        @Override
        public String toString() {
            return "Espresso";
        }
    }

    @Override
    public Coffee getCoffee() {
        return new Espresso();
    }

    @Override
    public String toString() {
        return "Espresso recipe";
    }
}
