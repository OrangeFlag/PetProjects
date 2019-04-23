package coffee;

public class LatteRecipe implements CoffeeRecipe {
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

    private class Latte implements Coffee {
        @Override
        public String toString() {
            return "Latte";
        }
    }

    @Override
    public Coffee getCoffee() {
        return new Latte();
    }

    @Override
    public String toString() {
        return "Latte recipe";
    }
}
