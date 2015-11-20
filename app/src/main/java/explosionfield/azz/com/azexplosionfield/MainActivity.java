package explosionfield.azz.com.azexplosionfield;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import explosionfield.azz.com.azexplosionfield.explosion.ExplosionField;

public class MainActivity extends Activity {
    View.OnClickListener onClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        final ViewGroup rootView = (ViewGroup) findViewById(R.id.root);
//        final TextView view = (TextView) findViewById(R.id.hello);
        final ExplosionField explosionField = (ExplosionField) findViewById(R.id.explosion);
//        ImageView imageView = (ImageView) findViewById(R.id.image);


        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                v.setAlpha(0.1f);

//                view.setOnClickListener(null); // 用过一次就不需要了
            }
        };

//        view.setOnClickListener(onClickListener);
//        imageView.setOnClickListener(onClickListener);

        addListener(rootView);
    }

    private void addListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0 ; i < count; i++) {
                addListener(viewGroup.getChildAt(i));
            }
        } else {
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
        }
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
