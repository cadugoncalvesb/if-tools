package com.example.if_tools;

//import static com.google.firebase.database.core.operation.OperationSource.Source.User;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.if_tools.databinding.ActivityCadastroBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.Source;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        binding.btnCriarConta.setOnClickListener(v -> {
           if (validaDados()) {
               criarContaFirebase();
           } else {
               Toast.makeText(this, "Erro na criação", Toast.LENGTH_SHORT).show();
           }
        });

    }

    private boolean validaDados() {
        String nome = binding.editNome.getText().toString().trim();
        String telefone = binding.editTelefone.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        if (nome.isEmpty()) {
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show();
            binding.editNome.setError("*");
            return false;
        }
        if (telefone.isEmpty()) {
            Toast.makeText(this, "Informe seu telefone", Toast.LENGTH_SHORT).show();
            binding.editTelefone.setError("*");
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_SHORT).show();
            binding.editEmail.setError("*");
            return false;
        }
        if (senha.isEmpty()) {
            Toast.makeText(this, "Informe sua senha", Toast.LENGTH_SHORT).show();
            binding.editSenha.setError("*");
            return false;
        }
        if (senha.length() < 6) {
            Toast.makeText(this, "Senha com menos de 6 caracter", Toast.LENGTH_SHORT).show();
            binding.editSenha.setError("*");
            return false;
        }

        // binding.progressBar.setVisibility(View.VISIBLE);
       return true;
        }

    private void criarContaFirebase() {

        String nome = binding.editNome.getText().toString().trim();
        String telefone = binding.editTelefone.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        User user = new User(nome, telefone, email, senha, 3, 3);
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                //finish();
                //startActivity(new Intent(this, MainActivity.class));
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                String userId = firebaseUser.getUid();
                mDatabase.child("usuarios").child(userId).setValue(user);
                Toast.makeText(this, "Sucesso com o id: " + userId, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));

            } else {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Erro ao criar usuário: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}