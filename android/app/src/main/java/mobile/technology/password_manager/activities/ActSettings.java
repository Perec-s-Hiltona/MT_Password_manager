package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import mobile.technology.password_manager.R;

public class ActSettings extends AppCompatActivity {

    private Toolbar toolbarSettings;
    private LinearLayout lnlUpdateUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);

        toolbarSettings = findViewById(R.id.toolbar_settings);
        toolbarSettings.setTitle(getResources().getText(R.string.settings));
        setSupportActionBar(toolbarSettings);

        // set back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        lnlUpdateUserData = (LinearLayout)findViewById(R.id.lnl_update_user_data);
        lnlUpdateUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo
            }
        });
    }
}
