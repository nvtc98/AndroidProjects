package com.example.baitap3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.baitap3.Classes.Category;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  String appTitle = "RSS Feed";
  List<Category> categories;
  ListView list_item;
  ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    //Gọi hàm create
    super.onCreate(savedInstanceState);
    // Kết nối activity với layout
    setContentView(R.layout.activity_main);
    //Set title cho ActionBar sau khi truy cap,thay doi cac thuoc tinh
    ActionBarSettings();
    categories = new ArrayList<Category>();
    list_item = findViewById(R.id.listRecipes);

    //new ReadRss().execute("https://www.geniuskitchen.com/rss");
    new ReadRss(this).execute("https://www.homecookingadventure.com/rss");
  }

  private void ActionBarSettings() {
    getSupportActionBar().setTitle(appTitle);
  }

  //Fill data len List


}
