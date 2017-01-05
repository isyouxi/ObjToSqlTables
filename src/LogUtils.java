/**
 * Created by youxi on 2016/12/28.
 */
public class LogUtils {

    static boolean isLog = true;

    public static void i(String string) {
        if (isLog)
            System.out.println(string);
    }

}
