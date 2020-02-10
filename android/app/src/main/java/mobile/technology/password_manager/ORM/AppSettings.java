package mobile.technology.password_manager.ORM;

import com.orm.SugarRecord;


public class AppSettings extends SugarRecord {

    private String passwordPhrase;

    public AppSettings(){
    }

    public AppSettings(String passwordPhrase) {
        this.passwordPhrase = passwordPhrase;
    }

    public String getPasswordPhrase() {
        return passwordPhrase;
    }

    public void setPasswordPhrase(String passwordPhrase) {
        this.passwordPhrase = passwordPhrase;
    }
}
