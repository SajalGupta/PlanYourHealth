package in.planyourhealth.planyourhealth;

/**
 * Created by Sajal on 24-04-2015.
 */
class Globals {
    private static String listOfEmails[];

    public static String[] getListOfEmails() {
        return listOfEmails;
    }

    public static void setListOfEmails(String[] listOfEmails) {
        Globals.listOfEmails = listOfEmails;
    }
}
