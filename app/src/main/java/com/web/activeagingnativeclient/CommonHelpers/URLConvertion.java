package com.web.activeagingnativeclient.CommonHelpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.web.activeagingnativeclient.Fragments.ShopView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Christian on 2015-10-02.
 */
public class URLConvertion {

    private Bitmap bitmap;

    public void handleBitmapUrl(final ImageView imageView, final String mUrl, final ProgressBar pb) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();

                    InputStream input = connection.getInputStream();

                    bitmap = BitmapFactory.decodeStream(input);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        }.execute();

    }

}
