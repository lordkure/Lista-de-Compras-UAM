package br.com.clebersantos.listadecontatosuam;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
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
    private Contato contato;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inicializar();

        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, getContatos());

        carregar();

        /*listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contatoAlterado = (Contato) parent.getItemAtPosition(position);
                Intent irParaEdicao = new Intent(PrincipalActivity.this, CadastrarActivity.class);
                irParaEdicao.putExtra("contatoSelecionado", contatoAlterado);
                startActivity(irParaEdicao);
            }
        });*/

        listaContatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contato = (Contato) parent.getItemAtPosition(position);
                return false;
            }
        });

        registerForContextMenu(listaContatos);

    }

    @Override
    protected void onResume() {
        carregar();
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
        List<Contato> listaContatos = new ArrayList<>();
        try {
            ContatoDAO dao = new ContatoDAO(this);
            listaContatos = dao.getListaContatos();
        }catch (Exception e){
            e.printStackTrace();
        }
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //menu.add("Ligar");
        //menu.add("Editar");
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ContatoDAO dao = new ContatoDAO(PrincipalActivity.this);
                dao.deletar(contato);
                dao.close();
                carregar();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void carregar(){
        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, getContatos());

        //CASO NAO TENHA NADA NO ADAPTADOR, COLOCO UMA MENSAGEM PARA O USUARIO
        if ( adapter.getCount() < 1 ) {
            List mensagem = new ArrayList();
            mensagem.add("Não há contatos cadastrados!");
            ArrayAdapter adapterMensagem = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mensagem);
            listaContatos.setAdapter(adapterMensagem);
        } else {
            listaContatos.setAdapter(adapter);
        }
    }

}
