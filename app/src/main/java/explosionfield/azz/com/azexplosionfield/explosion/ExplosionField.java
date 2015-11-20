package explosionfield.azz.com.azexplosionfield.explosion;

import android.animation.Animator;
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
        for (ExplosionAnimator animator : explosionAnimators) {
            animator.draw(canvas);
        }
    }

    /**
     * 爆破
     * @param view 使得该view爆破
     */
    public void explode(final View view) {
        Rect rect = new Rect(view.getLeft(),view.getTop(),view.getRight(),view.getBottom());
        final ExplosionAnimator animator = new ExplosionAnimator(this, createBitmapFromView(view), rect);
        explosionAnimators.add(animator);

        animator.setFloatValues(0.0f, 1.0f);
        animator.setDuration(1500);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setAlpha(1f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView)view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, view.getWidth(), view.getHeight(), false);
                return bitmap;
            }
        }
        //view.clearFocus(); //不同焦点状态显示的可能不同

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        Log.e("azzz", "图像宽 = " + bitmap.getWidth() + " 图像 高= " + bitmap.getHeight());
        Log.e("azzz", "view宽 = " + view.getHeight() + " view 高= " + view.getHeight());
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
