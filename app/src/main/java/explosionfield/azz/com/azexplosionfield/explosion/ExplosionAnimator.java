package explosionfield.azz.com.azexplosionfield.explosion;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

/**
 * Created by azz on 15/11/19.
 */
public class ExplosionAnimator extends ValueAnimator{
    public static final int PART_WH = 10; //宽高
    private Particle[][] particles;
    private Paint mPaint;
    private View mContainer;

    private Rect mBound;
    public ExplosionAnimator(View view, Bitmap bitmap, Rect bound) {
        mPaint = new Paint();
        mContainer = view;
        mBound = bound;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int partW_Count = w / PART_WH; //横向个数
        int partH_Count = h / PART_WH; //竖向个数

        particles = new Particle[partH_Count][partW_Count];
        for (int row = 0; row < partH_Count; row ++) { //行
            for (int column = 0; column < partW_Count; column ++) { //列
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(column * PART_WH, row * PART_WH);
                particles[row][column] = Particle.generateParticle(color, bound);
            }
        }
    }

    public void draw(Canvas canvas) {
        Log.d("azzz", "draw : particles " + particles.length);
        for (Particle[] particle : particles) {
            for (Particle p : particle) {
                p.advance(0.1f);
                mPaint.setColor(p.color);
                canvas.drawCircle(p.cx, p.cy, p.radius, mPaint);
            }
        }

        mContainer.postInvalidate();
    }

    @Override
    public void start() {
        super.start();
//        mContainer.postInvalidate();
        mContainer.invalidate(mBound);
    }
}
