package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.UserData;
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

                openPageUserData();
            }
        });
    }

    private void openPageUserData(){

        List<UserData> userDataList = UserData.listAll(UserData.class);

        if(userDataList.size()==0){

            final SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_user_data));
            alertDialog.setContentText((String)getResources().getText(R.string.msg_user_data_desc));
            alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));

            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intentAddPasswordKey = new Intent(".act_add_user");
                    startActivity(intentAddPasswordKey);

                    alertDialog.dismissWithAnimation();
                }
            });
            alertDialog.show();
        }else {

            String userId = "";

            for(UserData userData: userDataList){

                userId = String.valueOf(userData.getId());
            }

            Intent intent = new Intent(".act_add_user");
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    }
}
