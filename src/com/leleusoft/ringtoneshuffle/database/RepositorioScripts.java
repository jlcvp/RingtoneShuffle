package com.leleusoft.ringtoneshuffle.database;

import android.content.Context;
import android.util.Log;

public class RepositorioScripts extends RepositorioGenerico {

	private static final String NOME_BANCO = "DownloadService";
	private static final String SCRIPT_DELETE_TABELA= "DROP TABLE IF EXISTS tb_files";
	
	private static final String[] SCRIPT_CRIAR_TABELA = new String[] {
		
		"CREATE TABLE tb_files" +
			"(_id integer primary key autoincrement," +
			"nome_arquivo text not null," +
			"path text not null);"
		};
	
	private static final int VERSAO_BANCO = 1;

	private SQLiteHelper dbHelper;
	
	public RepositorioScripts(Context ctx) {
		super(ctx);
		dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_CRIAR_TABELA, SCRIPT_DELETE_TABELA);
		Log.i("BANCO", "dbHelper de boooa");
		db = dbHelper.getWritableDatabase();
		Log.i("BANCO","getwritable funfou");		
	}
	
	@Override
	public void fechar(){
		if(dbHelper != null){
			dbHelper.close();
		}
	}

}

	
	