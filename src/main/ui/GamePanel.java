package ui;

import model.Troop;
import model.Warrior;
import model.World;

import java.util.ArrayList;
import java.util.Scanner;

public class GamePanel {
    private final Scanner input;
    private final World world;

    public GamePanel() {
        world = new World();
        input = new Scanner(System.in);
        selectFromMenu();
    }

    private void selectFromMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nMain menu:");
            System.out.println("\tt -> troops");
            System.out.println("\tq -> quit");
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            if (keyIn.equals("t")) {
                editTroops();
            } else if (keyIn.equals("q")) {
                quit = true;
            } else {
                System.out.println("Selection not valid...");
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void editTroops() {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nSelect from:");
            System.out.println("\te -> edit troops");
            System.out.println("\tl -> list troops");
            System.out.println("\tq -> quit");
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            switch (keyIn) {
                case "e":
                    selectTroops();
                    break;
                case "l":
                    listTroops();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // EFFECTS: prompts user to select chequing or savings account and returns it
    private void selectTroops() {
        int selection;
        boolean quit = false;
        int index;

        while (!quit) {
            index = 0;
            System.out.println("Troops available:\n");
            for (; index < world.getWorldSize(); index++) {
                System.out.println("\t" + (index + 1) + " -> " + world.getTroopByIndex(index).getRace());
            }
            System.out.println("index of troop you want to edit:\n");
            selection = input.nextInt();
            if (selection > 0 && selection <= world.getWorldSize()) {
                quit = true;
                editTroop(world.getTroopByIndex(selection - 1));
            }
            if (!quit) {
                System.out.println("Invalid troop index...\n");
            }
        }
    }

    private void listTroops() {
        String selection;
        boolean quit = false;

        System.out.println("Troops available:\n");
        for (int index = 0; index < world.getWorldSize(); index++) {
            System.out.println("\t" + world.getTroopByIndex(index).getRace());
        }
        while (!quit) {
            System.out.println("press \"q\" to quit.\n");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("q")) {
                quit = true;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }

    }

    private void editTroop(Troop troop) {
        boolean quit = false;

        while (!quit) {
            displayEditingTroop(troop);
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            switch (keyIn) {
                case "a":
                    addWarrior(troop);
                    break;
                case "e":
                    selectWarrior(troop);
                    break;
                case "l":
                    listWarriors(troop);
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    private void displayEditingTroop(Troop troop) {
        System.out.println("\nEditing: " + troop.getRace());
        System.out.println("\ta -> add a " + troop.getRace() + " warrior");
        System.out.println("\te -> edit " + troop.getRace() + " warriors");
        System.out.println("\tl -> list " + troop.getRace() + " warriors");
        System.out.println("\tq -> quit");
    }

    private void selectWarrior(Troop troop) {
        int selection;
        boolean quit = false;
        int index;

        while (!quit) {
            index = 0;
            System.out.println("Warriors available:\n");
            for (; index < troop.getTroopSize(); index++) {
                System.out.println("\t" + (index + 1) + " -> " + troop.getWarriorByIndex(index).getName());
            }
            System.out.println("index of the warrior you want to edit:\n");
            selection = input.nextInt();
            if (selection > 0 && selection <= troop.getTroopSize()) {
                quit = true;
                editWarrior(troop.getWarriorByIndex(selection - 1));
            }
            if (!quit) {
                System.out.println("Invalid warrior index...\n");
            }
        }
    }

    private void editWarrior(Warrior warrior) {
        boolean quit = false;

        while (!quit) {
            displayEditingWarrior(warrior);
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            switch (keyIn) {
                case "r":
                    renameWarrior(warrior);
                    break;
                case "a":
                    editWarriorAttack(warrior);
                    break;
                case "d":
                    editWarriorDefense(warrior);
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    private void addWarrior(Troop troop) {
        Warrior newWarrior;
        String name;

        System.out.println("New warrior's name:");
        name = input.next();
        newWarrior = new Warrior(name);
        troop.addWarrior(newWarrior);
        System.out.println("Added a " + newWarrior.getName() + " to " + troop.getRace());
        editWarriorAttack(newWarrior);
        editWarriorDefense(newWarrior);
    }

    private void listWarriors(Troop troop) {
        String selection;
        boolean quit = false;

        System.out.println("Warriors available:\n");
        for (int index = 0; index < troop.getTroopSize(); index++) {
            System.out.println("\t" + troop.getWarriorByIndex(index).getName());
        }
        while (!quit) {
            System.out.println("press \"q\" to quit.\n");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("q")) {
                quit = true;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }
    }

    private void renameWarrior(Warrior warrior) {
        System.out.println("Warrior's new name: ");
        String selection = input.next();
        warrior.rename(selection);
        System.out.println("Warrior renamed to " + warrior.getName());
    }

    private void editWarriorAttack(Warrior warrior) {
        boolean quit = false;
        int attack;

        while (!quit) {
            System.out.println("Warrior's attack: ");
            attack = input.nextInt();
            if (attack > 0) {
                warrior.setAttack(attack);
                quit = true;
            } else {
                System.out.println("enter a positive value\n");
            }
        }
        System.out.println("Warrior's attack changed to " + warrior.getAttack());
    }

    private void editWarriorDefense(Warrior warrior) {
        boolean quit = false;
        int defense;

        while (!quit) {
            System.out.println("Warrior's defense: ");
            defense = input.nextInt();
            if (defense > 0) {
                warrior.setDefense(defense);
                quit = true;
            } else {
                System.out.println("enter a positive value\n");
            }
        }
        System.out.println("Warrior's defense changed to " + warrior.getAttack());
    }

    private void displayEditingWarrior(Warrior warrior) {
        System.out.println("\nEditing: " + warrior.getName());
        System.out.println("\tattack: " + warrior.getAttack());
        System.out.println("\tdefense: " + warrior.getDefense());
        System.out.println("\n\tr -> rename the warrior");
        System.out.println("\ta -> edit attack ");
        System.out.println("\td -> edit defense ");
        System.out.println("\tq -> quit");
    }

}
