package tcss450.uw.edu.dialoglab;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launch(View view){
        DialogFragment fragment = null;
        if(view.getId() == R.id.fire_missiles_button){
            fragment = new FireMissilesDialogFragment();
        }
        else if(view.getId() == R.id.list_button) {
            fragment = new ListDialogFragment();
        }
        else if(view.getId() == R.id.multi_list_button){
            fragment = new MultiListDialogFragment();
        }
        else if(view.getId() == R.id.custom_button){
            fragment = new CustomDialogFragment();
        }
        else if(view.getId() == R.id.time_button){
            fragment = new TimePickerDialogFragment();
        }
        else if(view.getId() == R.id.date_button){
            fragment = new DatePickerDialogFragment();
        }
        else if(view.getId() == R.id.notification_button){
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.notification_bg)
                            .setContentTitle("My notification")
                            .setContentText(Integer.valueOf(this.getTaskId()).toString());
// Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(this.getTaskId(), mBuilder.build());
        }
        if(fragment != null)
            fragment.show(getSupportFragmentManager(), "launch");
    }

}
