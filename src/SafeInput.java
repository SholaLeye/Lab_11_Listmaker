import java.util.Scanner;
import java.util.regex.Pattern;

public class SafeInput {

    // Gets a non-empty string from the user (no zero-length)
    public static String getNonZeroLenString(Scanner scanner, String prompt) {
        String input = "";
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.length() == 0) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.length() == 0);
        return input;
    }

    // Gets a string matching the regex pattern from the user
    public static String getRegExString(Scanner scanner, String prompt, String pattern) {
        String input;
        Pattern p = Pattern.compile(pattern);
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!p.matcher(input).matches()) {
                System.out.println("Input does not match required pattern. Please try again.");
            } else {
                return input;
            }
        } while (true);
    }

    // Gets an integer within a specified range from the user
    public static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        int input = 0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (input >= low && input <= high) {
                    valid = true;
                } else {
                    System.out.println("Input must be between " + low + " and " + high + ". Try again.");
                }
            } else {
                System.out.println("Invalid integer input. Try again.");
                scanner.nextLine(); // consume invalid input
            }
        } while (!valid);
        return input;
    }

    // Gets a yes/no confirmation from the user, returns true for yes, false for no
    public static boolean getYNConfirm(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt + " [Y/N]: ");
            input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("YES")) {
                return true;
            } else if (input.equals("N") || input.equals("NO")) {
                return false;
            } else {
                System.out.println("Please enter Y (yes) or N (no).");
            }
        } while (true);
    }
}
