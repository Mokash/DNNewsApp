package mo.dnnewsapp;

import android.os.Looper;

/**
 * Created by Mo on 06/11/2016.
 */

public class ThreadPreconditions {
    public static void checkOnMainThread() {
        if (BuildConfig.DEBUG) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("This method should be called from the Main Thread");
            }
        }
    }
}
