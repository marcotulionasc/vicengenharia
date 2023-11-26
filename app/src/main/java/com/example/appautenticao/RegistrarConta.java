package com.example.appautenticao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appautenticao.databinding.ActivityLoginBinding;
import com.example.appautenticao.databinding.ActivityRegistrarContaBinding;
import com.example.appautenticao.domain.Account;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarConta extends AppCompatActivity {

    private ActivityRegistrarContaBinding binding;

    private EditText editTextAccountName, editTextAmount, editTextDueDate;
    private Spinner spinnerCategory;
    private Button btnRegisterAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar o Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference accountsRef = database.getReference("accounts");


        editTextAccountName = findViewById(R.id.editTextAccountName);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextDueDate = findViewById(R.id.editTextDueDate);
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
        // Obter os valores dos campos
        String accountName = editTextAccountName.getText().toString();
        String amount = editTextAmount.getText().toString();
        String dueDate = editTextDueDate.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();

        // Obter uma referência para o nó "accounts" no Firebase
        DatabaseReference accountsRef = FirebaseDatabase.getInstance().getReference("accounts");

        // Criar um ID único para a nova conta
        String accountId = accountsRef.push().getKey();

        // Criar um objeto Account para representar os dados da conta
        Account newAccount = new Account(accountId, accountName, amount, dueDate, category);

        // Enviar os dados para o Firebase
        accountsRef.child(accountId).setValue(newAccount);

        // Exemplo de como você pode exibir uma mensagem simples
        Toast.makeText(this, "Conta Registrada!\nNome: " + accountName +
                "\nValor: " + amount +
                "\nData de Vencimento: " + dueDate +
                "\nCategoria: " + category, Toast.LENGTH_SHORT).show();
    }
}