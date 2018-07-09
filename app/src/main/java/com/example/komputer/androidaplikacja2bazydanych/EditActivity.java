package com.example.komputer.androidaplikacja2bazydanych;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    DBHelper bazaedit;
    EditText editFirstname, editLastname, editCity, editHousenumber, editStreet, editPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        try {
            Bundle bundle = getIntent().getExtras();
            EditText fname = findViewById(R.id.editTextFirstname);
            EditText lname = findViewById(R.id.editTextLastname);
            EditText city = findViewById(R.id.editTextCity);
            EditText housenumber = findViewById(R.id.editTextHousenumber);
            EditText street = findViewById(R.id.editTextStreet);
            EditText phone = findViewById(R.id.editTextPhone);
            bazaedit = new DBHelper(this);
            String strid = bundle.getString("editkey");
            Cursor cursor = bazaedit.ShowWorker(strid);
            cursor.moveToFirst();
            String eFirstname = cursor.getString(1);
            String eLastname = cursor.getString(2);
            String eCity = cursor.getString(3);
            String eHousenumber = cursor.getString(4);
            String eStreet = cursor.getString(5);
            String ePhone = cursor.getString(6);
            fname.setText(eFirstname);
            lname.setText(eLastname);
            city.setText(eCity);
            housenumber.setText(eHousenumber);
            street.setText(eStreet);
            phone.setText(ePhone);
        }catch (Exception e) {Toast.makeText(this, "Brak takiego rekordu w bazie", Toast.LENGTH_LONG).show();
            this.finish();}
    }
    public void Edytuj(View view){
        Bundle bundle = getIntent().getExtras();
        String strid = bundle.getString("editkey");
        EditText fname = findViewById(R.id.editTextFirstname);
        EditText lname = findViewById(R.id.editTextLastname);
        EditText city = findViewById(R.id.editTextCity);
        EditText housenumber = findViewById(R.id.editTextHousenumber);
        EditText street = findViewById(R.id.editTextStreet);
        EditText phone = findViewById(R.id.editTextPhone);
        bazaedit.UpdateWorker(
                strid,
                fname.getText().toString(),
                lname.getText().toString(),
                city.getText().toString(),
                housenumber.getText().toString(),
                street.getText().toString(),
                phone.getText().toString());
        Toast.makeText(getApplicationContext(),"Rekord został edytowany",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
