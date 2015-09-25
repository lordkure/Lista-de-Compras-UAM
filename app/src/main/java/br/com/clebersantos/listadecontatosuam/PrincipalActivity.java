package br.com.clebersantos.listadecontatosuam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.clebersantos.listadecontatosuam.DAO.ContatoDAO;
import br.com.clebersantos.listadecontatosuam.pojo.Contato;

/**
 * Created by Cleber Luiz da Silva dos Santos on 25/09/2015.
 * Universidade Anhembi Morumbi
 * RA: 20535680
 * Email: santos.cleber@gmail.com
 */
public class PrincipalActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private ListView listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, getContatos());
        listaContatos.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // INCLUO O MENU CRIADO A ESSA VIEW
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // CASO O ITEM CLICADO SEJA O 'ABOUT', COM O INTENT CHAMO A VIEW ABOUT
        if (id == R.id.action_settings) {
            Intent irParaSobre = new Intent(this, SobreActivity.class);
            startActivity(irParaSobre);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //METODO USADO PARA TRAZER DADOS DO BANCO JA CADASTRADOS
    private List<Contato> getContatos(){
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> listaContatos = dao.getListaContatos();
        return  listaContatos;
    }

    /**
     * METODO USADO PARA INICIALIZAR COMPONENTES DA VIEW
     */
    public void inicializar(){
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        listaContatos = (ListView) findViewById(R.id.listaContatos);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar = new Intent(PrincipalActivity.this, CadastrarActivity.class);
                startActivity(cadastrar);
            }
        });
    }
}
