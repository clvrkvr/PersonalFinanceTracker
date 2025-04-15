package com.example.personalfinancetracker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalfinancetracker.R;
import com.example.personalfinancetracker.data.Transaction;

import java.util.List;

/**
 * RecyclerView Adapter for displaying a list of transactions.
 *
 * Binds each Transaction object to a view layout (item_transaction.xml)
 * for display inside the RecyclerView.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactions;

    /**
     * Constructor to initialize the adapter with a list of transactions.
     *
     * @param transactions The initial list of transactions to display.
     */
    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Updates the data set with a new list of transactions and refreshes the UI.
     *
     * @param newList A new list of transactions.
     */
    public void updateData(List<Transaction> newList) {
        transactions = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each transaction row
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        // Bind the data for the transaction at the given position
        Transaction t = transactions.get(position);
        holder.title.setText(t.title);
        holder.amount.setText("$" + String.format("%.2f", t.amount));
        holder.category.setText(t.category);
        holder.date.setText(t.date);
    }

    @Override
    public int getItemCount() {
        return transactions.size(); // Total number of transactions
    }

    /**
     * ViewHolder class for holding and recycling views efficiently.
     */
    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView title, amount, category, date;

        /**
         * Initializes view references for the transaction item.
         *
         * @param itemView The root view of the transaction item layout.
         */
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            amount = itemView.findViewById(R.id.textAmount);
            category = itemView.findViewById(R.id.textCategory);
            date = itemView.findViewById(R.id.textDate);
        }
    }
}
