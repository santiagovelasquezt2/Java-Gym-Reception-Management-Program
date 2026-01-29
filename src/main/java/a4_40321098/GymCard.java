package a4_40321098;

import java.util.Objects;

/**
 * GymCard Class
 * Represents a single gym membership card with type, holder name, and
 * expiration date.
 * Enforces strict validation: day must be 1–31, and month must be 1–12.
 * Any invalid value is automatically stored as 0.
 */
public class GymCard {

    // ====================================================================
    // INSTANCE ATTRIBUTES
    // ====================================================================
    /** The type/category of gym membership (e.g., "Premium", "Standard") */
    private String type;

    /** The name of the cardholder */
    private String name;

    /** The day of expiration (1-31, or 0 if invalid) */
    private int day;

    /** The month of expiration (1-12, or 0 if invalid) */
    private int month;

    // ====================================================================
    // CONSTRUCTORS
    // ====================================================================

    /**
     * Default Constructor
     * Creates an empty GymCard with all fields uninitialized.
     */
    public GymCard() {
        // All fields remain at their default values (null for Strings, 0 for ints)
    }

    /**
     * Parameterized Constructor
     * Creates a GymCard with a specified type, name, and expiration date.-
     * Validates day (1–31) and month (1–12); invalid values are stored as 0.
     */
    public GymCard(String type, String name, int day, int month) {
        this.type = type;
        this.name = name;

        // Validate and store day – reject anything outside 1-31
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            this.day = 0;
        }
        // Validate and store month – reject anything outside 1-12
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 0;
        }
    }

    /**
     * Copy Constructor
     * Creates a new GymCard that is an exact duplicate of another GymCard.
     * All attributes are copied directly (validation already done in a source).
     */
    public GymCard(GymCard other) {
        this.type = other.getType();
        this.name = other.getName();
        this.day = other.getDay();
        this.month = other.getMonth();
    }

    // ====================================================================
    // GETTERS
    // ====================================================================

    /** Returns the expiration day (1-31 or 0 if invalid) */
    public int getDay() {
        return day;
    }

    /** Returns the expiration month (1-12 or 0 if invalid) */
    public int getMonth() {
        return month;
    }

    /** Returns the cardholder's name */
    public String getName() {
        return name;
    }

    /** Returns the membership type */
    public String getType() {
        return type;
    }

    // ====================================================================
    // SETTERS
    // ====================================================================

    /**
     * Updates the expiration day with validation.
     * Values outside 1–31 are stored as 0.
     */
    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            this.day = 0;
        }
    }

    /**
     * Updates the expiration month with validation.
     * Values outside 1–12 are stored as 0.
     */
    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 0;
        }
    }

    // ====================================================================
    // UTILITY METHODS
    // ====================================================================

    /**
     * Returns a formatted string representation of this gym card.
     * Matches the exact output format required by the driver program.
     * Example:
     * Gym Card: Premium
     * Holder: Scrump
     * Expires: 07/12
     */
    @Override
    public String toString() {
        return "Gym Card: " + type +
                "\nHolder: " + name +
                "\nExpires: " + String.format("%02d/%02d", day, month);
    }

    /**
     * Compares this gym card with another object for equality.
     * Two cards are considered equal only if typed, name, day, and month are
     * identical.
     * String comparison is case-sensitive.
     */
    @Override
    public boolean equals(Object obj) {
        // Same object reference → equal
        if (this == obj)
            return true;

        // Null or wrong class → not equal
        if (obj == null || getClass() != obj.getClass())
            return false;

        GymCard other = (GymCard) obj;

        // Compare every field with null safety
        if (!Objects.equals(this.type, other.getType()))
            return false;
        if (!Objects.equals(this.name, other.getName()))
            return false;
        if (this.day != other.getDay())
            return false;
        if (this.month != other.getMonth())
            return false;

        // All-fields match → cards are equal
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, day, month);
    }
}