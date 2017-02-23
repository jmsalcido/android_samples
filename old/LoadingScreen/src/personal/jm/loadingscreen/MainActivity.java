package personal.jm.loadingscreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * MainActivity.java
 * Nothing else.
 * @author jms
 *
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void loadActivity(View view) {
		// Start a new activity
		startActivity(new Intent(MainActivity.this, LoadedActivity.class));
	}

}
