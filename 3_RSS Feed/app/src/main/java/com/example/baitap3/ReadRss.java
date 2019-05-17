package com.example.baitap3;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.example.baitap3.Classes.Category;
import com.example.baitap3.Classes.Dish;
import com.example.baitap3.Classes.XMLDOMParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//  ReadRss ke thua AsyncTask de su dung DA TIEN TRINH
public class ReadRss extends AsyncTask<String, Void, String> {

  private MainActivity mainActivity;

  public ReadRss(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  //Thuc hien tien trinh trong khi ung dung chay nen
  @Override
  protected String doInBackground(String... strings) {
    //tạo builder rỗng
    StringBuilder content = new StringBuilder();
    try {
      //Tao Url
      URL url = new URL(strings[0]);
      //mở liên kết với url đã có
      //	getInputStream lấy input từ openConnection và tạo thành 1 luồng kí tự URl
      InputStreamReader inputStreamReader = new InputStreamReader(
          url.openConnection().getInputStream());
      // sau đó dùng BufferedReader để đọc dữ liệu theo từng dòng
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line = "";
      //nếu như đã có line đó thì viết thêm vào content
      while ((line = bufferedReader.readLine()) != null) {
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
  protected void onPostExecute(String s) {
    super.onPostExecute(s);
    XMLDOMParser parser = new XMLDOMParser();
    Document document = parser.getDocument(s);

    // Lấy dữ liệu Rss
    List<Dish> dishs = new ArrayList<Dish>();
    NodeList nodeChannel = document.getElementsByTagName("channel");
    Element element = (Element) nodeChannel.item(0);
    mainActivity.categories.add(new Category(1, parser.getValue(element, "title"), dishs));

    // Gắn data lên ListView
    List<String> categories_name = new ArrayList<String>();
    for (int i = 0; i < mainActivity.categories.size(); i++) {
      categories_name.add(mainActivity.categories.get(i).getCategoryName());
    }
    //Tìm listview có id là ...
    mainActivity.list_item = mainActivity.findViewById(R.id.listRecipes);
    //Lấy dữ liệu và tạo ra view
    mainActivity.adapter = new ArrayAdapter<String>(mainActivity.getApplication(), android.R.layout.simple_list_item_1,
        categories_name);
    mainActivity.list_item.setAdapter(mainActivity.adapter);
    // Thiết lập sự kiện cho Listview, khi chọn phần tử nào thì hiển thị lên TextView
    mainActivity.list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(mainActivity, OptionsActivity.class);
        i.putExtra("recipes_id", String.valueOf(position));
        mainActivity.startActivity(i);
      }
    });
  }
}
