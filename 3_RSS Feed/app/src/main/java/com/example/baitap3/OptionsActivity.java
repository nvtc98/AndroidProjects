package com.example.baitap3;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.baitap3.Classes.Category;
import com.example.baitap3.Classes.Dish;
import com.example.baitap3.Classes.XMLDOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class OptionsActivity extends AppCompatActivity {
    String app_name;
    int RecipesID;
    List<Category> categories;
    ListView list_item;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        categories = new ArrayList<Category>();
        new ReadRssOptions().execute("https://www.homecookingadventure.com/rss");
        //new ReadRssOptions().execute("https://www.geniuskitchen.com/rss");
    }

    private void showAlertDialog(View view, final int position, long id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view);
        final AlertDialog alertD = builder.create();

        TextView category_name_dialog = view.findViewById(R.id.category_name_dialog);
        TextView dish_describe_diaplog =  view.findViewById(R.id.dish_describe_diaplog);
        TextView dish_name_dialog = view.findViewById(R.id.dish_name_dialog);
        TextView dish_rating_diaplog = view.findViewById(R.id.dish_rating_diaplog);
        ImageView dish_image_dialog = view.findViewById(R.id.dish_image_dialog);
        Button btn_more = view.findViewById(R.id.btn_more);
        Button btn_close = view.findViewById(R.id.btn_close);

        String strCategoryName = categories.get(RecipesID).getCategoryName();
        String strDescribe = categories.get(RecipesID).getDishs().get(position).getDescribe();
        String strDishName = categories.get(RecipesID).getDishs().get(position).getDishName();
        String strRating = String.valueOf(categories.get(RecipesID).getDishs().get(position).getRating());
        String strImage = categories.get(RecipesID).getDishs().get(position).getLinkImage();

        category_name_dialog.setText(strCategoryName);
        dish_describe_diaplog.setText(strDescribe);
        dish_name_dialog.setText(strDishName);
        dish_rating_diaplog.setText("Rating: " + strRating);

        Glide.with(getApplicationContext()).load(strImage).into(dish_image_dialog);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.dismiss();
            }
        });

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = categories.get(RecipesID).getDishs().get(position).getLinkDetails();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        alertD.show();
    }

    public class ReadRssOptions extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL (strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            //GetData From Rss
            List<Dish> dishs = new ArrayList<Dish>();
            NodeList nodeList = document.getElementsByTagName("item");
            for(int i = 0; i< nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                Dish d = new Dish();
                d.setID(i);
                d.setDishName(parser.getValue(element,"title"));
                d.setLinkDetails(parser.getValue(element,"link"));
                String describe = parser.getValue(element,"description");
                describe = describe.substring(describe.lastIndexOf("/>") + 2,describe.length());
                d.setDescribe(describe);
                String imgUrl = parser.getValue(element,"description");
                imgUrl = imgUrl.substring(imgUrl.indexOf("src=") + 5, imgUrl.lastIndexOf('"'));
                d.setLinkImage(imgUrl);
                d.setRating(new Random().nextInt(5));
                dishs.add(d);
            }
            NodeList nodeChannel = document.getElementsByTagName("channel");
            Element element = (Element) nodeChannel.item(0);
            categories.add(new Category(1,parser.getValue(element,"title"),dishs));

            // Set info from MainAcitivy
            Intent intent = getIntent();
            RecipesID = Integer.parseInt(intent.getExtras().getString("recipes_id"));
            app_name ="RSS Feed";
            getSupportActionBar().setTitle(app_name);

            // SetData to listView
            List<String> options_name_of_category = new ArrayList<String>();
            for(int i=0; i<categories.get(RecipesID).getDishs().size(); i++)
                options_name_of_category.add(categories.get(RecipesID).getDishs().get(i).getDishName());

            list_item = (ListView)findViewById(R.id.listOptions);
            adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1,options_name_of_category);
            list_item.setAdapter(adapter);

            list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showAlertDialog(view,position,id);
                }
            });
        }
    }
}
