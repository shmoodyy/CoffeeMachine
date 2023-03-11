package machine;

class Barista extends MachineState {

    Barista() {
        mainMenu();
    }

    static IngredientsNeeded buy() {
        IngredientsNeeded drink = null;
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String buyOption = scanner.next().toUpperCase();
        if (buyOption.equalsIgnoreCase("back")) {
            System.out.println();
            mainMenu();
        }
        try {
            int coffeeChosen = Integer.parseInt(buyOption);
            switch (coffeeChosen) {
                case 1 -> drink = IngredientsNeeded.ESPRESSO;
                case 2 -> drink = IngredientsNeeded.LATTE;
                case 3 -> drink = IngredientsNeeded.CAPPUCCINO;
            }
        } catch (NumberFormatException ignored) {}
        return drink;
    }

    static void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        waterInventory += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        milkInventory += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        beansInventory += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add: ");
        cupInventory += scanner.nextInt();
        System.out.println();
    }

    static void take() {
        System.out.printf("%nI gave you $%d%n%n", moneyInventory);
        moneyInventory -= moneyInventory;
        System.out.println();
    }

    static void remaining() {
        System.out.printf("%nThe coffee machine has:%n%d ml of water%n%d ml of milk%n%d g of coffee beans" +
                        "%n%d disposable cups%n$%d of money%n%n", waterInventory, milkInventory, beansInventory
                , cupInventory, moneyInventory);
    }
}