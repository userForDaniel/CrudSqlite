package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button listar, registrar,buscar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        context=getApplicationContext();
        listar=findViewById(R.id.btn_listar);
        registrar=findViewById(R.id.btn_registrar);
        buscar=findViewById(R.id.btn_buscar);

    }

    @Override
    public void onClick(View v) {



    }


}