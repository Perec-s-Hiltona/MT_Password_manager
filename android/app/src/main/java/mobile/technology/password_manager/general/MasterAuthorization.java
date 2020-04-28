package mobile.technology.password_manager.general;

import java.util.List;

import mobile.technology.password_manager.ORM.UserData;


public class MasterAuthorization{

    private static boolean isAuthorization;

    public static boolean getIsAuthorization() {
        return isAuthorization;
    }

    public static void setIsAuthorization(boolean isAuthorization) {
        MasterAuthorization.isAuthorization = isAuthorization;
    }
}
