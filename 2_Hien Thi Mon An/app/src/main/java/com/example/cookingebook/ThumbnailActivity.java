package com.example.cookingebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ThumbnailActivity extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thumbnail_activity);
        imageView=findViewById(R.id.imageView);
        LoadData();
    }


    private void LoadData() {
        Intent intent = getIntent();
        Recipe recipe =(Recipe)intent.getSerializableExtra("recipe");
        imageView.setImageResource(recipe.getPhotoId());
    }
}
