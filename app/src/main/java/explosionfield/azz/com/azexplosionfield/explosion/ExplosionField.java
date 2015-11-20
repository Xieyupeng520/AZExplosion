package explosionfield.azz.com.azexplosionfield.explosion;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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
//        Rect rect = new Rect(view.getLeft(),view.getTop(),view.getRight(),view.getBottom());
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect); //得到view相对于整个屏幕的坐标
        Log.d("pos", "(" + rect.left + "," + rect.top + "," + rect.right + "," + rect.bottom + ")");

        rect.offset(0, -25);

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
        /*
         * 为什么屏蔽以下代码段？
         * 如果ImageView直接得到位图，那么当它设置背景（backgroud)时，不会读取到背景颜色
         */
//        if (view instanceof ImageView) {
//            Drawable drawable = ((ImageView)view).getDrawable();
//            if (drawable != null && drawable instanceof BitmapDrawable) {
//                return ((BitmapDrawable) drawable).getBitmap();
//            }
//        }

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
