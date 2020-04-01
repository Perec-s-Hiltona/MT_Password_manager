package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.AppSettings;
import mobile.technology.password_manager.ORM.KeyORM;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.adaptersCardViews.AdapterCVPassword;
import mobile.technology.password_manager.cardViews.CardViewPassword;

public class ActPasswords extends AppCompatActivity {

    private FloatingActionButton fabAddPassword;
    private Toolbar toolbarPasswords;

    private List <CardViewPassword> cardViewPasswordList;
    private AdapterCVPassword adapterCVPassword;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_passwords);

        toolbarPasswords = findViewById(R.id.toolbar_passwords);
        toolbarPasswords.setTitle(getResources().getText(R.string.passwords_desc));
        setSupportActionBar(toolbarPasswords);

        // set back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // fab
        fabAddPassword = (FloatingActionButton)findViewById(R.id.fab_add_password);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabAddPassword);

        //set param recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_passwords);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllPasswords();

        fabAddPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkExistPasswordKey();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPasswords();
    }

    private void checkExistPasswordKey(){

        try {

            List<AppSettings> appSettingsList = AppSettings.listAll(AppSettings.class);

            if(appSettingsList.size() > 0){

                Intent actAddPassword = new Intent(".act_add_password");
                startActivity(actAddPassword);

            } else {
                goActAddPasswordKey();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void goActAddPasswordKey(){

        final SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_password_key));
        alertDialog.setContentText((String)getResources().getText(R.string.msg_add_password_key_desc));
        alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));

        alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent intentAddPasswordKey = new Intent(".act_add_main_password");
                startActivity(intentAddPasswordKey);

                alertDialog.dismissWithAnimation();
            }
        });
        alertDialog.show();
    }

    private void getAllPasswords(){
        try {

            List <KeyORM> keyORMList = KeyORM.listAll(KeyORM.class);
            cardViewPasswordList = new ArrayList<>();

            for (KeyORM keyORM:keyORMList){
                // TODO decrypt data from DB

                //added decrypted data to List
                cardViewPasswordList.add(new CardViewPassword(keyORM.getId().intValue(),
                        getString(R.string.key_name)+" : "+keyORM.getKeyName(),
                        getString(R.string.login) + " : " + keyORM.getLogin(),
                        getString(R.string.password) + " : " + keyORM.getPassword(),
                        getString(R.string.URL) + " : " + keyORM.getURL(),
                        getString(R.string.bank_name) + " : " + keyORM.getBankName(),
                        getString(R.string.card_number) + " : "+ keyORM.getCardNumber(),
                        getString(R.string.card_holder) + " : " + keyORM.getCardHolder(),
                        getString(R.string.card_expiry_month) + " : " + keyORM.getCardExpiryMonth(),
                        getString(R.string.card_expiry_year) + " : " +keyORM.getCardExpiryYear(),
                        getString(R.string.card_PIN) + " : " + keyORM.getCardPIN(),
                        getString(R.string.card_CVV) + " : " + keyORM.getCardCVV(),
                        getString(R.string.comment) + " : " + keyORM.getComment()));

                adapterCVPassword = new AdapterCVPassword(cardViewPasswordList, this);
                recyclerView.setAdapter(adapterCVPassword);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
