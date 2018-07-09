package com.example.komputer.androidaplikacja2bazydanych;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    DBHelper bazadelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        try {
        Bundle bundle = getIntent().getExtras();
        bazadelete = new DBHelper(this);
        String strid = bundle.getString("editkey");
        Cursor cursor = bazadelete.ShowWorker(strid);
            cursor.moveToFirst();
            String eFirstname = cursor.getString(1);
            String eLastname = cursor.getString(2);
            String eCity = cursor.getString(3);
            String eHousenumber = cursor.getString(4);
            String eStreet = cursor.getString(5);
            String ePhone = cursor.getString(6);
            TextView fname = findViewById(R.id.fname);
            TextView lname = findViewById(R.id.lname);
            TextView city = findViewById(R.id.city);
            TextView housenumber = findViewById(R.id.housenumber);
            TextView street = findViewById(R.id.street);
            TextView phone = findViewById(R.id.phone);
            fname.setText(eFirstname);
            lname.setText(eLastname);
            city.setText(eCity);
            housenumber.setText(eHousenumber);
            street.setText(eStreet);
            phone.setText(ePhone);
        }catch (Exception e) {Toast.makeText(this, "Brak takiego rekordu w bazie", Toast.LENGTH_LONG).show();
            this.finish();}
    }

    public void Usun(View view) {
        Bundle bundle = getIntent().getExtras();
        String strid = bundle.getString("editkey");
        bazadelete.DeleteWorker(strid);
        Toast.makeText(getApplicationContext(),"Rekord został pomyślnie usunięty",Toast.LENGTH_LONG).show();
        this.finish();
    }


}
