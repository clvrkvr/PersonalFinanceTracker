package com.example.personalfinancetracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Represents a financial transaction in the personal finance tracker.
 * This entity maps to the "transactions" table in the Room database.
 *
 * Each transaction includes:
 * - a unique ID (auto-generated)
 * - a title describing the transaction
 * - a category (e.g., Food, Rent, Salary)
 * - the amount of money involved
 * - the date of the transaction
 */
@Entity(tableName = "transactions")
public class Transaction {

    /**
     * Unique identifier for the transaction.
     * Auto-generated by the Room database.
     */
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * Title or short description of the transaction.
     * Example: "Groceries", "Salary"
     */
    public String title;

    /**
     * Category of the transaction.
     * Example: "Food", "Income", "Utilities"
     */
    public String category;

    /**
     * Amount of money involved in the transaction.
     * Can be positive (income) or negative (expense).
     */
    public double amount;

    /**
     * Date of the transaction in String format.
     * Expected format could be "yyyy-MM-dd" (e.g., "2025-04-15").
     */
    public String date;
}
