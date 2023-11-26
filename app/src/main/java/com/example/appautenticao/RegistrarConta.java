package com.example.appautenticao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appautenticao.databinding.ActivityRegistrarContaBinding;
import com.example.appautenticao.domain.Account;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrarConta extends AppCompatActivity {

    private ActivityRegistrarContaBinding binding;

    private EditText editTextAccountName, editTextAmount, editTextDueDate;
    private Spinner spinnerCategory;
    private Button btnRegisterAccount;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextAccountName = findViewById(R.id.addTituloConta);
        editTextAmount = findViewById(R.id.addValorPagar);
        editTextDueDate = findViewById(R.id.addDataVencimento);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnRegisterAccount = findViewById(R.id.btnAdicionarConta);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
    }

    private void registerAccount() {
        String accountName = editTextAccountName.getText().toString();
        String amount = editTextAmount.getText().toString();
        String dueDate = editTextDueDate.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();

        db.collection("Contas")
                .document(accountName + " - " + category)
                .set(new Account(accountName, amount, dueDate, category))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sucesso ao salvar no Firestore
                        Toast.makeText(RegistrarConta.this, "Conta Registrada com Sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(this, MainActivity.class));
                    } else {
                        // Falha ao salvar no Firestore
                        Toast.makeText(RegistrarConta.this, "Erro ao Registrar Conta: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
