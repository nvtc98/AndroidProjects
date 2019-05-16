package pat.android.barcodereader;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray("DetailInfo");
        Student a = new Student(array[0],array[1],array[3],Integer.parseInt(array[2]));
        ImageView img = (ImageView)findViewById(R.id.imgDetail);
        TextView txtID = (TextView) findViewById(R.id.txtDetailID);
        TextView txtName = (TextView) findViewById(R.id.txtDetailName);
        TextView txtDay = (TextView) findViewById(R.id.txtDetailDay);

        img.setImageResource(Integer.parseInt(array[2]));
        txtID.setText(array[0]);
        txtName.setText(array[1]);
        txtDay.setText(GetNowDate());
        ChangeRecord("data.csv",GetNowDate());
        update();
    }


    public void WriteCSVExists(Student st) throws IOException {

    }





    private int getRowIndex(String sFileName,String ID)
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

    public void update(){
        String source = "data.csv";
        String destiantion="rs.csv";
        try {
            DetailActivity.updateCSV(source, destiantion, GetNowDate(), 0,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final char SEPARATOR = ',';
    public static void updateCSV(String input, String output, String  replace, int row, int col) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(input),SEPARATOR);
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
          /*  while ((line = r.readLine()) != null) {
                String tokens[] = line.split(",");
                if ( tokens[0].equals(ID)) {
                    line = line.replaceAll(".", GetNowDate());
                    Toast.makeText(DetailActivity.this,"",Toast.LENGTH_SHORT).show();
                }
            }
            r.close();*/
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            System.out.println(" There was an Error Reading the file. " + e.getMessage());
        }
    }


    public static void updateCSV(String fileToUpdate, String replace,
                                 int row, int col) throws IOException {

        File inputFile = new File(fileToUpdate);

// Read existing file
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
    }


    public static String GetNowDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        Date c = Calendar.getInstance().getTime();
        return fmt.format(c);
    }
}
