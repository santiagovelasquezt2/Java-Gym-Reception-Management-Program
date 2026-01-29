package a4_40321098;

import java.util.Objects;

/**
 * Reception Class
 * Represents a single reception desk in the gym, managing gym passes and
 * membership cards.
 * Provides methods to compare receptions (by value or distribution),
 * add/remove/update cards,
 * add passes, and generate formatted output for display in the driver program.
 */
public class Reception {

    // ====================================================================
    // INSTANCE ATTRIBUTES
    // ====================================================================
    /** The collection of gym passes handled by this reception */
    private GymPasses gymPasses;

    /** Array of membership cards (can be null if none) */
    private GymCard[] gymCards;

    // ====================================================================
    // CONSTRUCTORS
    // ====================================================================

    /**
     * Default Constructor
     * Creates an empty reception with no passes and no cards.
     */
    public Reception() {
        this.gymPasses = new GymPasses();
        this.gymCards = null;
    }

    /**
     * Parameterized Constructor
     * Creates a reception with given passes and cards.
     * Makes deep copies to avoid shared references.
     */
    public Reception(GymPasses gymPasses, GymCard[] gymCards) {
        GymPasses gymPassesCopy = new GymPasses(gymPasses);
        this.gymPasses = gymPassesCopy;

        if (gymCards == null) {
            this.gymCards = null;
        } else {
            GymCard[] gymCardCopy = new GymCard[gymCards.length];
            for (int i = 0; i < gymCards.length; i++) {
                gymCardCopy[i] = new GymCard(gymCards[i]); // Deep copy using copy constructor
            }
            this.gymCards = gymCardCopy;
        }
    }

    // ====================================================================
    // COMPARISON METHODS
    // ====================================================================

    /**
     * Checks if this reception has the same total monetary value of passes as
     * another.
     * Used for menu option 3 in the driver.
     */
    public boolean isEqualValue(Reception other) {
        return this.gymPasses.gymPassesTotal() == other.gymPasses.gymPassesTotal();
    }

    /**
     * Checks if this reception has exactly the same count of each pass type as
     * another.
     * Used for menu option 4 in the driver.
     */
    public boolean isEqualAmount(Reception other) {
        return this.gymPasses.getRegular() == other.gymPasses.getRegular()
                && this.gymPasses.getStudent() == other.gymPasses.getStudent()
                && this.gymPasses.getSenior() == other.gymPasses.getSenior()
                && this.gymPasses.getWeekend() == other.gymPasses.getWeekend()
                && this.gymPasses.getWeekly() == other.gymPasses.getWeekly();
    }

    // ====================================================================
    // GETTERS / ACCESSORS
    // ====================================================================

    /**
     * Returns the total monetary value of all passes at this reception.
     */
    public int totalValue() {
        return this.gymPasses.gymPassesTotal();
    }

    /**
     * Returns the number of membership cards at this reception.
     */
    public int totalGymCards() {
        return (this.gymCards == null) ? 0 : this.gymCards.length;
    }

    /**
     * Returns the GymCard at the specified index, or null if invalid.
     * Used by the driver to display cards.
     */
    public GymCard getGymCard(int index) {
        if (this.gymCards != null && index >= 0 && index < this.gymCards.length) {
            return this.gymCards[index];
        }
        return null;
    }

    /**
     * Returns a string breakdown of the passes in the format required by the
     * driver.
     */
    public String getGymPassesBreakdown() {
        return this.gymPasses.toString();
    }

    // ====================================================================
    // MODIFIERS
    // ====================================================================

    /**
     * Adds a new membership card to this reception.
     * Resizes the array dynamically and returns the new total number of cards.
     * Used for menu option 6 in the driver.
     */
    public int addGymCard(GymCard newCard) {
        if (this.gymCards == null) {
            this.gymCards = new GymCard[1];
            this.gymCards[0] = newCard;
        } else {
            GymCard[] tempArray = new GymCard[gymCards.length + 1];
            for (int i = 0; i < this.gymCards.length; i++) {
                tempArray[i] = this.gymCards[i];
            }
            tempArray[this.gymCards.length] = newCard;
            this.gymCards = tempArray;
        }
        return this.gymCards.length;
    }

    /**
     * Removes the membership card at the specified index.
     * Returns true if successful, false if the index is invalid or no cards.
     * Used for menu option 7 in the driver.
     */
    public boolean removeGymCard(int index) {
        if (this.gymCards == null || index < 0 || index >= this.gymCards.length) {
            return false;
        }

        // If it's the only card, set an array to null
        if (this.gymCards.length == 1) {
            this.gymCards = null;
            return true;
        }

        // Create a smaller array
        GymCard[] tempArray = new GymCard[this.gymCards.length - 1];
        int k = 0;
        for (int i = 0; i < this.gymCards.length; i++) {
            if (i == index) {
                continue; // Skip the one to remove
            }
            tempArray[k++] = this.gymCards[i];
        }
        this.gymCards = tempArray;
        return true;
    }

    /**
     * Updates the expiry date of the card at the specified index.
     * Does nothing if the index is invalid or no cards.
     * Used for menu option 8 in the driver.
     */
    public void updateGymCardExpiry(int index, int newDay, int newMonth) {
        if (this.gymCards != null && index >= 0 && index < this.gymCards.length) {
            this.gymCards[index].setDay(newDay);
            this.gymCards[index].setMonth(newMonth);
        }
    }

    /**
     * Adds the specified number of each pass type and returns the new total value.
     * Used for menu option 9 in the driver.
     */
    public int addGymPasses(int regular, int student, int senior, int weekend, int weekly) {
        this.gymPasses.addGymPasses(regular, student, senior, weekend, weekly);
        return this.gymPasses.gymPassesTotal();
    }

    // ====================================================================
    // UTILITY METHODS
    // ====================================================================

    /**
     * Returns a formatted string representation of this reception.
     * Includes passes breakdown and all cards (or "No membership card").
     * Used for menu option 2 in the driver.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(gymPasses.toString()).append("\n");

        if (gymCards != null && gymCards.length > 0) {
            for (int i = 0; i < gymCards.length; i++) {
                sb.append(gymCards[i].toString());
                // Add a newline only if not the last card, or always depending on the desired
                // spacing
                if (i < gymCards.length - 1) {
                    sb.append("\n");
                }
            }
        } else {
            sb.append("No membership card\n");
        }
        return sb.toString();
    }

    /**
     * Compares this reception with another object for equality.
     * Two receptions are equal if they have the same total pass value
     * and the same number of membership cards.
     * Used for menu option 5 in the driver.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Reception other = (Reception) obj;
        return this.totalValue() == other.totalValue() &&
                this.totalGymCards() == other.totalGymCards();
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalValue(), totalGymCards());
    }
}