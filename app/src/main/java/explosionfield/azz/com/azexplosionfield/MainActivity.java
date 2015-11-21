package explosionfield.azz.com.azexplosionfield;

import android.app.Activity;
import android.os.Bundle;

import explosionfield.azz.com.azexplosionfield.explosion.ExplosionField;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_az);

        ExplosionField explosionField = new ExplosionField(this);

        explosionField.addListener(findViewById(R.id.root));
    }


}
