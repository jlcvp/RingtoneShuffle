package com.leleusoft.ringtoneshuffle.database;

import android.content.Context;
import android.database.Cursor;

public class RepositorioService extends RepositorioGenerico {

	public RepositorioService(Context ctx) {
		super(ctx);
		super.abrir();
	}
	
	public Cursor query(String SQL)
	{
		Cursor c=null;
		//abre o banco, consulta e fecha
		
		c=super.db.rawQuery(SQL, null);
		return c;
		
	}
	
	
//	"CREATE TABLE tb_files" +
//	"(_id integer primary key autoincrement," +
//	"nome_arquivo text not null," +
//	"path text not null);"
	public void insert(String nome_arquivo, String path)
	{
		super.db.execSQL("INSERT INTO tb_files(nome_arquivo,path) VALUES('" +
				nome_arquivo+"', '"+
				path+"')");
	}
	
	public void delete(String whereClause){
		super.db.execSQL("DELETE FROM tb_files WHERE "+whereClause  );
	}
}
