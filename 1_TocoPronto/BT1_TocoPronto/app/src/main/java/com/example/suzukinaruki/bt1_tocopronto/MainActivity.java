package com.example.suzukinaruki.bt1_tocopronto;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	//khai bao cac control (nut, checkbox,...)
    Button btnPlace;
    CheckBox chbBeef, chbChicken, chbFish, chbSeafood, chbCheese, chbRice, chbBean, chbPico, chbGuaca, chbLBT, chbSoda, chbCerveza, chbMargarita, chbTequila;
    RadioButton rbSizeM, rbSizeL, rbCorn, rbFlour;
	final int REQUEST_SEND_SMS = 1, READ_PHONE_STATE = 2; //them uses-permission cua gui sms voi goi dien ben file androidmanifest.xml
	
	//event on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        addControls();
		CheckPermission();
        addEvents();
    }
	//xu ly event bam nut place order
    private void addEvents() {
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String size, tortilla, fillings, beverage;
                size = takeSize();
                tortilla = takeTortilla();
                fillings = takeFillings();
                beverage = takeBeverage();
                SmsManager smsManager = SmsManager.getDefault();
                String mess = "Order detail:\n " + "Size: " + size + "\nTortilla: "+ tortilla + "\nFillings:"+ fillings+"\nBeverage: "+ beverage;
                smsManager.sendTextMessage("0843623524", null, mess, null, null);
				Toast.makeText(getApplicationContext(),"Send Message sucessfully",Toast.LENGTH_LONG).show();
            }
        });
    }
	//get order detail
    private String takeSize() {
        String size = "";
        if (rbSizeL.isChecked()) {
            size = size + rbSizeL.getText();
        } else {
            size = size + rbSizeM.getText();
        }
        return size;
    }

    private String takeTortilla() {
        String tor = "";
        if (rbCorn.isChecked()) {
            tor = tor + rbCorn.getText();
        } else {
            tor = tor + rbFlour.getText();
        }
        return tor;
    }

    private String takeFillings() {
        String filling = "";
        if (chbBeef.isChecked()) {
            filling = filling + chbBeef.getText() + ", ";
        }
        if (chbSeafood.isChecked()) {
            filling = filling + chbSeafood.getText() + ", ";
        }
        if (chbBean.isChecked()) {
            filling = filling + chbBean.getText() + ", ";
        }
        if (chbCheese.isChecked()) {
            filling = filling + chbCheese.getText() + ", ";
        }
        if (chbChicken.isChecked()) {
            filling = filling + chbChicken.getText() + ", ";
        }
        if (chbFish.isChecked()) {
            filling = filling + chbFish.getText() + ", ";
        }
        if (chbLBT.isChecked()) {
            filling = filling + chbLBT.getText() + ", ";
        }
        if (chbPico.isChecked()) {
            filling = filling + chbPico.getText() + ", ";
        }
        if (chbGuaca.isChecked()) {
            filling = filling + chbGuaca.getText() + ", ";
        }
        if (chbRice.isChecked()) {
            filling = filling + chbRice.getText();
        }
        return filling;
    }

    private String takeBeverage() {
        String beve = "";
        if (chbSoda.isChecked()) {
            beve = beve + chbSoda.getText() + ", ";
        }
        if (chbTequila.isChecked()) {
            beve = beve + chbTequila.getText() + ", ";
        }
        if (chbMargarita.isChecked()) {
            beve = beve + chbMargarita.getText() + ", ";
        }
        if (chbCerveza.isChecked()) {
            beve = beve + chbCerveza.getText();
        }
        return beve;
    }
	//lay control tu giao dien
    private void addControls() {
        chbBean = (CheckBox) findViewById(R.id.chbBeans);
        chbBeef = (CheckBox) findViewById(R.id.chbBeef);
        chbCerveza = (CheckBox) findViewById(R.id.chbCerveza);
        chbCheese = (CheckBox) findViewById(R.id.chbCheese);
        chbChicken = (CheckBox) findViewById(R.id.chbChicken);
        chbFish = (CheckBox) findViewById(R.id.chbWhiteFish);
        chbGuaca = (CheckBox) findViewById(R.id.chbGuacamole);
        chbLBT = (CheckBox) findViewById(R.id.chbLBT);
        chbMargarita = (CheckBox) findViewById(R.id.chbMargarita);
        chbPico = (CheckBox) findViewById(R.id.chbPico);
        chbRice = (CheckBox) findViewById(R.id.chbRice);
        chbSeafood = (CheckBox) findViewById(R.id.chbSeaFood);
        chbSoda = (CheckBox) findViewById(R.id.chbSoda);
        chbTequila = (CheckBox) findViewById(R.id.chbTequila);
        rbCorn = (RadioButton) findViewById(R.id.rbCorn);
        rbFlour = (RadioButton) findViewById(R.id.rbFlour);
        rbSizeM = (RadioButton) findViewById(R.id.rbMedium);
        rbSizeL = (RadioButton) findViewById(R.id.rbLarge);
        btnPlace = (Button) findViewById(R.id.btnPlaceOrder);
    }
	
	//kiem tra quyen gui tin nhan/goi dien 
	private void CheckPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},REQUEST_SEND_SMS);
        }
        else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE);
        }
    }
	//kiem tra xem user da cap quyen gui sms voi goi dien chua, neu chua thi disable nut va thong bao khong cho gui (neu khong se bi loi~
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_SEND_SMS:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btnPlace.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(),"You can't order taco, because you accept permission SEND_SMS.",Toast.LENGTH_LONG).show();
                    btnPlace.setEnabled(false);
                }
            }
            case READ_PHONE_STATE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btnPlace.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(),"You can't order taco, because you accept permission READ_PHONE_STATE.",Toast.LENGTH_LONG).show();
                    btnPlace.setEnabled(false);
                }
            }
        }
    }
}


