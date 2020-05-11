package mobile.technology.password_manager.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import mobile.technology.password_manager.R;
import mobile.technology.password_manager.general.MasterAuthorization;


public class ActHome extends Activity implements View.OnClickListener  {

    private static final int PERMISSION_REQUEST_CODE = 123;


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


        if(!MasterAuthorization.getIsAuthorization()){

            Intent intent = new Intent(".act_authentication");
            startActivity(intent);
        }

        if(checkAppPermissions()){

            Toast.makeText(this,"HAS Per", Toast.LENGTH_SHORT).show();
        }else {
            requestPermissionWithRationale();
        }
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

    //permissions requests
    private boolean checkAppPermissions(){
        int res = 0;
        //string array of permitions
        String[] permissions = new String[]{
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        for (String perms : permissions){
            res = checkCallingOrSelfPermission(perms);
            if (res == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    private void requestPerms(){
        String[] permissions = new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions,PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allowed = true;

        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                for (int res : grantResults){
                    // if user granted all permissions.
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                }
                break;
            default:
                // if user not granted permissions.
                allowed = false;
                break;
        }
        if (allowed){
            //user granted all permissions we can perform our task.
            /*
            btn_SOS.setEnabled(true);
            btn_SOS.setBackgroundColor(getResources().getColor(R.color.colorRedDark));
            */
        }
        else {
            // we will give warning to user that they haven't granted permissions.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS))
                    Toast.makeText(this, R.string.perm_denied, Toast.LENGTH_LONG).show();
                else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION))
                    Toast.makeText(this, R.string.perm_denied, Toast.LENGTH_LONG).show();
                else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
                    Toast.makeText(this, R.string.perm_denied, Toast.LENGTH_LONG).show();
                else
                    showNoStoragePermissionSnackbar();
            }
        }
    }
    public void showNoStoragePermissionSnackbar() {
        Snackbar.make(ActHome.this.findViewById(R.id.act_home),
                getResources().getText(R.string.perm_denied), Snackbar.LENGTH_LONG)
                .setAction(R.string.settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openApplicationSettings();

                        Toast.makeText(getApplicationContext(),
                                R.string.open_settings_perm, Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void openApplicationSettings() {
        Intent appSettingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(appSettingsIntent, PERMISSION_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            /*
            btn_SOS.setEnabled(true);
            btn_SOS.setBackgroundColor(getResources().getColor(R.color.colorRedDark));
            */
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.SEND_SMS)) {
            showPermDesc();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            showPermDesc();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)) {
            showPermDesc();
        } else{
            requestPerms();
        }
    }

    private void showPermDesc(){
        final String message = ""+ getResources().getText(R.string.perm_desc) ;
        Snackbar.make(ActHome.this.findViewById(R.id.act_home), message, Snackbar.LENGTH_LONG)
                .setAction(getResources().getText(R.string.permissions) , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPerms();
                    }
                }).show();
    }
}
