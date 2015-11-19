package explosionfield.azz.com.azexplosionfield.explosion;

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

        particle.originRadius = particle.radius;
        particle.originCX = particle.cx;
        particle.originCY = particle.cy;
        return particle;
    }

    public void advance(float factor) {
        cx = originCX + factor;
        cy = originCY + factor;

        radius = radius * (1 + factor);
    }
}
