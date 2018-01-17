/**
 * Created by Melvin Wang on 2016-05-03.
 */
public class EmailClient {
    public static void main(String args[]) {

        for (int i = 0; i < Globals.boxMessages.length; i++) {
            Globals.boxMessages[i] = Globals.EMPTY_CLIENT_MESSAGE;
        }

        int error = MailTransfers.EmailClientRequestAllMail(Globals.RECEIVER_ID);
        if (error == Globals.PROCESS_OK) {
            EMailClientGUI gui = new EMailClientGUI();
        }
    }
}
