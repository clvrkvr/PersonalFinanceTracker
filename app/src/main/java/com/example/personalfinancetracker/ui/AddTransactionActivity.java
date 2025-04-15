package com.example.personalfinancetracker.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.personalfinancetracker.R;
import com.example.personalfinancetracker.data.AppDatabase;
import com.example.personalfinancetracker.data.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Activity for adding a new financial transaction.
 *
 * Users can input a title, amount, and category. The transaction is saved
 * to the Room database with the current date when the "Save" button is clicked.
 */
public class AddTransactionActivity extends AppCompatActivity {

    private EditText titleInput, amountInput, categoryInput;
    private Button saveBtn, cancelBtn;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialize input fields and button
        titleInput = findViewById(R.id.editTextTitle);
        amountInput = findViewById(R.id.editTextAmount);
        categoryInput = findViewById(R.id.editTextCategory);
        saveBtn = findViewById(R.id.buttonSave);
        cancelBtn = findViewById(R.id.buttonCancel);

        // Initialize the Room database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "finance_db").allowMainThreadQueries().build();

        // Set click listener to save transaction
        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String amountStr = amountInput.getText().toString();
            String category = categoryInput.getText().toString();

            // Validate input fields
            if (title.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);

            // Get current date in yyyy-MM-dd format
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            // Create a new Transaction object
            Transaction transaction = new Transaction();
            transaction.title = title;
            transaction.category = category;
            transaction.amount = amount;
            transaction.date = date;

            // Insert the transaction into the database
            db.transactionDao().insert(transaction);
            Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show();

            // Close the activity and return to MainActivity
            finish();
        });

        // Close the activity and return to MainActivity
        cancelBtn.setOnClickListener(v -> finish());
    }
}
