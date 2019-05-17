package pat.android.barcodereader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/rs.csv");

            //Pass data back
                Intent intent = new Intent();
                intent.putExtra("getDate",GetNowDate());
                intent.putExtra("studentID",a.getId());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
         });

    }

    public static Intent makeIntent(Context context)
    {
        return new Intent(context,DetailActivity.class);
    }

    /*private void WriteCSV(Student student){
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
            data.add(new String[]{a.getId(), a.getName(), String.valueOf(a.getImg()),GetNowDate()});

            writer.writeAll(data); // data is adding to csv
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void exportEmailInCSV() throws IOException {
        {

            File folder = new File(Environment.getExternalStorageDirectory()
                    + "/Folder");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            System.out.println("" + var);


            final String filename = folder.toString() + "/" + "Test.csv";

            // show waiting screen
            CharSequence contentTitle = getString(R.string.app_name);
            final ProgressDialog progDailog = ProgressDialog.show(
                    DetailActivity.this, contentTitle, "even geduld aub...",
                    true);//please wait
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {




                }
            };

            new Thread() {
                public void run() {
                    try {

                        FileWriter fw = new FileWriter(filename);

                        Cursor cursor = stu.selectAll();

                        fw.append("No");
                        fw.append(',');

                        fw.append("code");
                        fw.append(',');

                        fw.append("nr");
                        fw.append(',');

                        fw.append("Orde");
                        fw.append(',');

                        fw.append("Da");
                        fw.append(',');

                        fw.append("Date");
                        fw.append(',');

                        fw.append("Leverancier");
                        fw.append(',');

                        fw.append("Baaln");
                        fw.append(',');

                        fw.append("asd");
                        fw.append(',');

                        fw.append("Kwaliteit");
                        fw.append(',');

                        fw.append("asd");
                        fw.append(',');

                        fw.append('\n');

                        if (cursor.moveToFirst()) {
                            do {
                                fw.append(cursor.getString(0));
                                fw.append(',');

                                fw.append(cursor.getString(1));
                                fw.append(',');

                                fw.append(cursor.getString(2));
                                fw.append(',');

                                fw.append(cursor.getString(3));
                                fw.append(',');

                                fw.append(cursor.getString(4));
                                fw.append(',');

                                fw.append(cursor.getString(5));
                                fw.append(',');

                                fw.append(cursor.getString(6));
                                fw.append(',');

                                fw.append(cursor.getString(7));
                                fw.append(',');

                                fw.append(cursor.getString(8));
                                fw.append(',');

                                fw.append(cursor.getString(9));
                                fw.append(',');

                                fw.append(cursor.getString(10));
                                fw.append(',');

                                fw.append('\n');

                            } while (cursor.moveToNext());
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }

                        // fw.flush();
                        fw.close();

                    } catch (Exception e) {
                    }
                    handler.sendEmptyMessage(0);
                    progDailog.dismiss();
                }
            }.start();

        }

    }*/


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
