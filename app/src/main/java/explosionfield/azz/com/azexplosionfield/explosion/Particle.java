package explosionfield.azz.com.azexplosionfield.explosion;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by azz on 15/11/19.
 * 爆破粒子
 */
public class Particle {

    float cx; //center x of circle
    float cy; //center y of circle
    int color;
    float radius;

    static Random random = new Random();
    static final int OFFSET = 20; //偏移量

    public static Particle generateParticle(int color, Rect bound) {
        Particle particle = new Particle();
        particle.color = color;
        particle.radius = random.nextFloat() * ExplosionAnimator.PART_WH;
//        particle.cx = bound.centerX() + (random.nextFloat() - 0.5f) * OFFSET;
//        particle.cy = bound.centerY() + (random.nextFloat() - 0.5f) * OFFSET;
        particle.cx = bound.left + random.nextInt(bound.width());
        particle.cy = bound.top + random.nextInt(bound.height());

        return particle;
    }
}
