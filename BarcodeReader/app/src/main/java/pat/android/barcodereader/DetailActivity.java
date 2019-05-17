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
        txtDay.setText(GetDate());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/rs.csv");
                //Return data
                Intent intent = new Intent();
                intent.putExtra("getDate",GetDate());
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

    public static String GetDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss a dd-MM-yyyy");
        Date c = Calendar.getInstance().getTime();
        return fmt.format(c);
    }
}
