package com.example.personalfinancetracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.personalfinancetracker.R;
import com.example.personalfinancetracker.data.AppDatabase;
import com.example.personalfinancetracker.data.Transaction;
import com.example.personalfinancetracker.data.TransactionDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Main screen of the Personal Finance Tracker app.
 *
 * Displays a list of all financial transactions in a RecyclerView and
 * shows the current balance at the top. Users can add new transactions
 * using the floating action button (FAB).
 */
public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private TransactionAdapter adapter;
    private RecyclerView recyclerView;
    private TextView balanceTextView;
    private List<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        balanceTextView = findViewById(R.id.balanceTextView);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        // Initialize Room database (on the main thread for simplicity)
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "finance_db").allowMainThreadQueries().build();

        // Fetch all transactions from the database
        TransactionDao transactionDao = db.transactionDao();
        transactions = transactionDao.getAllTransactions();

        // Set up RecyclerView with the adapter
        adapter = new TransactionAdapter(transactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Display the current balance
        updateBalance();

        // Navigate to AddTransactionActivity when FAB is clicked
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddTransactionActivity.class)));
    }

    /**
     * Calculates the total balance from all transactions
     * and updates the balance TextView.
     */
    private void updateBalance() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.amount;
        }
        balanceTextView.setText("Balance: $" + String.format("%.2f", total));
    }

    /**
     * Refreshes the transaction list and balance when the activity resumes.
     * Ensures new transactions are reflected immediately after returning from AddTransactionActivity.
     */
    @Override
    protected void onResume() {
        super.onResume();
        transactions = db.transactionDao().getAllTransactions();
        adapter.updateData(transactions);
        updateBalance();
    }
}
