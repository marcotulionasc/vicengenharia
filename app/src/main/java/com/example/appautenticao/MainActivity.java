package com.example.appautenticao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appautenticao.databinding.ActivityMainBinding;
import com.example.appautenticao.domain.Account;
import com.example.appautenticao.domain.AccountAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private List<Account> accountList;
    private AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar a lista e o adaptador
        accountList = new ArrayList<>();
        accountAdapter = new AccountAdapter(accountList);

        // Configurar o RecyclerView
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(accountAdapter);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.btnDeslogar) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            } else if (item.getItemId() == R.id.btnAdicionarConta) {
                startActivity(new Intent(this, RegistrarConta.class));
                return true;
            }
            return false;
        });

        // Recuperar e exibir dados do Firestore
        retrieveDataFromFirestore();
    }

    // Método para recuperar dados do Firestore
    private void retrieveDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Referência para a coleção "Contas"
        db.collection("Contas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Limpar a lista existente
                    accountList.clear();

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        // Converte o documento em um objeto Account
                        Account account = documentSnapshot.toObject(Account.class);
                        accountList.add(account);
                    }

                    // Notificar o adaptador sobre a mudança nos dados
                    accountAdapter.notifyDataSetChanged();
                    Log.d("MainActivity", "Número de itens na lista: " + accountList.size());

                })
                .addOnFailureListener(e -> {
                    // Tratar falha na recuperação dos dados
                    Toast.makeText(this, "Erro ao recuperar dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
