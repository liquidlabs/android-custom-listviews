package ca.liquidlabs.addressbook;

import info.itsalif.addressbook.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ActivityHelper {

	private static Activity currentActivity;
	
	public static final byte PHONE_CALL = 1;
	public static final byte EMAIL = 2;
	
	// private constructor -> utility class
	private ActivityHelper() { }
	
	/**
	 * Starts an Activity from the currentActivity
	 * 
	 * @param type	The type of Intent [Phone Call, Email etc.]
	 * @param data	The data to be processed for the Activity
	 */
	public static void startActivity(byte type, String data) {
		
		if (ActivityHelper.currentActivity == null) return;
		
		if (type == ActivityHelper.PHONE_CALL) {
			// make the call			
			ActivityHelper.currentActivity
				.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + data)));
		}
		else {
			// its for sending email, it could also be done by using Bundle
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] {data});
			intent.putExtra(Intent.EXTRA_SUBJECT, ActivityHelper.currentActivity.getString(R.string.email_subject)); 

			
			String emailClientError = ActivityHelper.currentActivity.getString(R.string.email_client_error);			
			ActivityHelper.currentActivity.startActivity(Intent.createChooser(intent, emailClientError));
		}
		
	}
	
	/**
	 * Updates/Sets the currentActivity
	 * @param a Activity which is currently running on foreground
	 */
	public static void setActivity(Activity a) {
		ActivityHelper.currentActivity = a;
	}
}
