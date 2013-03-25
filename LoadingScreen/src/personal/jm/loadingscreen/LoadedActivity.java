package personal.jm.loadingscreen;

import java.io.IOException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class LoadedActivity extends Activity {

	private View mContentView;
	private View mLoadingView;
	private int mShortAnimationDuration;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loaded);
		mContentView = findViewById(R.id.content);
		mLoadingView = findViewById(R.id.loading_spinner);
		mContentView.setVisibility(View.GONE);
		mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
		ImageView imgView = (ImageView) findViewById(R.id.imageview);
		imgView.setTag("http://i.imgur.com/8or6G.jpg");
		imgView.setOnTouchListener(listenerMListener);
		new DownloadImageTask().execute(imgView);
	}
	
	private OnTouchListener listenerMListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = MotionEventCompat.getActionMasked(event);
	        String DEBUG_TAG = "HUEHUE";
		    switch(action) {
		        case (MotionEvent.ACTION_DOWN) :
		            Log.d(DEBUG_TAG,"Action was DOWN");
		            return true;
		        case (MotionEvent.ACTION_MOVE) :
		            Log.d(DEBUG_TAG,"Action was MOVE");
		            return true;
		        case (MotionEvent.ACTION_UP) :
		            Log.d(DEBUG_TAG,"Action was UP");
		            return true;
		        case (MotionEvent.ACTION_CANCEL) :
		            Log.d(DEBUG_TAG,"Action was CANCEL");
		            return true;
		        case (MotionEvent.ACTION_OUTSIDE) :
		            Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
		                    "of current screen element");
		            return true;      
		        default : 
		            return onTouchEvent(event);
		            }
		    }
	};

	private class DownloadImageTask extends AsyncTask<ImageView, Void, Bitmap> {
		
		private ImageView image;
		
	    /** The system calls this to perform work in a worker thread and
	      * delivers it the parameters given to AsyncTask.execute() */
	    protected Bitmap doInBackground(ImageView... images) {
	    	image = images[0];
	    	String url = (String)image.getTag();
	        return loadImageFromNetwork(url);
	    }
	    
	    @Override
	    protected void onPostExecute(Bitmap result) {
	    	// Decide which view to hide and which to show.
	        final View showView = mContentView;
	        final View hideView = mLoadingView;
	        
	        //image.setImageBitmap(result);
	        image.setImageBitmap(result);

	        // Set the "show" view to 0% opacity but visible, so that it is visible
	        // (but fully transparent) during the animation.
	        showView.setAlpha(0f);
	        showView.setVisibility(View.VISIBLE);

	        // Animate the "show" view to 100% opacity, and clear any animation listener set on
	        // the view. Remember that listeners are not limited to the specific animation
	        // describes in the chained method calls. Listeners are set on the
	        // ViewPropertyAnimator object for the view, which persists across several
	        // animations.
	        showView.animate()
	                .alpha(1f)
	                .setDuration(mShortAnimationDuration)
	                .setListener(null);

	        // Animate the "hide" view to 0% opacity. After the animation ends, set its visibility
	        // to GONE as an optimization step (it won't participate in layout passes, etc.)
	        hideView.animate()
	                .alpha(0f)
	                .setDuration(mShortAnimationDuration)
	                .setListener(new AnimatorListenerAdapter() {
	                    @Override
	                    public void onAnimationEnd(Animator animation) {
	                        hideView.setVisibility(View.GONE);
	                    }
	                });
	    }
	    
	    private Bitmap loadImageFromNetwork(String url) {
	    	Bitmap bm = null;
	        try {
	        	URL urln = new URL(url);
	            bm = BitmapFactory.decodeStream(urln.openConnection().getInputStream());
	        } catch (IOException e) {
	            Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
	        } 
	        return bm;
	    }
	}

}
