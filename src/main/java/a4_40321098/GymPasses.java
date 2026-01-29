package a4_40321098;

import java.util.Objects;

/**
 * GymPasses Class
 * Represents the collection of five different gym pass types sold at a
 * reception desk.
 * Tracks the number of each pass type and provides methods to compute total
 * monetary value,
 * compare pass distributions, and add more passes.
 */
public class GymPasses {

    // ====================================================================
    // Instance variables – counts of each pass type (non-negative by design)
    // ====================================================================
    private int regular = 0; // Number of Regular passes ($7 each)
    private int student = 0; // Number of Student passes ($5 each)
    private int senior = 0; // Number of Senior passes ($4 each)
    private int weekend = 0; // Number of Weekend passes ($12 each)
    private int weekly = 0; // Number of Weekly passes ($42 each)

    // ====================================================================
    // Public constants – fixed price for each pass type (shared by all instances)
    // ====================================================================
    public static final int PRICE_REGULAR = 7;
    public static final int PRICE_STUDENT = 5;
    public static final int PRICE_SENIOR = 4;
    public static final int PRICE_WEEKEND = 12;
    public static final int PRICE_WEEKLY = 42;

    // ====================================================================
    // Constructors
    // ====================================================================
    // Default constructor – creates an empty set of passes (all counts = 0)
    public GymPasses() {
    }

    // Parameterized constructor – initializes counts with given values
    public GymPasses(int regular, int student, int senior, int weekend, int weekly) {
        this.regular = regular;
        this.student = student;
        this.senior = senior;
        this.weekend = weekend;
        this.weekly = weekly;
    }

    // ====================================================================
    // Simple getters – return the current count of each pass type
    // ====================================================================
    public int getRegular() {
        return regular;
    }

    public int getStudent() {
        return student;
    }

    public int getSenior() {
        return senior;
    }

    public int getWeekend() {
        return weekend;
    }

    public int getWeekly() {
        return weekly;
    }

    // ====================================================================
    // Static price getters – allow price lookup without an instance
    // ====================================================================
    public static int getPriceRegular() {
        return PRICE_REGULAR;
    }

    public static int getPriceStudent() {
        return PRICE_STUDENT;
    }

    public static int getPriceSenior() {
        return PRICE_SENIOR;
    }

    public static int getPriceWeekend() {
        return PRICE_WEEKEND;
    }

    public static int getPriceWeekly() {
        return PRICE_WEEKLY;
    }

    // ====================================================================
    // Simple setters – update the count of a specific pass type
    // ====================================================================
    public void setRegular(int regular) {
        this.regular = regular;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public void setSenior(int senior) {
        this.senior = senior;
    }

    public void setWeekend(int weekend) {
        this.weekend = weekend;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    // ====================================================================
    // Utility: copy the counts from another GymPasses object
    // ====================================================================
    public void copy(GymPasses x) {
        if (x != null) {
            this.setRegular(x.getRegular());
            this.setStudent(x.getStudent());
            this.setSenior(x.getSenior());
            this.setWeekend(x.getWeekend());
            this.setWeekly(x.getWeekly());
        }
    }

    // ====================================================================
    // Copy constructor – creates a new object that is an exact duplicate
    // ====================================================================
    public GymPasses(GymPasses x) {
        this.copy(x);
    }

    // ====================================================================
    // Add more passes to the current counts (used by menu option 9)
    // ====================================================================
    public void addGymPasses(int regular, int student, int senior, int weekend, int weekly) {
        this.regular += regular;
        this.student += student;
        this.senior += senior;
        this.weekend += weekend;
        this.weekly += weekly;
    }

    // ====================================================================
    // Calculate and return the total monetary value of all passes
    // ====================================================================
    public int gymPassesTotal() {
        return getRegular() * PRICE_REGULAR
                + getStudent() * PRICE_STUDENT
                + getSenior() * PRICE_SENIOR
                + getWeekend() * PRICE_WEEKEND
                + getWeekly() * PRICE_WEEKLY;
    }

    // ====================================================================
    // toString – returns the exact format required by the driver program
    // Example: "10 x $7 + 4 x $5 + 1 x $4 + 1 x $12 + 1 x $42"
    // ====================================================================
    @Override
    public String toString() {
        return getRegular() + " x $" + PRICE_REGULAR + " + "
                + getStudent() + " x $" + PRICE_STUDENT + " + "
                + getSenior() + " x $" + PRICE_SENIOR + " + "
                + getWeekend() + " x $" + PRICE_WEEKEND + " + "
                + getWeekly() + " x $" + PRICE_WEEKLY;
    }

    // ====================================================================
    // Equality check – two GymPasses objects are equal if every count matches
    // (Used by Reception.isEqualAmount and menu option 4)
    // ====================================================================
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        GymPasses gp = (GymPasses) obj;
        return regular == gp.regular &&
                student == gp.student &&
                senior == gp.senior &&
                weekend == gp.weekend &&
                weekly == gp.weekly;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regular, student, senior, weekend, weekly);
    }
}