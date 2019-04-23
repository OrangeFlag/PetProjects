package coffee;

public class CappuccinoRecipe implements CoffeeRecipe {
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

    private class Cappuccino implements Coffee {
        @Override
        public String toString() {
            return "Cappuccino";
        }
    }

    @Override
    public Coffee getCoffee() {
        return new Cappuccino();
    }

    @Override
    public String toString() {
        return "Cappuccino recipe";
    }
}
