package machine;
import java.util.Scanner;
public class CoffeeMachine {
    private static Scanner scan = new Scanner(System.in);
    public static final int waterPerCupEspresso = 250;
    public static final int milkPerCupEspresso = 0;
    public static final int beansPerCupEspresso = 16;
    public static final int waterPerCupLatte = 350;
    public static final int milkPerCupLatte = 75;
    public static final int beansPerCupLatte = 20;
    public static final int waterPerCupCappuccino = 200;
    public static final int milkPerCupCappuccino = 100;
    public static final int beansPerCupCappuccino = 12;
    private static int money = 550;
    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    public static final int espresso = 4;
    public static final int latte = 7;
    public static final int cappuccino = 6;

    private static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String order = scan.next();
        switch (order) {
            case "1":
                if(water >= waterPerCupEspresso && milk >= milkPerCupEspresso && beans >= beansPerCupEspresso && cups >= 1) {
                    money += espresso;
                    water -= waterPerCupEspresso;
                    milk -= milkPerCupEspresso;
                    beans -= beansPerCupEspresso;
                    cups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < waterPerCupEspresso) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < milkPerCupEspresso) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < beansPerCupEspresso) {
                    System.out.println("Sorry, not enough coffee beans");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough cups");
                }
                break;
            case "2":
                if(water >= waterPerCupLatte && milk >= milkPerCupLatte && beans >= beansPerCupLatte && cups >= 1) {
                    money += latte;
                    water -= waterPerCupLatte;
                    milk -= milkPerCupLatte;
                    beans -= beansPerCupLatte;
                    cups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < waterPerCupLatte) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < milkPerCupLatte) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < beansPerCupLatte) {
                    System.out.println("Sorry, not enough coffee beans");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough cups");
                }
                break;
            case "3":
                if(water >= waterPerCupCappuccino && milk >= milkPerCupCappuccino && beans >= beansPerCupCappuccino && cups >= 1) {
                    money += cappuccino;
                    water -= waterPerCupCappuccino;
                    milk -= milkPerCupCappuccino;
                    beans -= beansPerCupCappuccino;
                    cups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < waterPerCupCappuccino) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < milkPerCupCappuccino) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < beansPerCupCappuccino) {
                    System.out.println("Sorry, not enough coffee beans");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough cups");
                }
                break;
            default:
                break;
        }
    }

    private static void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        water += scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add");
        beans += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scan.nextInt();
    }

    private static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private static void display() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (exit == false) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String operation = scan.next();
            switch (operation) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    display();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }
}