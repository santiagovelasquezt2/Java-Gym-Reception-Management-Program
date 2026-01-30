package gym;

import java.util.Objects;

/**
 * Reception Class
 * Represents a single reception desk in the gym, managing gym passes and
 * membership cards.
 * Provides methods to compare receptions (by value or distribution),
 * add/remove/update cards,
 * add passes, and generate formatted output for display.
 */
public class Reception {

    private GymPasses gymPasses;
    private GymCard[] gymCards;

    /**
     * Default Constructor
     */
    public Reception() {
        this.gymPasses = new GymPasses();
        this.gymCards = null;
    }

    /**
     * Parameterized Constructor
     */
    public Reception(GymPasses gymPasses, GymCard[] gymCards) {
        GymPasses gymPassesCopy = new GymPasses(gymPasses);
        this.gymPasses = gymPassesCopy;

        if (gymCards == null) {
            this.gymCards = null;
        } else {
            GymCard[] gymCardCopy = new GymCard[gymCards.length];
            for (int i = 0; i < gymCards.length; i++) {
                gymCardCopy[i] = new GymCard(gymCards[i]);
            }
            this.gymCards = gymCardCopy;
        }
    }

    public boolean isEqualValue(Reception other) {
        return this.gymPasses.gymPassesTotal() == other.gymPasses.gymPassesTotal();
    }

    public boolean isEqualAmount(Reception other) {
        return this.gymPasses.getRegular() == other.gymPasses.getRegular()
                && this.gymPasses.getStudent() == other.gymPasses.getStudent()
                && this.gymPasses.getSenior() == other.gymPasses.getSenior()
                && this.gymPasses.getWeekend() == other.gymPasses.getWeekend()
                && this.gymPasses.getWeekly() == other.gymPasses.getWeekly();
    }

    public int totalValue() {
        return this.gymPasses.gymPassesTotal();
    }

    public int totalGymCards() {
        return (this.gymCards == null) ? 0 : this.gymCards.length;
    }

    public GymCard getGymCard(int index) {
        if (this.gymCards != null && index >= 0 && index < this.gymCards.length) {
            return this.gymCards[index];
        }
        return null;
    }

    public String getGymPassesBreakdown() {
        return this.gymPasses.toString();
    }

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

    public boolean removeGymCard(int index) {
        if (this.gymCards == null || index < 0 || index >= this.gymCards.length) {
            return false;
        }

        if (this.gymCards.length == 1) {
            this.gymCards = null;
            return true;
        }

        GymCard[] tempArray = new GymCard[this.gymCards.length - 1];
        int k = 0;
        for (int i = 0; i < this.gymCards.length; i++) {
            if (i == index) {
                continue;
            }
            tempArray[k++] = this.gymCards[i];
        }
        this.gymCards = tempArray;
        return true;
    }

    public void updateGymCardExpiry(int index, int newDay, int newMonth) {
        if (this.gymCards != null && index >= 0 && index < this.gymCards.length) {
            this.gymCards[index].setDay(newDay);
            this.gymCards[index].setMonth(newMonth);
        }
    }

    public int addGymPasses(int regular, int student, int senior, int weekend, int weekly) {
        this.gymPasses.addGymPasses(regular, student, senior, weekend, weekly);
        return this.gymPasses.gymPassesTotal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(gymPasses.toString()).append("\n");

        if (gymCards != null && gymCards.length > 0) {
            for (int i = 0; i < gymCards.length; i++) {
                sb.append(gymCards[i].toString());
                if (i < gymCards.length - 1) {
                    sb.append("\n");
                }
            }
        } else {
            sb.append("No membership card\n");
        }
        return sb.toString();
    }

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
