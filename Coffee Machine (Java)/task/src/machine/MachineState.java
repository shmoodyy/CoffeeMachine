package machine;

import java.util.Scanner;

class MachineState {

    enum IngredientsNeeded {

        ESPRESSO(250, 0 ,16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        final int WATER_NEEDED, MILK_NEEDED, BEANS_NEEDED, MONEY_GENERATED;

        IngredientsNeeded(int waterNeeded, int milkNeeded, int beansNeeded, int moneyGenerated) {
            this.WATER_NEEDED = waterNeeded;
            this.MILK_NEEDED = milkNeeded;
            this.BEANS_NEEDED = beansNeeded;
            this.MONEY_GENERATED = moneyGenerated;
        }
    }

    static boolean isExit = false;
    static int waterInventory = 400;
    static int milkInventory = 540;
    static int beansInventory = 120;
    static int cupInventory = 9;
    static int moneyInventory = 550;
    static final Scanner scanner = new Scanner(System.in);

    static void mainMenu() {
        while (!isExit) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String actionChosen = scanner.next().toLowerCase();
            switch (actionChosen) {
                case "buy" -> servings(Barista.buy());
                case "fill" -> Barista.fill();
                case "take" -> Barista.take();
                case "remaining" -> Barista.remaining();
                case "exit" -> isExit = true;
            }
        }
    }

    static void servings(IngredientsNeeded drinkChosen) {
        try {
            boolean enoughWater = waterInventory >= drinkChosen.WATER_NEEDED;
            boolean enoughMilk = milkInventory >= drinkChosen.MILK_NEEDED;
            boolean enoughBeans = beansInventory >= drinkChosen.BEANS_NEEDED;
            boolean isDoable = (drinkChosen.name().equals("ESPRESSO") && enoughWater && enoughBeans)
                    || (enoughWater && enoughMilk && enoughBeans && cupInventory >= 1);
            String missingIngredient = !enoughWater ? "water" : !enoughMilk ? "milk" : !enoughBeans ? "coffee beans" : "";
            if (isDoable) {
                waterInventory -= drinkChosen.WATER_NEEDED;
                milkInventory -= drinkChosen.MILK_NEEDED;
                beansInventory -= drinkChosen.BEANS_NEEDED;
                cupInventory--;
                moneyInventory += drinkChosen.MONEY_GENERATED;
                System.out.println("I have enough resources, making you a coffee!\n");
            } else {
                System.out.printf("Sorry, not enough %s!%n%n", missingIngredient);
            }
        } catch (NullPointerException ignored) {}
    }
}