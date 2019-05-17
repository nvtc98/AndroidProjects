package pat.android.hochiminhtour;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HoChiMinhTour extends AppCompatActivity {
    ArrayList<Destination> destinationArrayList;
    DestinationAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        adapter = new DestinationAdapter(this,R.layout.row_destination,destinationArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater layoutInflater = LayoutInflater.from(HoChiMinhTour.this);
                View dialogView = layoutInflater.inflate(R.layout.dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HoChiMinhTour.this);
                alertDialogBuilder.setView(dialogView);
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
                Button btnMapIt = (Button)dialogView.findViewById(R.id.btnMapIt);
                btnMapIt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(destinationArrayList.get(position).location));
                        startActivity(intent);
                    }
                });
                Button btnMoreInfo = (Button)dialogView.findViewById(R.id.btnMoreInfo);
                btnMoreInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(destinationArrayList.get(position).webpage));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void AnhXa(){
        listView = (ListView) findViewById(R.id.listView);
        destinationArrayList = new ArrayList<>();
        destinationArrayList.add(new Destination("Tòa nhà Bitexco","https://www.google.com/maps/place/Bitexco+Financial+Tower,+B%E1%BA%BFn+Ngh%C3%A9,+Qu%E1%BA%ADn+1,+H%E1%BB%93+Ch%C3%AD+Minh,+Vietnam/@10.7719839,106.7022025,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f41476fee6b:0x15de2e5129cc54f!8m2!3d10.7719839!4d106.7043912","http://bitexco.com.vn/",R.drawable.bitexco));
       destinationArrayList.add(new Destination("Tòa nhà Landmark 81","https://www.google.com/maps/place/Landmark+81/@10.7948349,106.7198438,17z/data=!3m1!4b1!4m5!3m4!1s0x317527c45a742e67:0x4b5d7cec7bdebb18!8m2!3d10.7948349!4d106.7220325","https://landmark.vinhomes.vn/",R.drawable.landmark));
        destinationArrayList.add(new Destination("Chợ Bến Thành","https://www.google.com/maps/place/Ben+Thanh+Market/@10.7725451,106.6958526,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f3f3129e64d:0x8d6b2d79522c7f30!8m2!3d10.7725451!4d106.6980413","http://www.chobenthanh.org.vn/",R.drawable.benthanh));
        destinationArrayList.add(new Destination("Nhà thờ Đức Bà","https://www.google.com/maps/place/Notre+Dame+Cathedral+of+Saigon/@10.7797855,106.6968302,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f37e6d82451:0xe84f59936ced5b45!8m2!3d10.7797855!4d106.6990189","https://nhathoconggiao.com/danh-sach-nha-tho/nhatho/nha-tho-duc-ba-sai-gon",R.drawable.nhathoducba));
        destinationArrayList.add(new Destination("Bảo tàng Mỹ Thuật","https://www.google.com/maps/place/H%E1%BB%93+Ch%C3%AD+Minh+City+Museum+of+Fine+Arts/@10.7698608,106.697141,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f4077edf9b1:0xb1466bf707230576!8m2!3d10.7698608!4d106.6993297","https://www.vnfam.vn/en/",R.drawable.baotangmythuat));

    }
}
