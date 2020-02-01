package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String action = "";
        Machine machine = new Machine(400, 540, 120, 9, 550,
                                        new int[]{250, 0, 16, 4},
                                        new int[]{350, 75, 20, 7},
                                        new int[]{200, 100, 12, 6});

        while(!action.contentEquals("exit")) {
            action = !action.contentEquals("") ? "" : action;
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = sc.nextLine();

            if(action.contentEquals("buy")) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String mode = sc.nextLine();
                machine.buy(mode);
            } else if(action.contentEquals("fill")) {
                machine.fill();
            } else if(action.contentEquals("take")) {
                machine.take();
            } else if(action.contentEquals("remaining")) {
                machine.remaining();
            }
        }
    }
}

/*enum CoffeeMachineStates {
    BUY("buy"),
    FILL("fill"),
    TAKE("take"),
    REMAINING("remaining"),
    EXIT("exit");

    String action;

    CoffeeMachineStates(String action) {
        this.action = action;
    }
}*/

class Machine {
    private int initWater;
    private int initMilk;
    private int initBeans;
    private int initCups;
    private int initMoney;
    private int[] esspressoProps;
    private int[] latteProps;
    private int[] cappucinoProps;

    Machine(int water, int milk, int beans, int cups, int money, int[] esspresso, int[] latte, int[] cappucino) {
        this.initWater = water;
        this.initMilk = milk;
        this.initBeans = beans;
        this.initCups = cups;
        this.initMoney = money;
        this.esspressoProps = esspresso;
        this.latteProps = latte;
        this.cappucinoProps = cappucino;
    }

    public void buy(String mode) {
        if(!mode.contentEquals("back")) {
            int water = 0;
            int milk = 0;
            int beans = 0;
            int money = 0;

            switch (mode) {
                case "1":
                    water = esspressoProps[0];
                    beans = esspressoProps[2];
                    money = esspressoProps[3];
                    break;
                case "2":
                    water = latteProps[0];
                    milk = latteProps[1];
                    beans = latteProps[2];
                    money = latteProps[3];
                    break;
                case "3":
                    water = cappucinoProps[0];
                    milk = cappucinoProps[1];
                    beans = cappucinoProps[2];
                    money = cappucinoProps[3];
                    break;
            }

            if(this.initWater - water < 0) {
                System.out.println("Sorry, not enough water!");
            } else if(this.initMilk - milk < 0) {
                System.out.println("Sorry, not enough milk!");
            } else if(this.initBeans - beans < 0) {
                System.out.println("Sorry, not enough beans!");
            } else {
                System.out.println("I have enough resources, making you a coffee!");
                this.initWater -= water;
                this.initMilk -= milk;
                this.initBeans -= beans;
                this.initMoney += money;
                this.initCups--;
            }
        }
    }

    public void fill() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Write how many ml of water do you want to add:");
        this.initWater += sc.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        this.initMilk += sc.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.initBeans += sc.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.initCups += sc.nextInt();
    }

    public void take() {
        System.out.println("I gave you $" + this.initMoney);
        this.initMoney = 0;
    }

    public void remaining() {
        showSetupWindow(initWater, initMilk, initBeans, initCups, initMoney);
    }

    public void showSetupWindow(int water, int milk, int beans, int cups, int money) {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }
}
