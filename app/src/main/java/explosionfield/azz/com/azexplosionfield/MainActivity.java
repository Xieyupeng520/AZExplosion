package explosionfield.azz.com.azexplosionfield;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import explosionfield.azz.com.azexplosionfield.explosion.ExplosionField;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup rootView = (ViewGroup) findViewById(R.id.root);
        final TextView view = (TextView) findViewById(R.id.hello);
        final ExplosionField explosionField = (ExplosionField) findViewById(R.id.explosion);
        ImageView imageView = (ImageView) findViewById(R.id.image);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                explosionField.postInvalidate();
                v.setAlpha(0.1f);

//                view.setOnClickListener(null); // 用过一次就不需要了
            }
        };

        view.setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
