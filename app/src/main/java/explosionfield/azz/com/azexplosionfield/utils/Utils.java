package explosionfield.azz.com.azexplosionfield.utils;

import android.content.res.Resources;

/**
 * Created by azz on 15/11/19.
 */
public class Utils {
    /**
     * 密度
     */
    public static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(int dp) {
        return Math.round(dp * DENSITY);
    }
}
