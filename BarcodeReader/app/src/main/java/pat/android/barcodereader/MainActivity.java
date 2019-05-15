package pat.android.barcodereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.support.v7.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<Student> students = new ArrayList<Student>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct the data source
        // Create the adapter to convert the array to views
        // Attach the adapter to a ListView
        RecyclerView rvStudent = (RecyclerView) findViewById(R.id.recyclerview);
        for(int i = 0; i < readCSV().size();i++){
            Student a = readCSV().get(i);
            students.add(a);
        }
        final StudentAdapter adapter = new StudentAdapter(students);
        rvStudent.setAdapter(adapter);
        rvStudent.setLayoutManager(new LinearLayoutManager(this));
        int curSize = adapter.getItemCount();
        adapter.notifyItemRangeInserted(curSize, students.size());
        final EditText searchID = (EditText)findViewById(R.id.txtKey);
        Button btnTim = (Button)findViewById(R.id.btnSearch);
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getFilter().filter(searchID.getText().toString());
            }
        });
    }

    public ArrayList<Student> readCSV(){
        InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,Charset.forName("UTF-8")));
        String line="";
        ArrayList<Student> studentlist = new ArrayList<Student>();
        try
        {
            while ((line=reader.readLine())!=null)
            {
                String[] temp_data = line.split(",");

                int imgId= getResources().getIdentifier(temp_data[3], "drawable", getPackageName());
                Student student = new Student(temp_data[0],temp_data[1],temp_data[2],imgId);
                studentlist.add(student);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return studentlist;
    }
}
