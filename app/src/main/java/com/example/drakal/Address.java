package com.example.drakal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class Member{
    private  String HouseNo,Area,City,State;
    private long pincode;

    public Member() {
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(String houseNo) {
        HouseNo = houseNo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }
}

public class Address extends AppCompatActivity {

    EditText HouseNo,Area,City,State,Pincode;
    Button Submit;
    Member member;
    DatabaseReference reff;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        HouseNo = findViewById(R.id.hn);
        Area = findViewById(R.id.ar);
        City = findViewById(R.id.ct);
        State = findViewById(R.id.st);
        Pincode = findViewById(R.id.pc);
        Submit = findViewById(R.id.sbtn);

        member=new Member();
        //String Id = FirebaseAuth.getInstance().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("member");


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Pin = Integer.parseInt(Pincode.getText().toString().trim());

                member.setHouseNo(HouseNo.getText().toString().trim());
                member.setArea(Area.getText().toString().trim());
                member.setCity(City.getText().toString().trim());
                member.setState(State.getText().toString().trim());
                member.setPincode(Pin);
                reff.push().setValue(member);

                Toast.makeText(Address.this,"Address Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}