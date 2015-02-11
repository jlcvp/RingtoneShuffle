package com.leleusoft.ringtoneshuffle;

import java.io.File;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

public class RingtoneShuffleIntentService extends IntentService {

	public RingtoneShuffleIntentService(String name) {
		super(name);		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//verificar algum extra aqui e setar o ringtone
		
	}
	
	
	private void setRingtone()
	{
		File k = getRandFile();

		ContentValues values = new ContentValues();
		values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
		values.put(MediaStore.MediaColumns.TITLE, k.getName());
		values.put(MediaStore.MediaColumns.SIZE, k.length());
		values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
		values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
		values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
		values.put(MediaStore.Audio.Media.IS_ALARM, false);
		values.put(MediaStore.Audio.Media.IS_MUSIC, false);

		//Insert it into the database
		Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
		String[] proj  = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.TITLE};
		Cursor c = getContentResolver().query(uri, proj , "WHERE "+MediaStore.MediaColumns.DATA+"="+k.getAbsolutePath(),null, null);
		
		Uri newUri = null;
		if(c!=null && c.getCount()>0) //já está no banco
		{
			newUri = this.getContentResolver().insert(uri, values);
		}
		else
		{
			newUri = uri;
		}
		
		RingtoneManager.setActualDefaultRingtoneUri(
		  this,
		  RingtoneManager.TYPE_NOTIFICATION,
		  newUri
		);
	}
	
	private File getRandFile()
	{
		
		return new File("hue");
	}

}
