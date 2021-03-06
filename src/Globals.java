/**
 * Created by Melvin Wang on 2016-05-03.
 */
public class Globals {
    public static final int MAX_CLIENT_MESSAGES = 500;
    public static final int SENDER_ID = 0;
    public static final int RECEIVER_ID = 1;

    //initialization constants
    public static final String STR_NULL = "";
    public static final int LENGTH_OF_INT = 4;

    //error constants
    public static final int PROCESS_OK = 0;
    public static final int PROCESS_ERROR = -1;

    //position constants for message
    //Structure of message:
    //command + sender + receiver + timeStamp + first record marker + subject + end of subject marker + message text
    public static final int COMMAND_POS = 0;
    public static final int COMMAND_LEN = 1;

    public static final int CLIENT_ID_LEN = 9;
    public static final int CLIENT_POS = COMMAND_POS + COMMAND_LEN;

    public static final int SENDER_POS = COMMAND_POS + COMMAND_LEN;
    public static final int SENDER_LEN = CLIENT_ID_LEN;
    public static final int RECEIVER_LEN = CLIENT_ID_LEN;
    public static final int RECEIVER_POS = SENDER_POS + SENDER_LEN;
    public static final int DATE_TIME_POS = RECEIVER_POS + RECEIVER_LEN;
    public static final int DATE_TIME_LEN = 8;
    public static final int MARKER_POS = DATE_TIME_POS + DATE_TIME_LEN;
    public static final int MARKER_LEN = 1;
    public static final char END_OF_SUBJECT_MARKER = '~';
    public static final int END_OF_SUBJECT_MARKER_LEN = 1;
    public static final int IDENTIFICATION_LEN = SENDER_LEN + RECEIVER_LEN + DATE_TIME_LEN;

    public static final String EMPTY_CLIENT_MESSAGE = STR_NULL;

    //Global variables for client
    public static String[] boxMessages = new String[MAX_CLIENT_MESSAGES];

    //Server command constants
    public static final char SEND_MESSAGE = 'S';
    public static final char IN_BOX = 'I';
    public static final char OUT_BOX = 'O';

    //message and record delimiters;
    public static final char FIRST_RECORD_OF_MESSAGE = '+';

    //Network Constants
    public static final String SERVER_IP_ADDRESS = "10.104.82.71";
    public static final int NET_OK = 0;
    public static final int NET_SEND_ERROR = -1;
    public static final int SENDING_ATTEMPTS_LIMIT = 1000;

    public static final int NET_RECEIVE_ERROR = -2;
    public static final int END_OF_MESSAGES_TRANSMISSION = -2;

    public static final int END_OF_MESSAGE = -1;


}
