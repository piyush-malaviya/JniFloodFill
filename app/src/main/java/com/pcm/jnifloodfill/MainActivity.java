package com.pcm.jnifloodfill;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcm.library.JniBitmap;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private View.OnTouchListener onImageTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    Bitmap bitmap;
                    if (imageView.getDrawable() instanceof BitmapDrawable) {
                        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    } else {
                        bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        imageView.draw(canvas);
                    }

                    if (bitmap != null) {
                        JniBitmap.floodFill(bitmap, x, y, Color.RED, 10);
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
            }

            return true;
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(onImageTouchListener);
    }
}
