package pat.android.barcodereader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentListener {
    ArrayList<Student> students = new ArrayList<Student>();
    EditText ketqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct the data source
        // Create the adapter to convert the array to views
        // Attach the adapter to a ListView
        RecyclerView rvStudent = (RecyclerView) findViewById(R.id.recyclerview);
        for (int i = 0; i < readCSV().size(); i++) {
            Student a = readCSV().get(i);
            students.add(a);
        }
        final StudentAdapter adapter = new StudentAdapter(students,this);
        rvStudent.setAdapter(adapter);
        rvStudent.setLayoutManager(new LinearLayoutManager(this));
        int curSize = adapter.getItemCount();
        adapter.notifyItemRangeInserted(curSize, students.size());
        final EditText searchID = (EditText) findViewById(R.id.txtKey);
        Button btnTim = (Button) findViewById(R.id.btnSearch);
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getFilter().filter(searchID.getText().toString());
                // Not result found
                if(students.size()==0){
                    Toast.makeText(MainActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button btnScan = (Button) findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

    }

    public void init() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        //integrator.setBarcodeImageEnabled(true);
        // beep khi scan qr thành công
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultcode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultcode, intent);
        if (result != null) {
            String contents = result.getContents();
            ketqua = (EditText)findViewById(R.id.txtKey);
            ketqua.setText(contents);
            // lấy hiệu ứng rung khi scan thành công.
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // SET RUNG 400 MILLISECONDS
            v.vibrate(400);

        }
    }


    public ArrayList<Student> readCSV() {
        InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String line = "";
        ArrayList<Student> studentlist = new ArrayList<Student>();
        try {
            while ((line = reader.readLine()) != null) {
                String[] temp_data = line.split(",");

                int imgId = getResources().getIdentifier(temp_data[2], "drawable", getPackageName());
                Student student = new Student(temp_data[0], temp_data[1], temp_data[3], imgId);
                studentlist.add(student);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return studentlist;
    }

    public String GetNowDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        Date c = Calendar.getInstance().getTime();
        return fmt.format(c);
    }

    @Override
    public void onStudentClick(int position) {
        Student a = students.get(position);
        Bundle b=new Bundle();
        b.putStringArray("DetailInfo", new String[]{a.getId(),a.getName(), String.valueOf(a.getImg()),a.getDay()});
        Intent intent= new Intent(this,DetailActivity.class);
        intent.putExtras(b);
        intent.putExtra("StudentLst",students);
        startActivity(intent);
        //startActivityForResult(intent,1);
    }





}
