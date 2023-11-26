package com.example.appautenticao.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appautenticao.R;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private List<Account> accountList;

    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.textViewAccountName.setText(account.getAccountName());
        holder.textViewAmount.setText(account.getAmount());

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAccountName;
        TextView textViewAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAccountName = itemView.findViewById(R.id.textViewAccountName);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);

        }
    }
}

