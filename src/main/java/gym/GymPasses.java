package gym;

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

    private int regular = 0;
    private int student = 0;
    private int senior = 0;
    private int weekend = 0;
    private int weekly = 0;

    public static final int PRICE_REGULAR = 7;
    public static final int PRICE_STUDENT = 5;
    public static final int PRICE_SENIOR = 4;
    public static final int PRICE_WEEKEND = 12;
    public static final int PRICE_WEEKLY = 42;

    public GymPasses() {
    }

    public GymPasses(int regular, int student, int senior, int weekend, int weekly) {
        this.regular = regular;
        this.student = student;
        this.senior = senior;
        this.weekend = weekend;
        this.weekly = weekly;
    }

    public GymPasses(GymPasses x) {
        this.copy(x);
    }

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

    public void copy(GymPasses x) {
        if (x != null) {
            this.setRegular(x.getRegular());
            this.setStudent(x.getStudent());
            this.setSenior(x.getSenior());
            this.setWeekend(x.getWeekend());
            this.setWeekly(x.getWeekly());
        }
    }

    public void addGymPasses(int regular, int student, int senior, int weekend, int weekly) {
        this.regular += regular;
        this.student += student;
        this.senior += senior;
        this.weekend += weekend;
        this.weekly += weekly;
    }

    public int gymPassesTotal() {
        return getRegular() * PRICE_REGULAR
                + getStudent() * PRICE_STUDENT
                + getSenior() * PRICE_SENIOR
                + getWeekend() * PRICE_WEEKEND
                + getWeekly() * PRICE_WEEKLY;
    }

    @Override
    public String toString() {
        return getRegular() + " x $" + PRICE_REGULAR + " + "
                + getStudent() + " x $" + PRICE_STUDENT + " + "
                + getSenior() + " x $" + PRICE_SENIOR + " + "
                + getWeekend() + " x $" + PRICE_WEEKEND + " + "
                + getWeekly() + " x $" + PRICE_WEEKLY;
    }

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
