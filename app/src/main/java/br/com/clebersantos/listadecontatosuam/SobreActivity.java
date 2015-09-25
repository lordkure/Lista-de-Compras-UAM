package br.com.clebersantos.listadecontatosuam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Cleber Luiz da Silva dos Santos on 25/09/2015.
 * Universidade Anhembi Morumbi
 * RA: 20535680
 * Email: santos.cleber@gmail.com
 */
public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        //CRIO O COMPONENTE BOTAO BUSCANDO NO XML PELO ID
        Button btnVoltar = (Button) findViewById(R.id.btnHome);
        //COLOCO UM EVENTO LISTENER DE CLICK NO BOTAO
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //USANDO O INTENT, FAÇO A CHAMADA DA TELA PRINCIPAL DO APP
                Intent retornarHome = new Intent(SobreActivity.this, PrincipalActivity.class);
                startActivity(retornarHome);
            }
        });


    }


}
