package gym;

import java.util.Objects;

/**
 * GymCard Class
 * Represents a single gym membership card with type, holder name, and
 * expiration date.
 * Enforces strict validation: day must be 1–31, and month must be 1–12.
 * Any invalid value is automatically stored as 0.
 */
public class GymCard {

    private String type;
    private String name;
    private int day;
    private int month;

    /**
     * Default Constructor
     */
    public GymCard() {
    }

    /**
     * Parameterized Constructor
     */
    public GymCard(String type, String name, int day, int month) {
        this.type = type;
        this.name = name;

        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            this.day = 0;
        }
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 0;
        }
    }

    /**
     * Copy Constructor
     */
    public GymCard(GymCard other) {
        this.type = other.getType();
        this.name = other.getName();
        this.day = other.getDay();
        this.month = other.getMonth();
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            this.day = 0;
        }
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 0;
        }
    }

    @Override
    public String toString() {
        return "Gym Card: " + type +
                "\nHolder: " + name +
                "\nExpires: " + String.format("%02d/%02d", day, month);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        GymCard other = (GymCard) obj;

        if (!Objects.equals(this.type, other.getType()))
            return false;
        if (!Objects.equals(this.name, other.getName()))
            return false;
        if (this.day != other.getDay())
            return false;
        if (this.month != other.getMonth())
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, day, month);
    }
}
