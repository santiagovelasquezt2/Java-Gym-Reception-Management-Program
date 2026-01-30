package gym;

import java.util.Scanner;

/**
 * GyDemo – Main driver class for the gym management application
 */
public class GyDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create five receptions with test data required by the assignment
        Reception reception0 = new Reception(new GymPasses(10, 4, 1, 1, 1),
                new GymCard[] { new GymCard("Basic", "Scrump", 25, 12),
                        new GymCard("Standard", "Pelekai", 3, 12) });

        Reception reception1 = new Reception(new GymPasses(10, 4, 1, 1, 1),
                new GymCard[] { new GymCard("Premium", "Scrump", 7, 12),
                        new GymCard("Basic", "Jookiba", 24, 8) });

        Reception reception2 = new Reception(new GymPasses(5, 13, 0, 4, 0),
                new GymCard[] { new GymCard("PremiumPlus", "Pleakley", 1, 6),
                        new GymCard("Basic", "Gantu", 18, 12),
                        new GymCard("Standard", "Stitch", 5, 4) });

        Reception reception3 = new Reception(new GymPasses(2, 8, 5, 0, 3), null);
        Reception reception4 = new Reception(new GymPasses(2, 8, 5, 0, 3), null);

        Reception[] allReceptions = { reception0, reception1, reception2, reception3, reception4 };

        // Welcome banner
        System.out.println(
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(
                "| Welcome to Fit Gym @Concordia University Application                                                         |");
        System.out.println(
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        int choice;
        do {
            displayMenu(); // Show available options
            choice = readValidMenuChoice(scanner); // Validate menu input (0-9)

            switch (choice) {
                case 1 -> viewAllReceptions(allReceptions);
                case 2 -> viewOneReception(allReceptions, scanner);
                case 3 -> listSameTotalValue(allReceptions);
                case 4 -> listSamePassTypes(allReceptions);
                case 5 -> listSameValueAndMemberships(allReceptions);
                case 6 -> addMembership(allReceptions, scanner);
                case 7 -> removeMembership(allReceptions, scanner);
                case 8 -> updateExpiry(allReceptions, scanner);
                case 9 -> addPasses(allReceptions, scanner);
                case 0 -> System.out.println("Thank you for using Fit Gym @Concordia University Application!");
            }
        } while (choice != 0);

        scanner.close();
    }

    // Displays the main menu
    private static void displayMenu() {
        System.out.println(
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(
                "| What would you like to do?                                                                                   |");
        System.out.println(
                "|  1 >> See the content of all Receptions                                                                      |");
        System.out.println(
                "|  2 >> See the content of one Reception                                                                       |");
        System.out.println(
                "|  3 >> List Receptions with same $ amount of gym passes                                                       |");
        System.out.println(
                "|  4 >> List Receptions with same number of gym passes types                                                   |");
        System.out.println(
                "|  5 >> List Receptions with same $ amount of gym passes and same number of memberships                        |");
        System.out.println(
                "|  6 >> Add a membership card to an existing Reception                                                         |");
        System.out.println(
                "|  7 >> Remove an existing membership card from a Reception                                                    |");
        System.out.println(
                "|  8 >> Update the expiry date of an existing membership card                                                  |");
        System.out.println(
                "|  9 >> Add gym passes to a Reception                                                                          |");
        System.out.println(
                "|  0 >> To quit                                                                                                |");
        System.out.println(
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("\nPlease enter your choice and press <Enter>: ");
    }

    // Ensures the user enters a valid menu option (0-9)
    private static int readValidMenuChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            displayMenu();
        }
        int choice = scanner.nextInt();
        if (choice < 0 || choice > 9) {
            System.out.println("Sorry that is not a valid choice. Try again.");
        }
        return choice;
    }

    // Option 1 – Display the contents of every reception
    private static void viewAllReceptions(Reception[] receptions) {
        System.out.println("Content of each Reception:");
        System.out.println("---------------------------------------");
        for (int i = 0; i < receptions.length; i++) {
            System.out.println("Reception #" + i + ":");
            System.out.print(receptions[i].getGymPassesBreakdown());

            int cardCount = receptions[i].totalGymCards();
            if (cardCount > 0) {
                System.out.println();
                for (int j = 0; j < cardCount; j++) {
                    GymCard card = receptions[i].getGymCard(j);
                    System.out.printf("%s - %s - %02d/%02d.\n",
                            card.getType(), card.getName(), card.getDay(), card.getMonth());
                }
            } else {
                System.out.println("\nNo membership card");
            }
            System.out.println();
        }
    }

    // Option 2 – Display a single reception chosen by the user
    private static void viewOneReception(Reception[] receptions, Scanner scanner) {
        int index = getValidReceptionIndex(receptions, scanner, "see the content of");
        System.out.println(receptions[index]);
    }

    // Option 3 – Find pairs with the same total monetary value of passes
    private static void listSameTotalValue(Reception[] receptions) {
        System.out.println("List of Receptions with same total $ GymPasses:");
        boolean found = false;
        for (int i = 0; i < receptions.length; i++)
            for (int j = i + 1; j < receptions.length; j++)
                if (receptions[i].isEqualValue(receptions[j])) {
                    System.out.println("\tReceptions " + i + " and " + j + " both have " + receptions[i].totalValue());
                    found = true;
                }
        if (!found)
            System.out.println("None found.");
    }

    // Option 4 – Find pairs with identical pass type counts
    private static void listSamePassTypes(Reception[] receptions) {
        System.out.println("List of Receptions with same gym passes types distribution:");
        boolean found = false;
        for (int i = 0; i < receptions.length; i++)
            for (int j = i + 1; j < receptions.length; j++)
                if (receptions[i].isEqualAmount(receptions[j])) {
                    System.out.println("\tReceptions " + i + " and " + j + ".");
                    found = true;
                }
        if (!found)
            System.out.println("None found.");
    }

    // Option 5 – Find pairs that are fully equal (value and membership count)
    private static void listSameValueAndMemberships(Reception[] receptions) {
        System.out.println("List of Receptions with same $ amount of passes and same number of memberships:\n");
        boolean found = false;
        for (int i = 0; i < receptions.length; i++)
            for (int j = i + 1; j < receptions.length; j++)
                if (receptions[i].equals(receptions[j])) {
                    System.out.println("Receptions " + i + " and " + j);
                    found = true;
                }
        if (!found)
            System.out.println("None found.");
    }

    // Option 6 – Add a new membership card
    private static void addMembership(Reception[] receptions, Scanner scanner) {
        int index = getValidReceptionIndex(receptions, scanner, "add an membership to");

        System.out.println("Please enter the following information so that we may complete the membership-");
        System.out.print("--> Type of membership (Basic, Standard, Premium, PremiumPlus): ");
        String type = scanner.next();
        System.out.print("--> name of the membership card holder: ");
        String name = scanner.next();
        System.out.print("--> Expiry day number and month (separate by a space): ");
        int day = scanner.nextInt(), month = scanner.nextInt();

        receptions[index].addGymCard(new GymCard(type, name, day, month));
        System.out.println("You now have " + receptions[index].totalGymCards() + " GymCard(s)");
    }

    // Option 7 – Remove an existing membership card
    private static void removeMembership(Reception[] receptions, Scanner scanner) {
        int index = getValidReceptionIndex(receptions, scanner, "remove a membership card from");

        if (receptions[index].totalGymCards() == 0) {
            System.out.println("Sorry that Reception has no membership card");
            return;
        }

        System.out.print("Which membership card do you want to remove? (Enter number 0 to "
                + (receptions[index].totalGymCards() - 1) + "): ");
        int cardIndex = scanner.nextInt();

        if (receptions[index].removeGymCard(cardIndex))
            System.out.println("Membership card was removed successfully");
        else
            System.out.println("Sorry but there is no membership number " + cardIndex);
    }

    // Option 8 – Update the expiry date of a membership card
    private static void updateExpiry(Reception[] receptions, Scanner scanner) {
        int index = getValidReceptionIndex(receptions, scanner, "update a membership card from");

        if (receptions[index].totalGymCards() == 0) {
            System.out.println("Sorry that Reception has no membership card");
            return;
        }

        System.out.print("Which membership card do you want to update? (Enter number 0 to "
                + (receptions[index].totalGymCards() - 1) + "): ");
        int cardIndex = scanner.nextInt();

        while (cardIndex < 0 || cardIndex >= receptions[index].totalGymCards()) {
            System.out.println("Sorry but there is no membership number " + cardIndex);
            System.out.print("--> Try again (0-" + (receptions[index].totalGymCards() - 1) + "): ");
            cardIndex = scanner.nextInt();
        }

        System.out.print("--> Enter new expiry date day number and month (separate by a space): ");
        int day = scanner.nextInt(), month = scanner.nextInt();

        receptions[index].updateGymCardExpiry(cardIndex, day, month);
        System.out.println("Expiry Date updated.");
    }

    // Option 9 – Add more gym passes to a reception
    private static void addPasses(Reception[] receptions, Scanner scanner) {
        int index = getValidReceptionIndex(receptions, scanner, "add passes to");

        System.out.println(
                "How many Regular Pass ($7), Student Pass($5), Senior Pass ($4), Weekend Pass ($12) and Weekly Pass ($42) gym passes do you want to add?");
        System.out.print("Enter 5 numbers separated by a space): ");

        int regular = scanner.nextInt(), student = scanner.nextInt(), senior = scanner.nextInt(),
                weekend = scanner.nextInt(), weekly = scanner.nextInt();

        int newTotal = receptions[index].addGymPasses(regular, student, senior, weekend, weekly);
        System.out.println("You now have $" + newTotal + ".0");
    }

    // Helper method – repeatedly prompt until a valid reception index (0-4) is
    // entered
    private static int getValidReceptionIndex(Reception[] receptions, Scanner scanner, String action) {
        System.out.print("Which Reception do you want to " + action + "? (Enter number 0 to "
                + (receptions.length - 1) + "): ");
        int index = scanner.nextInt();

        while (index < 0 || index >= receptions.length) {
            System.out.print("Sorry but there is no Reception number " + index + "\n--> Try again: ");
            index = scanner.nextInt();
        }
        return index;
    }
}
