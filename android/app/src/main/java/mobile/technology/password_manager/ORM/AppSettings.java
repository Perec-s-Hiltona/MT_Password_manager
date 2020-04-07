package mobile.technology.password_manager.ORM;

import com.orm.SugarRecord;


public class AppSettings extends SugarRecord {

    private String mainEncryptedPassword;

    public AppSettings(){

    }

    public AppSettings(String mainEncryptedPassword) {

        this.mainEncryptedPassword = mainEncryptedPassword;
    }

    public String getMainEncryptedPassword() {

        return mainEncryptedPassword;
    }

    public void setMainEncryptedPassword(String mainEncryptedPassword) {
        this.mainEncryptedPassword = mainEncryptedPassword;
    }
}
