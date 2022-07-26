package com.example.myassetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.app.ActionBar.LayoutParams;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;
    private Button btn2;
    private ImageView image;
    private AssetManager assetManager;
    private ImageView imageViewByFlag;
    private LinearLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = getAssets();

        //btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        myLayout = (LinearLayout) findViewById(R.id.mylayout);
        image = (ImageView) findViewById(R.id.image);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn2){
            try {
                String[] imgPath = assetManager.list("flag");

                for(int i = 0; i < imgPath.length; i++){
                    InputStream is = assetManager.open("flag/"+imgPath[i]);
                    Log.d("MainActivity",imgPath[i]);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imageViewByFlag = new ImageView(this);
                    imageViewByFlag.setImageBitmap(bitmap);
//                    ScrollView scrollView = new ScrollView(this);
//                    scrollView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                    LinearLayout.LayoutParams params = new LinearLayout .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    imageViewByFlag.setLayoutParams(params);
                    imageViewByFlag.getLayoutParams().height = 100;
                    imageViewByFlag.getLayoutParams().width = 100;
                    params.setMargins(10,10,10,10);
                    myLayout.requestLayout();

                    myLayout.addView(imageViewByFlag);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}