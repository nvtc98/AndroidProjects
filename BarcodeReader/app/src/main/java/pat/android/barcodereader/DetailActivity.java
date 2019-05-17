package pat.android.barcodereader;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity  implements ActivityCompat.OnRequestPermissionsResultCallback {
    Student a = new Student();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray("DetailInfo");
        a = new Student(array[0],array[1],array[3],Integer.parseInt(array[2]));
        ImageView img = (ImageView)findViewById(R.id.imgDetail);
        TextView txtID = (TextView) findViewById(R.id.txtDetailID);
        TextView txtName = (TextView) findViewById(R.id.txtDetailName);
        TextView txtDay = (TextView) findViewById(R.id.txtDetailDay);
        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
        img.setImageResource(Integer.parseInt(array[2]));
        txtID.setText(array[0]);
        txtName.setText(array[1]);
        txtDay.setText(GetNowDate());
        //final String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/rs.csv"); // Here csv file name is MyCsvFile.csv

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/rs.csv");

                try {
                    CSVWriter writer = new CSVWriter(new FileWriter(csv));

                    List<String[]> data = new ArrayList<String[]>();
                    data.add(new String[]{"Country", "Capital"});
                    data.add(new String[]{"India", "New Delhi"});
                    data.add(new String[]{"United States", "Washington D.C"});
                    data.add(new String[]{"Germany", "Berlin"});

                    writer.writeAll(data); // data is adding to csv

                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                WriteCSV(a);
            }
         });

    }

    private void WriteCSV(Student student){
        String csv =getExternalFilesDir(null).getAbsolutePath()+"/data.csv";
        try {
            String fileUrl = "/BarcodeReader/data.csv";
            String file = android.os.Environment.getExternalStorageDirectory().getPath() + fileUrl;
            File f = new File(file);

            if(f.exists())
                return;
            else f.createNewFile();

            CSVWriter writer = new CSVWriter(new FileWriter(csv));
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{"Country", "Capital"});
            data.add(new String[]{"India", "New Delhi"});
            data.add(new String[]{"United States", "Washington D.C"});
            data.add(new String[]{"Germany", "Berlin"});

            writer.writeAll(data); // data is adding to csv
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*private int getRowIndex(String sFileName,String ID)
    {
        int i = 0;
        try {
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getClassLoader().getResourceAsStream(sFileName)));
            String line;
            while ((line = r.readLine()) != null) {
                String tokens[] = line.split(",");

                String IDcsv = tokens[0];
                if (IDcsv.equals(ID)) {
                    return i;
                }
                i++;
            }
            r.close();
        } catch (IOException e) {
            System.out.println(" There was an Error Reading the file. " + e.getMessage());
        }
        return i;
    }

    public void update(String ID){
        String source = "res/raw/data.csv";
        String destiantion="res/raw/rs.csv";
        try {
            DetailActivity.updateCSV(source, destiantion, GetNowDate(), getRowIndex(source,ID),4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final char SEPARATOR = ',';
    public static void updateCSV(String input, String output, String  replace, int row, int col) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(input));
        List<String[]> csvBody = reader.readAll();
        csvBody.get(row)[col]=replace;
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter(output),SEPARATOR,' ');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }
    private void ChangeRecord(String file, String text) {
        // TODO Auto-generated method stub
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_APPEND);
            fos.write(text.getBytes());
            fos.close();
          *//*  while ((line = r.readLine()) != null) {
                String tokens[] = line.split(",");
                if ( tokens[0].equals(ID)) {
                    line = line.replaceAll(".", GetNowDate());
                    Toast.makeText(DetailActivity.this,"",Toast.LENGTH_SHORT).show();
                }
            }
            r.close();*//*
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            System.out.println(" There was an Error Reading the file. " + e.getMessage());
        }
    }
*/

    /*public static void updateCSV(String fileToUpdate, String replace,
                                 int row, int col) throws IOException {

        File inputFile = new File(fileToUpdate);

// Read existing file
        //CSVReaderBuilder  builder = new CSVReaderBuilder(new FileReader(fileToUpdate));
        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
        csvBody.get(row)[col] = replace;
        reader.close();

// Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }*/


    public static String GetNowDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        Date c = Calendar.getInstance().getTime();
        return fmt.format(c);
    }
}
