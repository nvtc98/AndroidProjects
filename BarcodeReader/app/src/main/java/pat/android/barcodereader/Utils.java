package pat.android.barcodereader;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Trúc on 5/16/2019.
 */
public class Utils {
    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
