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
import mobile.technology.password_manager.ORM.NoteORM;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.adaptersCardViews.AdapterCVNote;
import mobile.technology.password_manager.cardViews.CardViewNote;
import mobile.technology.password_manager.general.MasterEncrypt;

public class ActNotes extends AppCompatActivity {

    private FloatingActionButton fabAddPassword;
    private Toolbar toolbarNotes;

    private List <CardViewNote> cardViewNoteList;
    private AdapterCVNote adapterCVNote;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notes);

        toolbarNotes = findViewById(R.id.toolbar_notes);
        toolbarNotes.setTitle(getResources().getText(R.string.my_notes));
        setSupportActionBar(toolbarNotes);

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

        getAllNotes();

        fabAddPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent actAddPassword = new Intent(".act_add_note");
                startActivity(actAddPassword);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getAllNotes();
    }


    private void getAllNotes(){
        try {

            List<AppSettings> appSettingsList = AppSettings.listAll(AppSettings.class);

            if(appSettingsList.size() <= 0){

                final SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_main_password));
                alertDialog.setContentText((String)getResources().getText(R.string.msg_main_password_desc));
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

                List <NoteORM> noteORMList = NoteORM.listAll(NoteORM.class);
                cardViewNoteList = new ArrayList<>();

                String decryptedNoteName;
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

                for (NoteORM noteORM : noteORMList){

                    // decrypt data from DB
                    decryptedNoteName = masterEncrypt.decryptData(noteORM.getNoteName(),decryptedMainPassword);
                    decryptedLogin = masterEncrypt.decryptData(noteORM.getLogin(),decryptedMainPassword);
                    decryptedPassword = masterEncrypt.decryptData(noteORM.getPassword(),decryptedMainPassword);
                    decryptedURL = masterEncrypt.decryptData(noteORM.getURL(),decryptedMainPassword);
                    decryptedBankName = masterEncrypt.decryptData(noteORM.getBankName(),decryptedMainPassword);
                    decryptedCardNumber = masterEncrypt.decryptData(noteORM.getCardNumber(),decryptedMainPassword);
                    decryptedCardHolder = masterEncrypt.decryptData(noteORM.getCardHolder(),decryptedMainPassword);
                    decryptedCardExpiryMonth = masterEncrypt.decryptData(noteORM.getCardExpiryMonth(),decryptedMainPassword);
                    decryptedCardExpiryYear = masterEncrypt.decryptData(noteORM.getCardExpiryYear(), decryptedMainPassword);
                    decryptedCardCVV = masterEncrypt.decryptData(noteORM.getCardCVV(), decryptedMainPassword);
                    decryptedCardPIN = masterEncrypt.decryptData(noteORM.getCardPIN(),decryptedMainPassword);
                    decryptedComment = masterEncrypt.decryptData(noteORM.getComment(),decryptedMainPassword);

                    //added decrypted data to List

                    cardViewNoteList.add(new CardViewNote(noteORM.getId().intValue(),
                            decryptedNoteName,
                            decryptedLogin,
                            decryptedPassword,
                            decryptedURL,

                            decryptedBankName,
                            decryptedCardNumber,
                            decryptedCardHolder,
                            decryptedCardExpiryMonth,
                            decryptedCardExpiryYear,
                            decryptedCardCVV,
                            decryptedCardPIN,
                            getString(R.string.comment) + " : " + decryptedComment));

                    adapterCVNote = new AdapterCVNote(cardViewNoteList, this);
                    recyclerView.setAdapter(adapterCVNote);
                }

            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
