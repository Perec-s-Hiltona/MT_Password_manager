package mobile.technology.password_manager.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import mobile.technology.password_manager.ORM.UserData;
import mobile.technology.password_manager.R;

public class ActHome extends Activity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        LinearLayout passwords = (LinearLayout)findViewById(R.id.linl_passwords);
        LinearLayout settings = (LinearLayout)findViewById(R.id.linl_settings);

        List<LinearLayout> linearLayoutList = new ArrayList<>();
        linearLayoutList.add(passwords);
        linearLayoutList.add(settings);

        // animation
        for (LinearLayout linearLayout: linearLayoutList){
            YoYo.with(Techniques.BounceIn).duration(2000).repeat(0).playOn(linearLayout);
        }

        // setOnClickListener
        passwords.setOnClickListener((View.OnClickListener) this);
        settings.setOnClickListener((View.OnClickListener) this);

        //checkUser();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.linl_passwords:
                Intent act_passwords = new Intent(".act_notes");
                startActivity(act_passwords);
                break;
            case R.id.linl_settings:
                Intent act_settings = new Intent(".act_settings");
                startActivity(act_settings);
                break;
        }
    }

    private void checkUser() {

        List<UserData> userDataList = UserData.listAll(UserData.class);

        if (userDataList.size() > 0) {

            Intent act_authentication = new Intent(".act_authentication");
            startActivity(act_authentication);
        }
    }
}
