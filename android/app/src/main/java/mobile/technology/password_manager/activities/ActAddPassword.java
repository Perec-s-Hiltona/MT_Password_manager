package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.AppSettings;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.general.MasterEncrypt;

public class ActAddPassword extends AppCompatActivity implements  CompoundButton.OnCheckedChangeListener{

    private FloatingActionButton fabSavePassword;
    private View viewLine1, viewLine2, viewLine3, viewLine4, viewLine5;

    private EditText edtKeyName;
    private EditText edtLogin;
    private EditText edtPassword;
    private EditText edtURL;
    private EditText edtBankName;
    private EditText edtCardNumber;
    private EditText edtCardHolder;
    private EditText edtCardExpiryMonth;
    private EditText edtCardExpiryYear;
    private EditText edtCardCVV;
    private EditText edtCardPIN;
    private EditText edtComment;

    private Switch switchAddBankData;

    private CardView cardViewBankCard;

    String login, password, url,
            bankName, cardNumber, cardHolder, cardExpiryMonth, cardExpiryYear, cardCVV, cardPIN,
            comment;

    String encryptMainPasswordKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppSettings appSettings = new AppSettings();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_password);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_add_password);
        toolbar.setTitle(getResources().getText(R.string.msg_add) + " " + getResources().getText(R.string.password));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewLine1 = (View)findViewById(R.id.view_line_password1);
        viewLine2 = (View)findViewById(R.id.view_line_password2);
        viewLine3 = (View)findViewById(R.id.view_line_password3);
        viewLine4 = (View)findViewById(R.id.view_line_password4);
        viewLine5 = (View)findViewById(R.id.view_line_password5);

        edtKeyName = (EditText)findViewById(R.id.edtKeyName);
        edtLogin = (EditText)findViewById(R.id.edtLogin);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtURL = (EditText)findViewById(R.id.edtURL);
        edtBankName = (EditText)findViewById(R.id.edtBankName);
        edtCardNumber = (EditText)findViewById(R.id.edtCardNumber);
        edtCardHolder = (EditText)findViewById(R.id.edtCardHolder);
        edtCardExpiryMonth = (EditText)findViewById(R.id.edtCardExpiryMonth);
        edtCardExpiryYear = (EditText)findViewById(R.id.edtCardExpiryYear);
        edtCardCVV = (EditText)findViewById(R.id.edtCardCVV);
        edtCardPIN = (EditText)findViewById(R.id.edtCardPIN);
        edtComment = (EditText)findViewById(R.id.edtComment);

        cardViewBankCard = (CardView) findViewById(R.id.cardView_bank_card);

        // setInvisible
        cardViewBankCard.setVisibility(View.GONE);
        viewLine5.setVisibility(View.GONE);

        // animations
        List<View> viewList = new ArrayList<>();
        viewList.add(viewLine1);
        viewList.add(viewLine2);
        viewList.add(viewLine3);
        viewList.add(viewLine4);
        viewList.add(viewLine5);

        for (View view : viewList){
            YoYo.with(Techniques.ZoomInLeft).duration(2000).repeat(0).playOn(view);
        }

        // switcher
        switchAddBankData = (Switch)findViewById(R.id.switch_add_bank_data);
        if(switchAddBankData != null){
            switchAddBankData.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        }

        // fab
        fabSavePassword = (FloatingActionButton)findViewById(R.id.fab_save_password);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabSavePassword);
        fabSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    savePassword();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // for switcher
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            cardViewBankCard.setVisibility(View.VISIBLE);
            viewLine5.setVisibility(View.VISIBLE);
        }else {
            cardViewBankCard.setVisibility(View.GONE);
            viewLine5.setVisibility(View.GONE);
        }
    }

    private void savePassword() throws Exception {
        Toast.makeText(this, "Pressed SAVE", Toast.LENGTH_SHORT).show();
        String keyName = edtKeyName.getText().toString();

        if(keyName.length() == 0){

            SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_data));
            alertDialog.setContentText((String)getResources().getText(R.string.password_name));
            alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));
            alertDialog.show();

        }else {

            login = edtLogin.getText().toString();
            password = edtPassword.getText().toString();
            url = edtURL.getText().toString();

            if (switchAddBankData.isChecked()){

                bankName = edtBankName.getText().toString();
                cardNumber = edtCardNumber.getText().toString();
                cardHolder = edtCardHolder.getText().toString();
                cardExpiryMonth = edtCardExpiryMonth.getText().toString();
                cardExpiryYear = edtCardExpiryYear.getText().toString();
                cardCVV = edtCardCVV.getText().toString();
                cardPIN = edtCardPIN.getText().toString();
            }

            comment = edtComment.getText().toString();

            // get main password key
            List<AppSettings> appSettingsList = AppSettings.listAll(AppSettings.class);

            for (AppSettings appSettings : appSettingsList){

                encryptMainPasswordKey = appSettings.getValuePassword();
                System.out.println("encrypted password key :"+ encryptMainPasswordKey);
            }

            //encrypt rows and save in DB
            if(login.length() < 0){
                login = (String) getResources().getText(R.string.empty_field);
            }
            if(password.length() < 0){
                password = (String)getResources().getText(R.string.empty_field);
            }
            if(url.length() < 0){
                url = (String)getResources().getText(R.string.empty_field);
            }

            if(switchAddBankData.isChecked()){
                if(bankName.length() < 0){
                    bankName = (String)getResources().getText(R.string.empty_field);
                }
                if(cardNumber.length() < 0){
                    cardNumber = (String)getResources().getText(R.string.empty_field);
                }
                if(cardHolder.length() < 0){
                    cardHolder = (String)getResources().getText(R.string.empty_field);
                }
                if(cardExpiryMonth.length() < 0){
                    cardExpiryMonth = (String)getResources().getText(R.string.empty_field);
                }
                if(cardExpiryYear.length() < 0){
                    cardExpiryYear = (String)getResources().getText(R.string.empty_field);
                }
                if(cardCVV.length() < 0){
                    cardCVV = (String)getResources().getText(R.string.empty_field);
                }
                if(cardPIN.length() < 0){
                    cardPIN = (String)getResources().getText(R.string.empty_field);
                }
            }
            if(comment.length() < 0){
                comment = (String)getResources().getText(R.string.empty_field);
            }

            // encrypt fields
            //TODO

        }
    }
}
