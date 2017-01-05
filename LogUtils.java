/**
 * Created by is_yo on 2016/12/28.
 */
public class LogUtils {

    public static void i(String string){
        System.out.println(string);
    }


    public static void i(boolean isLog,String string){
        if(isLog)
            System.out.println(string);
    }
}
