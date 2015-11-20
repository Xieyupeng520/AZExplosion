package explosionfield.azz.com.azexplosionfield.explosion;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by azz on 15/11/19.
 * 爆破粒子
 */
public class Particle {

    //原本的值（不可变）
    float originCX;
    float originCY;
    float originRadius;

    //实际的值（可变）
    float cx; //center x of circle
    float cy; //center y of circle
    float radius;

    int color;
    float alpha;

    static Random random = new Random();
    static final int OFFSET = 20; //偏移量

    public static Particle generateParticle(int color, Rect bound) {
        Particle particle = new Particle();
        particle.color = color;
        particle.alpha = 1f;

        particle.radius = random.nextFloat() * ExplosionAnimator.PART_WH;
//        particle.cx = bound.centerX() + (random.nextFloat() - 0.5f) * OFFSET;
//        particle.cy = bound.centerY() + (random.nextFloat() - 0.5f) * OFFSET;
        particle.cx = bound.left + random.nextInt(bound.width());
        particle.cy = bound.top + random.nextInt(bound.height());

        particle.originRadius = particle.radius;
        particle.originCX = particle.cx;
        particle.originCY = particle.cy;
        return particle;
    }

    public static Particle generateParticle(int color, Rect bound, Point point) {
        int row = point.y; //行是高
        int column = point.x; //列是宽

        Particle particle = new Particle();
        particle.color = color;
        particle.alpha = 1f;

        particle.radius = ExplosionAnimator.PART_WH;
        particle.cx = bound.left + ExplosionAnimator.PART_WH * (column - 1);
        particle.cy = bound.top + ExplosionAnimator.PART_WH * (row - 1);

        particle.originRadius = particle.radius;
        particle.originCX = particle.cx;
        particle.originCY = particle.cy;
        return particle;
    }
    public void advance(float factor) {
        cx = cx + factor * random.nextInt(200) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(80);

        radius = radius - factor * random.nextInt(2);

        alpha = 1f - factor + random.nextFloat();
    }
}
