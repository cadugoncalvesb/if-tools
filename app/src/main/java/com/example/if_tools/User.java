package com.example.if_tools;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String nome;
    public String telefone;
    public String email;
    public String senha;
    public int nivel;

    public User(String nome, String email) {
    }

    public User(String nome, String telefone, String email, String senha, int nivel) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;

    }
}
