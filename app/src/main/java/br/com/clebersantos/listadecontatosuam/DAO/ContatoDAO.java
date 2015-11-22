package br.com.clebersantos.listadecontatosuam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.clebersantos.listadecontatosuam.pojo.Contato;

/**
 * Created by Cleberr on 25/09/2015.
 */
public class ContatoDAO extends SQLiteOpenHelper {

    //CONSTANTES PARA FACILIAR A CRIACAO DO BANCO DE DADOS
    private static  final String DATABASE = "AppUAM";
    private static final int VERSAO = 1;
    private static final String TABELA = "Contatos";

    //CONSTRUTOR PADRAO DA CLASSE
    public ContatoDAO( Context ctx){
        super(ctx, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIO A TABELA NO BANCO
        String sql = "CREATE TABLE " + TABELA + " (" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT UNIQUE NOT NULL, " +
                "telefone TEXT" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //USADO PARA ATUALIZAR OU CORRIGIR A TABELA SE NECESSARIO
        //ESSE METODO SERA USADO NA MUDANCA DE VERSAO DO BANCO
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    /**
     * METODO USADO PARA GRAVAR O CONTATO
     * @param contato
     */
    public void inserirContato( Contato contato ){
        ContentValues cv = new ContentValues();
        cv.put("nome", contato.getNome());
        cv.put("telefone", contato.getTelefone());

        getWritableDatabase().insert(TABELA, null, cv);
    }

    /**
     * METODO QUE RETORNA OS CONTATOS CADASTRADOS NO BANCO
     * @return
     */
    public List<Contato> getListaContatos(){
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        List<Contato> listaContatos = new ArrayList<Contato>();

        while (cursor.moveToNext()){
            Contato c = new Contato();
            c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            c.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            listaContatos.add(c);
        }

        return listaContatos;
    }

    public void deletar(Contato contato){
        String[] args = {contato.getNome()};
        getWritableDatabase().delete(TABELA, "nome=?", args);
    }

    public void atualizar(Contato contato) {
        String[] args = {contato.getNome()};
        ContentValues values = new ContentValues();
        values.put("nome",contato.getNome());
        values.put("telefone",contato.getTelefone());
        getWritableDatabase().update(TABELA, values, "nome=?", args);
    }
}
