package machine;

import coffee.CappuccinoRecipe;
import coffee.CoffeeRecipe;
import coffee.EspressoRecipe;
import coffee.LatteRecipe;

import java.util.*;

public class CoffeeMachine {
    int water;
    int milk;
    int coffeeBeans;
    int disposableCups;
    int money;
    List<CoffeeRecipe> kindsOfCoffeeRecipe = new ArrayList<>();

    public void addKindOfCoffee(CoffeeRecipe c) {
        if (c != null) {
            kindsOfCoffeeRecipe.add(c);
        }
    }

    public List<CoffeeRecipe> getKindsOfCoffeeRecipe() {
        return new ArrayList<>(kindsOfCoffeeRecipe);
    }

    public CoffeeRecipe.Coffee buy(int idCoffee) {
        if (idCoffee >= kindsOfCoffeeRecipe.size()) {
            return null;
        }
        CoffeeRecipe coffeeRecipe = kindsOfCoffeeRecipe.get(idCoffee);
        if (canMakeCoffeeRecipe(coffeeRecipe)) {
            return coffeeRecipe.getCoffee();
        } else {
            return null;
        }
    }

    private boolean canMakeCoffeeRecipe(CoffeeRecipe coffeeRecipe) {
        if (coffeeRecipe.howMuchWater() > water ||
                coffeeRecipe.howMuchMilk() > milk ||
                coffeeRecipe.howMuchCoffeeBeans() > coffeeBeans ||
                coffeeRecipe.howMuchDisposableCups() > disposableCups) {
            return false;
        } else {
            water -= coffeeRecipe.howMuchWater();
            milk -= coffeeRecipe.howMuchMilk();
            coffeeBeans -= coffeeRecipe.howMuchCoffeeBeans();
            disposableCups -= coffeeRecipe.howMuchDisposableCups();
            return true;
        }
    }

    public void fill(int water, int milk, int coffeeBeans, int disposableCups) {
        this.water += water;
        this.milk += milk;
        this.coffeeBeans += coffeeBeans;
        this.disposableCups += disposableCups;
    }

    public int take() {
        int moneyBuffer = money;
        money = 0;
        return moneyBuffer;
    }

    public Map<String, Integer> remaining() {
        return new HashMap<String, Integer>() {{
            put("water", water);
            put("milk", milk);
            put("coffeeBeans", coffeeBeans);
            put("disposableCups", disposableCups);
            put("money", money);
        }};
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addKindOfCoffee(new EspressoRecipe());
        coffeeMachine.addKindOfCoffee(new LatteRecipe());
        coffeeMachine.addKindOfCoffee(new CappuccinoRecipe());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Write action(buy, fill, take, remaining, exit):");
            String command = scanner.nextLine();
            switch (command) {
                case "buy":
                    System.out.println("What you want to buy?");
                    int index = 0;
                    for (CoffeeRecipe cr : coffeeMachine.getKindsOfCoffeeRecipe()) {
                        System.out.printf("%d - %s\n", index, cr.toString());
                        index += 1;
                    }
                    CoffeeRecipe.Coffee coffee = coffeeMachine.buy(Integer.parseInt(scanner.nextLine())); //ToDO can throw 2 type exception
                    if (coffee != null){
                        System.out.printf("Coffee is ready: %s\n", coffee.toString());
                    } else{
                        System.out.println("Sorry, not enough resources for this recipe");
                    }
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add: ");
                    int water = Integer.parseInt(scanner.nextLine()); //ToDO can throw exception
                    System.out.println("Write how many ml of milk do you want to add: ");
                    int milk = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    int coffeeBeans = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many disposable cups do you want to add: ");
                    int disposableCups = Integer.parseInt(scanner.nextLine());
                    coffeeMachine.fill(water, milk, coffeeBeans, disposableCups);
                    break;
                case "take":
                    System.out.printf("You take %d$. Wait, you're not a thief?\n", coffeeMachine.take());
                    break;
                case "remaining":
                    coffeeMachine.remaining().forEach((k, v) -> System.out.printf("%d of %s\n", v, k));
                    break;
                case "exit":
                    return;
            }
        }
    }
}
