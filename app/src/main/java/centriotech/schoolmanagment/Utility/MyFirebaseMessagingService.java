package centriotech.schoolmanagment.Utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.WelcomeActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    SharedPreference sharedPreference;
    SharedPreferenceConfig sharedPreferenceConfig;
    public static final String CHANNEL_ID="ExampleServiceChannel";
    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {

            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
        if (remoteMessage.getNotification()!=null){
            sharedPreferenceConfig=new SharedPreferenceConfig(this);
            String title =remoteMessage.getNotification().getTitle();
            String message =remoteMessage.getNotification().getBody();
            String click_action=remoteMessage.getNotification().getClickAction();
            sendNotification(title,message,click_action);

        }
    }

    private void sendNotification(String title, String message, String click_action) {

        Intent intent;
        if (click_action.equals("NOTIFICATION")){
            SharedPreferenceConfig  sharedPreferenceConfig=new SharedPreferenceConfig(getApplicationContext());
            intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra("message", "value");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else{
            intent=new Intent(this,WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,CHANNEL_ID).
                setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());



    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    //this method will display the notification
    //We are passing the JSONObject that is received from
    //firebase cloud messaging
    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String click_action=data.getString("click_action");

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());
            Intent intent = null;
            if (click_action.equals("USERDRAWER")){
                intent= new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            //creating an intent for the notification
            Uri defaultSoundUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notificationbuilder=new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(((Uri) defaultSoundUri))
                    .setContentIntent(pendingIntent);



            //if there is no image
//            if(imageUrl.equals("null")){
//                //displaying small notification
//                mNotificationManager.showSmallNotification(title, message, intent);
//            }else{
//                //if there is an image
//                //displaying a big notification
//                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
//            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

}
