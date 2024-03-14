package com.example.if_tools;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    public String nome;
    public String telefone;
    public String email;
    public String senha;
    public int nivel = 3;
    public int tipo = 3;

    public User(String nome, String email) {
    }

    public User(String nome, String telefone, String email, String senha, int nivel, int tipo) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public void atualizaDados(String nome, String telefone, String senha) {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
    }

}
