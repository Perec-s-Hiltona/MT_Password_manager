package mobile.technology.password_manager.ORM;

import com.orm.SugarRecord;


public class UserData extends SugarRecord {

    private String login;
    private String password;
    private String phone;
    private String mail;

    public UserData(){

    }

    public UserData(String login, String password, String phone, String mail) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
