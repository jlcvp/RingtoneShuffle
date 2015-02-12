package com.leleusoft.ringtoneshuffle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class RingtoneShuffleIntentService extends IntentService {

	public RingtoneShuffleIntentService() {
		super("RingtoneShuffleIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("DEBUG", "Service - onHandleIntent");
		setRingtone();
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
		Cursor c = getContentResolver().query(uri, proj , ""+MediaStore.MediaColumns.DATA+" like '"+k.getAbsolutePath()+"'",null, null);
		
		Uri newUri = null;
		if(c!=null && c.getCount()>0) //já está no banco
		{
			this.getContentResolver().delete(uri, ""+MediaStore.MediaColumns.DATA+" like '"+k.getAbsolutePath()+"'", null);
			
		}		
			newUri = this.getContentResolver().insert(uri, values);
		
		
		RingtoneManager.setActualDefaultRingtoneUri(
		  this,
		  RingtoneManager.TYPE_NOTIFICATION,
		  newUri
		);
	}
	
	private File getRandFile()
	{
		List<String> results = new ArrayList<String>();
		String basePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/portalNotification";
		File[] files = new File(basePath).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 
		String path=null;
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		if(!results.isEmpty())
		{
			Random rnd = new Random(System.currentTimeMillis());
			int i = rnd.nextInt(results.size());
			path = basePath+"/"+results.get(i);			
		}
		Log.i("DEBUG", "File = "+path);
		return new File(path);
	}

}
