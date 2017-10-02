package luongduongquan.com.apporderfood.Utils;

import android.text.TextUtils;
import android.util.Log;


/**
 * Provides utility methods for logging. It is removed automatically in release
 * mode. Set {@link LogUtils#logEnable} = true before using (default is false),
 * may be in application
 */
public class LogUtils {

    private static final String PADDING_STRING = "   ";

    private static final int MAX_LOG = 3000;

    public static boolean logEnable = true;

    private static int sMethodStackLevel = 0;

    // ------------------------------------------------------------------------------------------------

    /**
     * Prints message in DEBUG mode
     *
     * @param tag
     * @param message
     */
    public static void trace(String tag, String message) {
        if (!logEnable)
            return;

        if (tag != null && message != null) {
            String remain = "";
            if (message.length() > MAX_LOG) {
                if (message.length() > 20 * MAX_LOG) {// Fix error overflow
                    String connector = "[...MORE...]";

                    message = message.substring(0, 20 * MAX_LOG - connector.length())
                            + connector + message.substring(message.length() - MAX_LOG);
                    Log.w(tag, "*********** LOG  ******  MESSAGE TO LARGE!!!! **");
                }

                remain = message.substring(MAX_LOG);
                message = message.substring(0, MAX_LOG);
            }

            Log.d(tag, message);

            if (!TextUtils.isEmpty(remain))
                trace(tag, remain);
        }
    }

    /**
     * Prints error message in DEBUG mode
     *
     * @param tag
     * @param message
     */
    public static void error(String tag, String message) {

        if (!logEnable || TextUtils.isEmpty(message))
            return;

        Log.e(tag, message);
    }

    /**
     * Prints error message in DEBUG mode
     *
     * @param tag
     * @param throwable
     */
    public static void error(String tag, Throwable throwable) {

        if (!logEnable || throwable == null)
            return;

        Log.e(tag, " ", throwable);

        throwable.printStackTrace();
    }

    /**
     * Prints warn message in DEBUG mode
     *
     * @param tag
     * @param message
     */
    public static void warn(String tag, String message) {

        if (!logEnable || TextUtils.isEmpty(message))
            return;

        Log.w(tag, message);
    }

    /**
     * Prints info message in DEBUG mode
     *
     * @param tag
     * @param message
     */
    public static void info(String tag, String message) {

        if (!logEnable || TextUtils.isEmpty(message))
            return;

        Log.i(tag, message);
    }



    /**
     * Gets the current method name Use {@link } function
     *
     * @return the name of method that call this method
     */
    private static String getMethodName() {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[4].getMethodName();
    }

    /**
     * Gets the padding string use to append to the begin of the log This will
     * be calculate base on the level of the current method in he stack trace
     *
     * @return
     */
    private static String getPaddingString() {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String strPad = "";
        int nPad = 0;
        if (sMethodStackLevel == 0) {
            sMethodStackLevel = ste.length;
        }

        nPad = ste.length - sMethodStackLevel;

        if (nPad < 0)
            nPad = 0;
        for (int i = 0; i < nPad; i++)
            strPad += PADDING_STRING;
        return strPad;
    }

    /**
     * Logs status and name of object in debug mode
     *
     * @param tag
     * @param header
     * @param object
     */
    public static void logObject(String tag, String header, Object object) {

        if (!logEnable)
            return;

        String message;
        if (object == null) {
            message = "null";
        } else {
            String listenerName = object.getClass().getSimpleName();
            if (TextUtils.isEmpty(listenerName)) {
                message = object.toString();
            } else {
                message = listenerName;
            }
        }
        LogUtils.trace(tag, header + ": " + message);
    }

    /**
     * Prints all attributes of an object
     *
     * @param tag
     * @param object
     */
    public static void printBean(String tag, Object object) {

        if (!logEnable)
            return;

        if (object != null) {
//            Log.w(tag, ToStringBuilder.reflectionToString(object));
        }
    }

    public static String hex(int n) {
        return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
    }

    public static String hex(float f) {
        return hex(Float.floatToRawIntBits(f));
    }

    public static void enter(String tag, String message) {
        if (!logEnable)
            return;

        if (tag != null && message != null) {
            String remain = "";
            if (message.length() > MAX_LOG) {
                if (message.length() > 20 * MAX_LOG) {// Fix error overflow
                    String connector = "[...MORE...]";

                    message = message.substring(0, 20 * MAX_LOG - connector.length())
                            + connector + message.substring(message.length() - MAX_LOG);
                    Log.w(tag, "*********** LOG  ******  MESSAGE TO LARGE!!!! **");
                }

                remain = message.substring(MAX_LOG);
                message = message.substring(0, MAX_LOG);
            }

            Log.d(tag, message + " ──────────┐");

            if (!TextUtils.isEmpty(remain))
                trace(tag, remain);
        }
    }

    public static void leave(String tag, String message) {
        if (!logEnable)
            return;

        if (tag != null && message != null) {
            String remain = "";
            if (message.length() > MAX_LOG) {
                if (message.length() > 20 * MAX_LOG) {// Fix error overflow
                    String connector = "[...MORE...]";

                    message = message.substring(0, 20 * MAX_LOG - connector.length())
                            + connector + message.substring(message.length() - MAX_LOG);
                    Log.w(tag, "*********** LOG  ******  MESSAGE TO LARGE!!!! **");
                }

                remain = message.substring(MAX_LOG);
                message = message.substring(0, MAX_LOG);
            }

            Log.d(tag, message + " ──────────┘");


            if (!TextUtils.isEmpty(remain))
                trace(tag, remain);
        }
    }
}
