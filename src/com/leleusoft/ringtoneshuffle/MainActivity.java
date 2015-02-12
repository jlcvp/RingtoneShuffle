package com.leleusoft.ringtoneshuffle;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;



public class MainActivity extends Activity {

	int notificationCount =0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnClickListener {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            return rootView;
        }
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
        	getActivity().findViewById(R.id.button1).setOnClickListener(this);
        	getActivity().findViewById(R.id.button2).setOnClickListener(this);
            
            
        	super.onActivityCreated(savedInstanceState);
        }

		@Override
		public void onClick(View v) {
			Log.i("DEBUG", "onClick");
			switch (v.getId())
			{
			case R.id.button1:
				Notification.Builder mBuilder =
		        new Notification.Builder(getActivity())
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("My notification")
		        .setContentText("Hello World!")
		        .setDefaults(Notification.DEFAULT_ALL);
				
				NotificationManager mNotificationManager=
						(NotificationManager) getActivity()
						.getSystemService(Context.NOTIFICATION_SERVICE);
				
				Notification notification=mBuilder.build();
				
				
				mNotificationManager.notify(5002, notification);
				break;
				
			case R.id.button2:
				Toast.makeText(getActivity(), "Toast", Toast.LENGTH_LONG).show();
				break;
				
			default:
				break;
			}
			
		}
    }
}
