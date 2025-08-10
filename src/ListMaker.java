import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayListAndMenu();
            // Get command input validated by regex (accepts upper or lower case)
            String choice = SafeInput.getRegExString(console, "Enter command [A|D|I|P|Q]: ", "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    running = !quitProgram(); // quitProgram returns true if confirmed to quit
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
        System.out.println("Program exited. Goodbye!");
    }

    // Step 3: Utility method to display list with numbering and menu options
    private static void displayListAndMenu() {
        System.out.println("\nCurrent List:");
        if (list.isEmpty()) {
            System.out.println("[The list is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ": " + list.get(i));
            }
        }
        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
    }

    // Step 3a: Add an item to the end of the list
    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(console, "Enter the item to add: ");
        list.add(item);
        System.out.println("Added \"" + item + "\" to the list.");
    }

    // Step 3b: Delete an item by number with validation
    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }
        int itemNum = SafeInput.getRangedInt(console, "Enter the item number to delete (1-" + list.size() + "): ", 1, list.size());
        String removed = list.remove(itemNum - 1);
        System.out.println("Deleted \"" + removed + "\" from the list.");
    }

    // Step 3c: Insert an item at a specified position
    private static void insertItem() {
        int position;
        if (list.isEmpty()) {
            System.out.println("List is empty. Insert will act like add.");
            position = 1;
        } else {
            position = SafeInput.getRangedInt(console, "Enter the position to insert at (1-" + (list.size() + 1) + "): ", 1, list.size() + 1);
        }
        String item = SafeInput.getNonZeroLenString(console, "Enter the item to insert: ");
        list.add(position - 1, item);
        System.out.println("Inserted \"" + item + "\" at position " + position + ".");
    }

    // Step 3d: Print the current list with numbering
    private static void printList() {
        System.out.println("\nCurrent List:");
        if (list.isEmpty()) {
            System.out.println("[The list is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ": " + list.get(i));
            }
        }
    }

    // Step 3e: Quit program with confirmation
    private static boolean quitProgram() {
        return SafeInput.getYNConfirm(console, "Are you sure you want to quit?");
    }
}
