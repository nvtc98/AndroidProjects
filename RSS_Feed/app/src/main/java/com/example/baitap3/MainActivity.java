package com.example.baitap3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String app_name = "";
    List<Category> categories;
    ListView list_item;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super duoc su dung de tham chieu bien instance cua lop cha gan nhat
        super.onCreate(savedInstanceState);
        //setContentView de ket noi Layout vao Activity
        setContentView(R.layout.activity_main);
        app_name ="  Food.com Recipes " +  DateFormat.getDateInstance(DateFormat.FULL,  Locale.US).format(new Date());
        //Set title cho ActionBar sau khi truy cap,thay doi cac thuoc tinh
        getSupportActionBar().setTitle(app_name);
        //Hien thi nut home
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Hien thi icon Food
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_food);
        categories = new ArrayList<Category>();
        list_item = (ListView)findViewById(R.id.list_recipes);

        //new ReadRss().execute("https://www.geniuskitchen.com/rss");
        new ReadRss().execute("https://www.homecookingadventure.com/rss");
    }
    //Fill data len List
    private List<Category> InitializationData(){
        RawData data = new RawData();
        List<Category> c = new ArrayList<Category>();
        //Tra ve tap hop tat ca Category trong RawData
        c = data.getCategories();
        return c;
    }

    //  ReadRss ke thua AsyncTask de su dung DA TIEN TRINH
    public class ReadRss extends AsyncTask<String, Void, String>
    {

        //Thuc hien tien trinh trong khi ung dung chay nen
        @Override
        protected String doInBackground(String... strings)
        {
            //tạo builder rỗng
            StringBuilder content = new StringBuilder();
            try {
                //Tao Url
                URL url = new URL (strings[0]);
                //mở liên kết với url đã có
                //	getInputStream lấy input từ openConnection và tạo thành 1 luồng kí tự URl
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                // sau đó dùng BufferedReader để đọc dữ liệu theo từng dòng
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                //nếu như đã có line đó thì viết thêm vào content
                while ((line = bufferedReader.readLine()) != null)
                {
                    content.append(line);
                }
                //Đóng hàm đọc
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // xuất string content
            return content.toString();
        }
        //Sau khi tiến trình kết thúc thì hàm này sẽ tự động xảy ra. Ta có thể lấy được kết quả trả về sau khi thực hiện tiến trình kết thúc ở đây.
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);

            // Lấy dữ liệu Rss
            List<Dish> dishs = new ArrayList<Dish>();
            NodeList nodeChannel = document.getElementsByTagName("channel");
            Element element = (Element) nodeChannel.item(0);
            categories.add(new Category(1,parser.getValue(element,"title"),dishs));


            // Gắn data lên ListView
            List<String> categories_name = new ArrayList<String>();
            for(int i=0; i<categories.size(); i++)
                categories_name.add(categories.get(i).getCategoryName());
            //Tìm listview có id là ...
            list_item = (ListView)findViewById(R.id.list_recipes);
            //Lấy dữ liệu và tạo ra view
            adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1,categories_name);
            list_item.setAdapter(adapter);
            // Thiết lập sự kiện cho Listview, khi chọn phần tử nào thì hiển thị lên TextView
            list_item.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent i = new Intent(MainActivity.this,OptionsActivity.class);
                    i.putExtra("recipes_id",String.valueOf(position));
                    startActivity(i);
                }
            });
        }
    }
}
