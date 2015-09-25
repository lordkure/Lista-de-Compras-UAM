package br.com.clebersantos.listadecontatosuam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.clebersantos.listadecontatosuam.DAO.ContatoDAO;
import br.com.clebersantos.listadecontatosuam.pojo.Contato;

/**
 * Created by Cleber Luiz da Silva dos Santos on 25/09/2015.
 * Universidade Anhembi Morumbi
 * RA: 20535680
 * Email: santos.cleber@gmail.com
 */
public class CadastrarActivity extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnIncluir;
    private EditText nome;
    private EditText telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        inicializar();

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

    /**
     * METODO USADO PARA CRIAR NOVO OBJETO CONTATO PARA SER GRAVADO
     */
    private Contato gerarNovoContato(){
        Contato contato = new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        return contato;
    }

    /**
     * METODO USADO PARA INICIALIZAR COMPONENTES DA VIEW
     */
    private void inicializar(){

        //GERO OS COMPONENTES PARA USO
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnIncluir = (Button) findViewById(R.id.btnIncluir);
        nome = (EditText) findViewById(R.id.txtNome);
        telefone = (EditText) findViewById(R.id.txtTelefone);

        //IMPLEMENTO O LISTER DO BOTAO CANCELAR
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent(CadastrarActivity.this, PrincipalActivity.class);
                startActivity(cancelar);
            }
        });

        //INPLEMENTO O LISTENER DO BOTAO INCLUIR
        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato novoContato = gerarNovoContato();
                //CRIO CONEXAO COM O BANCO E CRIO A TABELA SE NAO EXISTIR
                ContatoDAO dao = new ContatoDAO(CadastrarActivity.this);
                dao.inserirContato(novoContato);
                //FECHO CONEXAO COM O BANCO
                dao.close();
                //CHAMO A VIEW PRINCIPAL AO TERMINO DO CADASTRO
                Intent intent = new Intent(CadastrarActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }

}
