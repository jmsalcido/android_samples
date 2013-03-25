package personal.jm.loadingscreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void loadActivity(View view) {
		startActivity(new Intent(MainActivity.this, LoadedActivity.class));
	}

}
