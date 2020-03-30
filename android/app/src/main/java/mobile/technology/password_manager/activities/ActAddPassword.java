package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.AppSettings;
import mobile.technology.password_manager.ORM.KeyORM;
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

    MasterEncrypt masterEncrypt;

    String login, password, url,
            bankName, cardNumber, cardHolder, cardExpiryMonth, cardExpiryYear, cardCVV, cardPIN,
            comment;

    String encryptedMainPassword;

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

        masterEncrypt = new MasterEncrypt();
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

            // get main password from DB
            List<AppSettings> appSettingsList = AppSettings.listAll(AppSettings.class);

            for (AppSettings appSettings : appSettingsList){
                encryptedMainPassword = appSettings.getValuePassword();
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

                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                if(bankName.length() < 1 || cardNumber.length() < 1 || cardHolder.length() < 1 ||
                    cardExpiryMonth.length() < 1 || cardExpiryYear.length() < 1 ||
                    cardCVV.length() < 1 || cardPIN.length() < 1){

                    SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                    alertDialog.setTitleText((String) getResources().getText(R.string.msg_check_correct_data));
                    alertDialog.setContentText((String)getResources().getText(R.string.bank_data));
                    alertDialog.setConfirmText((String)getResources().getText(R.string.msg_ok));
                    alertDialog.show();
                    return;

                } else if (Integer.parseInt(edtCardExpiryMonth.getText().toString()) > 12){

                    SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                    alertDialog.setTitleText((String) getResources().getText(R.string.msg_check_correct_data));
                    alertDialog.setContentText((String)getResources().getText(R.string.card_expiry_month));
                    alertDialog.setConfirmText((String)getResources().getText(R.string.msg_ok));
                    alertDialog.show();
                    return;

                } else if (Integer.parseInt(edtCardExpiryYear.getText().toString()) < currentYear){

                    SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                    alertDialog.setTitleText((String) getResources().getText(R.string.msg_check_correct_data));
                    alertDialog.setContentText((String)getResources().getText(R.string.card_expiry_year));
                    alertDialog.setConfirmText((String)getResources().getText(R.string.msg_ok));
                    alertDialog.show();
                    return;
                }
            }
            if(comment.length() < 0){
                comment = (String)getResources().getText(R.string.empty_field);
            }

            String decryptedMainPassword = masterEncrypt.decryptData(encryptedMainPassword,masterEncrypt.getZeroPassword());

            // encrypt fields
            String encKeyName = masterEncrypt.encryptData(keyName, decryptedMainPassword);
            String encLogin = masterEncrypt.encryptData(login,decryptedMainPassword);
            String encPassword = masterEncrypt.encryptData(password, decryptedMainPassword);
            String encURL = masterEncrypt.encryptData(url, decryptedMainPassword);

            String encBankName = masterEncrypt.encryptData(bankName,decryptedMainPassword);
            String encCardNumber = masterEncrypt.encryptData(cardNumber,decryptedMainPassword);
            String encCardHolder = masterEncrypt.encryptData(cardHolder,decryptedMainPassword);
            String encCardExpiryMonth = masterEncrypt.encryptData(cardExpiryMonth,decryptedMainPassword);
            String encCardExpiryYear = masterEncrypt.encryptData(cardExpiryYear,decryptedMainPassword);
            String encCardCVV = masterEncrypt.encryptData(cardCVV,decryptedMainPassword);
            String encCardPIN = masterEncrypt.encryptData(cardPIN,decryptedMainPassword);

            String encComment = masterEncrypt.encryptData(comment,decryptedMainPassword);

            // save encrypted data in DB
            KeyORM keyORM = new KeyORM();
            keyORM.setKeyName(encKeyName);
            keyORM.setLogin(encLogin);
            keyORM.setPassword(encPassword);
            keyORM.setURL(encURL);
            keyORM.setBankName(encBankName);
            keyORM.setCardNumber(encCardNumber);
            keyORM.setCardHolder(encCardHolder);
            keyORM.setCardExpiryMonth(encCardExpiryMonth);
            keyORM.setCardExpiryYear(encCardExpiryYear);
            keyORM.setCardCVV(encCardCVV);
            keyORM.setCardPIN(encCardPIN);
            keyORM.setComment(encComment);

            SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
            alertDialog.setTitleText((String)getResources().getText(R.string.msg_saved));
            alertDialog.show();

            // clear edit texts
            edtKeyName.setText("");
            edtLogin.setText("");
            edtPassword.setText("");
            edtURL.setText("");

            edtBankName.setText("");
            edtCardNumber.setText("");
            edtCardHolder.setText("");
            edtCardExpiryMonth.setText("");
            edtCardExpiryYear.setText("");
            edtCardCVV.setText("");
            edtCardPIN.setText("");

            edtComment.setText("");
        }
    }
}
