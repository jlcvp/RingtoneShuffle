package com.leleusoft.ringtoneshuffle;

import com.leleusoft.ringtoneshuffle.NotificationToastCatchService.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * Recebe o broadcast do bagui de notification e encaminha pra o service enfileirar
 * @author joaoleonardo
 *
 */
public class NotificationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
//		 Intent mIntent = new Intent(Constants.ACTION_CATCH_NOTIFICATION);
//         mIntent.putExtra(Constants.EXTRA_PACKAGE, sourcePackageName);
//         mIntent.putExtra(Constants.EXTRA_MESSAGE, notificationMsg);
		
		Intent it = new Intent(context, RingtoneShuffleIntentService.class);
		it.putExtra(NotificationToastCatchService.Constants.EXTRA_PACKAGE, intent.getStringExtra(NotificationToastCatchService.Constants.EXTRA_PACKAGE));
		it.putExtra(NotificationToastCatchService.Constants.EXTRA_MESSAGE, intent.getStringExtra(NotificationToastCatchService.Constants.EXTRA_MESSAGE));
		context.startService(it);
	}
	

}
