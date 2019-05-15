package pat.android.barcodereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    public static final String TAG = "PhanAnhTruc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        StudentAdapter adapter = new StudentAdapter (MainActivity.this,readCSV());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void LoadCsvLv(){

    }
    public ArrayList<Student> readCSV(){
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        /*InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try
        {
            while ((line=reader.readLine())!=null)
            {
                String[] row = line.split(",");

                Student student = new Student(row[0],row[1],row[2],Integer.parseInt(row[3]));
                studentArrayList.add(student);
                Log.wtf(TAG,"Success add:"+student);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return studentArrayList;*/
        studentArrayList = new ArrayList<>();
        studentArrayList.add(new Student("Tòa nhà Bitexco","https://www.google.com/maps/place/Bitexco+Financial+Tower,+B%E1%BA%BFn+Ngh%C3%A9,+Qu%E1%BA%ADn+1,+H%E1%BB%93+Ch%C3%AD+Minh,+Vietnam/@10.7719839,106.7022025,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f41476fee6b:0x15de2e5129cc54f!8m2!3d10.7719839!4d106.7043912","http://bitexco.com.vn/",1));
        studentArrayList.add(new Student("Tòa nhà Landmark 81","https://www.google.com/maps/place/Landmark+81/@10.7948349,106.7198438,17z/data=!3m1!4b1!4m5!3m4!1s0x317527c45a742e67:0x4b5d7cec7bdebb18!8m2!3d10.7948349!4d106.7220325","https://landmark.vinhomes.vn/",2));
        studentArrayList.add(new Student("Chợ Bến Thành","https://www.google.com/maps/place/Ben+Thanh+Market/@10.7725451,106.6958526,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f3f3129e64d:0x8d6b2d79522c7f30!8m2!3d10.7725451!4d106.6980413","http://www.chobenthanh.org.vn/",3));
        studentArrayList.add(new Student("Nhà thờ Đức Bà","https://www.google.com/maps/place/Notre+Dame+Cathedral+of+Saigon/@10.7797855,106.6968302,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f37e6d82451:0xe84f59936ced5b45!8m2!3d10.7797855!4d106.6990189","https://nhathoconggiao.com/danh-sach-nha-tho/nhatho/nha-tho-duc-ba-sai-gon",4));
        studentArrayList.add(new Student("Bảo tàng Mỹ Thuật","https://www.google.com/maps/place/H%E1%BB%93+Ch%C3%AD+Minh+City+Museum+of+Fine+Arts/@10.7698608,106.697141,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f4077edf9b1:0xb1466bf707230576!8m2!3d10.7698608!4d106.6993297","https://www.vnfam.vn/en/",5));
        return studentArrayList;
    }

}
