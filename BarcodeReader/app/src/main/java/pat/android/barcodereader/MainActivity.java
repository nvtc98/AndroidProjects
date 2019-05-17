package pat.android.barcodereader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentListener {

    public static final int REQUEST_CODE_MESSAGE = 101;
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Student> studentsUpdated = new ArrayList<Student>();
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

        Button btnSendEmail = (Button)findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SendEmail();
                sendEmail();
            }
        });
    }

    public void init() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultcode, Intent intent) {
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
        switch (requestCode){
            case REQUEST_CODE_MESSAGE:
                if(resultcode == Activity.RESULT_OK){
                    String message = intent.getStringExtra("getDate");
                    String studentID = intent.getStringExtra("studentID");
                    if(UpdateStudent(studentID,message)!=null)
                    {
                        studentsUpdated.add(UpdateStudent(studentID,message));
                        Toast.makeText(this,"Updated: "+message,Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(this,"Cancel ",Toast.LENGTH_SHORT).show();
        }
    }

    public Student UpdateStudent(String ID, String mesUpdate){
        for(Student i: students){
            if(i.getId().equals(ID)){
                i.setDay(mesUpdate);
                return i;
            }
        }
        return null;
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

    //btn Detail view
    @Override
    public void onStudentClick(int position) {
        Toast.makeText(this,"Touch Detail View",Toast.LENGTH_SHORT).show();
        Student a = students.get(position);
        Bundle b=new Bundle();
        b.putStringArray("DetailInfo", new String[]{a.getId(),a.getName(), String.valueOf(a.getImg()),a.getDay()});
        Intent intent= DetailActivity.makeIntent(MainActivity.this);
        intent.putExtras(b);
        intent.putExtra("StudentLst",students);
        startActivityForResult(intent, REQUEST_CODE_MESSAGE);
    }

    public void WriteCSV(){
        final String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/result.csv"); // Here csv file name is MyCsvFile.csv

        try {
            File folder = new File(csv);
            boolean var = false;
            CSVWriter writer = new CSVWriter(new FileWriter(csv));
            List<String[]> data = new ArrayList<String[]>();
            for(Student i:studentsUpdated) {
                data.add(new String[]{i.getId(), i.getName(), String.valueOf(i.getImg()), DetailActivity.GetDate()});
            }
            //String[] data = new String[]{a.getId(), a.getName(), String.valueOf(a.getImg()),GetNowDate()};
            writer.writeAll(data); // data is adding to csv
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WriteCSV();
    }

    @Override
    protected void onStop() {
        super.onStop();
        WriteCSV();
    }

    // Send Email
    public void sendEmail() {
        WriteCSV();
        File fileCSVpath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/result.csv");
        Uri u1  =  Uri.fromFile(fileCSVpath);
        String[] TO = {"toilati123vn@gmail.com"};
        //String[] CC = {"toilati123vn@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "The Updated List of Selected Students ");
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "Attachment here");
        emailIntent.putExtra(Intent.EXTRA_STREAM, u1);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
