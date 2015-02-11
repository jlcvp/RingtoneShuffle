package com.leleusoft.ringtoneshuffle;

import android.app.IntentService;
import android.content.Intent;

public class RingtoneShuffleIntentService extends IntentService {

	public RingtoneShuffleIntentService(String name) {
		super(name);		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//verificar algum extra aqui e setar o ringtone
	}

}
