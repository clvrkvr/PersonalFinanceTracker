package com.example.personalfinancetracker.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * The main database class for the Personal Finance Tracker app.
 *
 * This class defines the database configuration and serves as the
 * app's main access point to the persisted data.
 *
 * It includes:
 * - The list of entities (in this case, only Transaction)
 * - The version of the database
 * - The abstract method to get the DAO
 */
@Database(entities = {Transaction.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Provides access to the TransactionDao for database operations.
     *
     * @return The TransactionDao instance.
     */
    public abstract TransactionDao transactionDao();
}
