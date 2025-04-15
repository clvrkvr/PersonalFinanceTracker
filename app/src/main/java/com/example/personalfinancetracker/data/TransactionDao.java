package com.example.personalfinancetracker.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object (DAO) for the Transaction entity.
 *
 * Defines database operations (CRUD) for transactions using Room.
 */
@Dao
public interface TransactionDao {

    /**
     * Inserts a new transaction into the database.
     *
     * @param transaction The transaction object to be inserted.
     */
    @Insert
    void insert(Transaction transaction);

    /**
     * Retrieves all transactions from the database,
     * ordered by ID in descending order (latest first).
     *
     * @return A list of all transactions.
     */
    @Query("SELECT * FROM transactions ORDER BY id DESC")
    List<Transaction> getAllTransactions();
}
