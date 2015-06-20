package in.planyourhealth.planyourhealth;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sajal on 24-04-2015.
 */
class Globals {
    private static String listOfEmails[];

    public static String[] getListOfEmails() {
        return listOfEmails;
    }
    public static Context context;
    public static boolean[] mClicked={false,false,false,false,false,false,false};

    public static void setListOfEmails(String[] listOfEmails) {
        Globals.listOfEmails = listOfEmails;
    }



}
