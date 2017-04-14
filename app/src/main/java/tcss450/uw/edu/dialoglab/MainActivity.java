package tcss450.uw.edu.dialoglab;

import android.support.v4.app.DialogFragment;
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

        if(fragment != null)
            fragment.show(getSupportFragmentManager(), "launch");
    }

}
