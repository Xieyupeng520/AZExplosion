package explosionfield.azz.com.azexplosionfield.explosion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by azz on 15/11/19.
 */
public class ExplosionField extends View{
    private static final String TAG = "ExplosionField";
    private static final Canvas mCanvas = new Canvas();
    private ArrayList<ExplosionAnimator> explosionAnimators;

    public ExplosionField(Context context) {
        super(context);
        init();
    }

    public ExplosionField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        explosionAnimators = new ArrayList<ExplosionAnimator>();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("azzz", "onDraw");
        for (ExplosionAnimator animator : explosionAnimators) {
            animator.draw(canvas);
        }
    }

    /**
     * 爆破
     * @param view 使得该view爆破
     */
    public void explode(View view) {
        Rect rect = new Rect(view.getLeft(),view.getTop(),view.getRight(),view.getBottom());
        ExplosionAnimator animator = new ExplosionAnimator(view, createBitmapFromView(view), rect);
        explosionAnimators.add(animator);
    }

    private Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView)view).getDrawable();
            Log.d(TAG, "view is imageview , drawable is " + drawable);
            if (drawable != null && drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        //view.clearFocus(); //不同焦点状态显示的可能不同

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        if (bitmap != null) {
            synchronized (mCanvas) {
                mCanvas.setBitmap(bitmap);
                view.draw(mCanvas);
                mCanvas.setBitmap(null); //清除引用
            }
        }
        return bitmap;
    }
}
