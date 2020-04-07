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
import mobile.technology.password_manager.general.MasterEncrypt;

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

                Intent actAddPassword = new Intent(".act_add_password");
                startActivity(actAddPassword);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getAllPasswords();
    }


    private void getAllPasswords(){
        try {

            List<AppSettings> appSettingsList = AppSettings.listAll(AppSettings.class);

            if(appSettingsList.size() <= 0){

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

            }else {

                String encryptedPassword = "";

                for (AppSettings appSettings:appSettingsList){
                    encryptedPassword = appSettings.getMainEncryptedPassword();
                }

                MasterEncrypt masterEncrypt = new MasterEncrypt();

                String decryptedMainPassword = masterEncrypt.decryptData(encryptedPassword,masterEncrypt.getZeroPassword());

                List <KeyORM> keyORMList = KeyORM.listAll(KeyORM.class);
                cardViewPasswordList = new ArrayList<>();

                String decryptedKeyName ;
                String decryptedLogin;
                String decryptedPassword;
                String decryptedURL;
                String decryptedBankName;
                String decryptedCardNumber;
                String decryptedCardHolder;
                String decryptedCardExpiryMonth;
                String decryptedCardExpiryYear;
                String decryptedCardCVV;
                String decryptedCardPIN;
                String decryptedComment;

                for (KeyORM keyORM:keyORMList){

                    // TODO decrypt data from DB
                    decryptedKeyName = masterEncrypt.decryptData(keyORM.getKeyName(),decryptedMainPassword);
                    decryptedLogin = masterEncrypt.decryptData(keyORM.getLogin(),decryptedMainPassword);
                    decryptedPassword = masterEncrypt.decryptData(keyORM.getPassword(),decryptedMainPassword);
                    decryptedURL = masterEncrypt.decryptData(keyORM.getRowURL(),decryptedMainPassword);
                    decryptedBankName = masterEncrypt.decryptData(keyORM.getBankName(),decryptedMainPassword);
                    decryptedCardNumber = masterEncrypt.decryptData(keyORM.getCardNumber(),decryptedMainPassword);
                    decryptedCardHolder = masterEncrypt.decryptData(keyORM.getCardHolder(),decryptedMainPassword);
                    decryptedCardExpiryMonth = masterEncrypt.decryptData(keyORM.getCardExpiryMonth(),decryptedMainPassword);
                    decryptedCardExpiryYear = masterEncrypt.decryptData(keyORM.getCardExpiryYear(), decryptedMainPassword);
                    decryptedCardCVV = masterEncrypt.decryptData(keyORM.getCardCVV(), decryptedMainPassword);
                    decryptedCardPIN = masterEncrypt.decryptData(keyORM.getCardPIN(),decryptedMainPassword);
                    decryptedComment = masterEncrypt.decryptData(keyORM.getComment(),decryptedMainPassword);

                    //added decrypted data to List
                    cardViewPasswordList.add(new CardViewPassword(keyORM.getId().intValue(),
                            decryptedKeyName,
                            getString(R.string.login) + " : " + decryptedLogin,
                            getString(R.string.password) + " : " + decryptedPassword,
                            getString(R.string.rowURL) + " : " + decryptedURL,
                            getString(R.string.bank_name) + " : " + decryptedBankName,
                            getString(R.string.card_number) + " : "+ decryptedCardNumber,
                            getString(R.string.card_holder) + " : " + decryptedCardHolder,
                            getString(R.string.card_expiry_month) + " : " + decryptedCardExpiryMonth,
                            getString(R.string.card_expiry_year) + " : " + decryptedCardExpiryYear,
                            getString(R.string.card_PIN) + " : " + decryptedCardCVV,
                            getString(R.string.card_CVV) + " : " + decryptedCardPIN,
                            getString(R.string.comment) + " : " + decryptedComment));

                    adapterCVPassword = new AdapterCVPassword(cardViewPasswordList, this);
                    recyclerView.setAdapter(adapterCVPassword);
                }

            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
