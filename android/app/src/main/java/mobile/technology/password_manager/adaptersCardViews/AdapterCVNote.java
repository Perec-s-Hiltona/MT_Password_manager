package mobile.technology.password_manager.adaptersCardViews;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.PopupMenu;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.NoteORM;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.activities.ActAddNote;
import mobile.technology.password_manager.cardViews.CardViewNote;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AdapterCVNote extends RecyclerView.Adapter<AdapterCVNote.ViewHolder> {

    private List<CardViewNote> cardViewNoteList;
    private Context context;

    public AdapterCVNote(List<CardViewNote> cardViewNoteList, Context context){
        this.cardViewNoteList = cardViewNoteList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_note,
                viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        try {

            final CardViewNote cardViewNote = cardViewNoteList.get(position);

            //animation
            YoYo.with(Techniques.ZoomInLeft).duration(1000).repeat(0).playOn(viewHolder.imgOptions);
            viewHolder.txtKeyName.setText(cardViewNote.getKeyName());

            hidePersonalData(viewHolder);
            hideBankData(viewHolder);

            viewHolder.switchShowPersonalData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(viewHolder.switchShowPersonalData.isChecked()){

                        String empty_data = getStringByID(context,R.string.no_data);

                        viewHolder.txtLogin.setText(cardViewNote.getLogin());
                        viewHolder.txtPassword.setText(cardViewNote.getPassword());
                        viewHolder.txtURL.setText(cardViewNote.getURL());

                        if(!viewHolder.txtLogin.getText().toString().equals(empty_data)){
                            viewHolder.txtLogin.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtPassword.getText().toString().equals(empty_data)){
                            viewHolder.txtPassword.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtURL.getText().toString().equals(empty_data)){
                            viewHolder.txtURL.setVisibility(View.VISIBLE);
                        }

                        if(viewHolder.txtLogin.getText().toString().equals(empty_data) &&
                            viewHolder.txtPassword.getText().toString().equals(empty_data) &&
                            viewHolder.txtURL.getText().toString().equals(empty_data)){

                            String empty_field = getStringByID(context,R.string.no_data);

                            viewHolder.txtLogin.setText(empty_field);
                            viewHolder.txtLogin.setVisibility(View.VISIBLE);
                        }

                    }else {

                        hidePersonalData(viewHolder);
                    }
                }
            });

            viewHolder.switchShowBankData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(viewHolder.switchShowBankData.isChecked()){

                        viewHolder.txtBankName.setText(cardViewNote.getBankName());
                        viewHolder.txtCardNumber.setText(cardViewNote.getCardNumber());
                        viewHolder.txtCardHolder.setText(cardViewNote.getCardHolder());
                        viewHolder.txtCardExpiryMonth.setText(cardViewNote.getCardExpiryMonth());
                        viewHolder.txtCardExpiryYear.setText(cardViewNote.getCardExpiryYear());
                        viewHolder.txtCardPIN.setText(cardViewNote.getCardPIN());
                        viewHolder.txtCardCVV.setText(cardViewNote.getCardCVV());

                        String emptyField = getStringByID(context,R.string.no_data);
                        String noDigitData = getStringByID(context,R.string.no_digit_data);

                        if(!viewHolder.txtBankName.getText().toString().equals(emptyField)){
                            viewHolder.txtBankName.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtCardNumber.getText().toString().equals(emptyField)){
                            viewHolder.txtCardNumber.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtCardHolder.getText().toString().equals(emptyField)){
                            viewHolder.txtCardHolder.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtCardExpiryMonth.getText().toString().equals(emptyField)){
                            viewHolder.txtCardExpiryMonth.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtCardExpiryYear.getText().toString().equals(emptyField)){
                            viewHolder.txtCardExpiryYear.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtCardCVV.getText().toString().equals(emptyField)){
                            viewHolder.txtCardCVV.setVisibility(View.VISIBLE);
                        }
                        if(!viewHolder.txtCardPIN.getText().toString().equals(emptyField)){
                            viewHolder.txtCardPIN.setVisibility(View.VISIBLE);
                        }
                        if(viewHolder.txtBankName.getText().toString().equals(emptyField) &&
                                viewHolder.txtCardNumber.getText().toString().equals(noDigitData) &&
                                viewHolder.txtCardHolder.getText().toString().equals(emptyField) &&
                                viewHolder.txtCardExpiryMonth.getText().toString().equals(noDigitData)&&
                                viewHolder.txtCardExpiryYear.getText().toString().equals(noDigitData)&&
                                viewHolder.txtCardCVV.getText().toString().equals(noDigitData)&&
                                viewHolder.txtCardPIN.getText().toString().equals(noDigitData)){

                            viewHolder.txtBankName.setText(getStringByID(context, R.string.no_data));
                            viewHolder.txtBankName.setVisibility(View.VISIBLE);
                            // hide other fields
                            viewHolder.txtCardNumber.setVisibility(View.GONE);
                            viewHolder.txtCardHolder.setVisibility(View.GONE);
                            viewHolder.txtCardExpiryMonth.setVisibility(View.GONE);
                            viewHolder.txtCardExpiryYear.setVisibility(View.GONE);
                            viewHolder.txtCardCVV.setVisibility(View.GONE);
                            viewHolder.txtCardPIN.setVisibility(View.GONE);
                        }

                    }else {

                        hideBankData(viewHolder);
                    }
                }
            });

            viewHolder.txtComment.setText(cardViewNote.getComment());

            viewHolder.imgOptions.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // show option menu
                    PopupMenu popupMenu = new PopupMenu(context,viewHolder.imgOptions);
                    popupMenu.inflate(R.menu.menu_1);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){
                                case R.id.action_change:

                                    // get noteId by CarView ID
                                    String noteID = getItemID(cardViewNote.getItemID());

                                    // send itemID to other Activity
                                    Intent intent = new Intent(context, ActAddNote.class);
                                    intent.putExtra("itemID", noteID);
                                    context.startActivity(intent);

                                    break;

                                case R.id.action_remove:
                                    if(removeNote(cardViewNote.getItemID())){

                                        cardViewNoteList.remove(position);
                                        notifyDataSetChanged();

                                        SweetAlertDialog alertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                                        alertDialog.setTitle(R.string.deleted);
                                        alertDialog.setConfirmText(getStringByID(context, R.string.ok));
                                        alertDialog.show();
                                    }

                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                    YoYo.with(Techniques.Bounce).duration(1000).repeat(0).playOn(viewHolder.imgOptions);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideBankData(final ViewHolder viewHolder){

        viewHolder.txtBankName.setVisibility(View.GONE);
        viewHolder.txtCardNumber.setVisibility(View.GONE);
        viewHolder.txtCardHolder.setVisibility(View.GONE);
        viewHolder.txtCardExpiryMonth.setVisibility(View.GONE);
        viewHolder.txtCardExpiryYear.setVisibility(View.GONE);
        viewHolder.txtCardPIN.setVisibility(View.GONE);
        viewHolder.txtCardCVV.setVisibility(View.GONE);

    }

    private void hidePersonalData(final ViewHolder viewHolder){

        viewHolder.txtLogin.setVisibility(View.GONE);
        viewHolder.txtPassword.setVisibility(View.GONE);
        viewHolder.txtURL.setVisibility(View.GONE);
    }

    private String getItemID(int itemCardViewID){
        // get carID into cardView by CardView ID
        try {
            List<NoteORM> noteORMList = NoteORM.listAll(NoteORM.class);

            for (NoteORM noteORM : noteORMList){
                if (itemCardViewID == noteORM.getId()){
                    String itemID = noteORM.getId().toString();
                    return itemID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String carID = "empty";
        return carID;
    }

    @Override
    public int getItemCount() {
        return cardViewNoteList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtKeyName;
        public TextView txtLogin;
        public TextView txtPassword;
        public TextView txtURL;
        public TextView txtBankName;
        public TextView txtCardNumber;
        public TextView txtCardHolder;
        public TextView txtCardExpiryMonth;
        public TextView txtCardExpiryYear;
        public TextView txtCardPIN;
        public TextView txtCardCVV;
        public TextView txtComment;

        public ImageView imgIconPassword;
        public ImageView imgOptions;

        public Switch switchShowPersonalData;
        public Switch switchShowBankData;

        public RelativeLayout rltPasswordCV;

        public ViewHolder(View itemView) {
            super(itemView);

            txtKeyName = (TextView)itemView.findViewById(R.id.txt_key_name);
            txtLogin = (TextView)itemView.findViewById(R.id.txt_login);
            txtPassword = (TextView)itemView.findViewById(R.id.txt_password);
            txtURL = (TextView)itemView.findViewById(R.id.txt_URL);
            txtBankName = (TextView)itemView.findViewById(R.id.txt_bank_name);
            txtCardNumber = (TextView)itemView.findViewById(R.id.txt_card_number);
            txtCardHolder = (TextView)itemView.findViewById(R.id.txt_card_holder);
            txtCardExpiryMonth = (TextView)itemView.findViewById(R.id.txt_card_expiry_month);
            txtCardExpiryYear = (TextView)itemView.findViewById(R.id.txt_card_expiry_year);
            txtCardPIN = (TextView)itemView.findViewById(R.id.txt_card_PIN);
            txtCardCVV = (TextView)itemView.findViewById(R.id.txt_card_CVV);
            txtComment = (TextView)itemView.findViewById(R.id.txt_comment);

            imgOptions = (ImageView)itemView.findViewById(R.id.imView_options);
            imgIconPassword = (ImageView)itemView.findViewById(R.id.imView_icon_password);

            switchShowPersonalData = (Switch)itemView.findViewById(R.id.switch_show_personal_data);
            switchShowBankData = (Switch)itemView.findViewById(R.id.switch_show_bank_data);

            rltPasswordCV = (RelativeLayout)itemView.findViewById(R.id.rlt_password_cv);
        }
    }

    public static String getStringByID(Context context, Integer stringID){
        Resources resources = context.getResources();
        return resources.getString(resources.getIdentifier(stringID.toString(),
                "string", context.getPackageName()));
    }

    private boolean removeNote(int itemID){
        try {
            List <NoteORM> noteORMList = NoteORM.listAll(NoteORM.class);

            for (NoteORM note : noteORMList){
                if(itemID == note.getId()){
                    note.delete();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
