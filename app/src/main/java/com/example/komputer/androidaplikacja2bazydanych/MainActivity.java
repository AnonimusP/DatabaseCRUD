package com.example.komputer.androidaplikacja2bazydanych;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DBHelper baza;
    EditText editID, editFirstname, editLastname, editCity, editHousenumber, editStreet, editPhone;
    String strid;
    Button bcancel,btn_usun,btn_delete,btn_add,btn_edit,btn_edytuj,btn_canceledit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baza = new DBHelper(this);

        editFirstname = findViewById(R.id.editTextFirstname);
        editLastname = findViewById(R.id.editTextLastname);
        editCity = findViewById(R.id.editTextCity);
        editHousenumber = findViewById(R.id.editTextHousenumber);
        editStreet = findViewById(R.id.editTextStreet);
        editPhone = findViewById(R.id.editTextPhone);
        editID = findViewById(R.id.editID);
        bcancel = findViewById(R.id.btn_cancel);
        btn_canceledit = findViewById(R.id.btn_canceledit);
        btn_usun = findViewById(R.id.btn_usun);
        btn_delete=findViewById(R.id.btn_delete);
        btn_add=findViewById(R.id.btn_add);
        btn_edit=findViewById(R.id.btn_edit);
        btn_edytuj=findViewById(R.id.btn_edytuj);


    }

    public void Dodaj(View view) {
            baza.AddWorker(
                    editFirstname.getText().toString(),
                    editLastname.getText().toString(),
                    editCity.getText().toString(),
                    editHousenumber.getText().toString(),
                    editStreet.getText().toString(),
                    editPhone.getText().toString());
        editFirstname.setText("");
        editLastname.setText("");
        editCity.setText("");
        editHousenumber.setText("");
        editStreet.setText("");
        editPhone.setText("");
        Toast.makeText(this,"Pomyślnie dodano nową pozycję",Toast.LENGTH_LONG).show();
    }

    public void Edytuj(View view) {
        strid = editID.getText().toString();
        Cursor cursor = baza.ShowWorkers();
            if (cursor.getCount() == 0) {
                Toast.makeText(this,"Baza danych pusta, najpierw wprowadź pozycje",Toast.LENGTH_LONG).show();
                return;
            } else {
                if (TextUtils.isEmpty(strid)) {
                    editID.setError("Musisz podać ID którego chcesz edytować");
                    return;
                } else {
                    Intent i = new Intent(getApplicationContext(), EditActivity.class);
                    i.putExtra("editkey", strid);
                    startActivity(i);
                }
            }
        }


    public void Usun (View view) {
        strid = editID.getText().toString();
        Cursor cursor = baza.ShowWorkers();
            if (cursor.getCount() == 0) {
                Toast.makeText(this,"Baza danych pusta, najpierw wprowadź pozycje",Toast.LENGTH_LONG).show();
                return;
            } else if (TextUtils.isEmpty(strid)) {
                editID.setError("Musisz podać ID którego chcesz edytować");
                return;
            } else {
                Intent i = new Intent(getApplicationContext(), DeleteActivity.class);
                i.putExtra("editkey", strid);
                startActivity(i);
            }
    }


    public void AktywujUsun(View view){
        editID.setVisibility(View.VISIBLE);
        bcancel.setVisibility(View.VISIBLE);
        btn_usun.setVisibility(View.VISIBLE);
        btn_delete.setVisibility(View.INVISIBLE);
        btn_add.setVisibility(View.INVISIBLE);
        btn_edit.setVisibility(View.INVISIBLE);
        editFirstname.setVisibility(View.INVISIBLE);
        editLastname.setVisibility(View.INVISIBLE);
        editCity.setVisibility(View.INVISIBLE);
        editHousenumber.setVisibility(View.INVISIBLE);
        editStreet.setVisibility(View.INVISIBLE);
        editPhone.setVisibility(View.INVISIBLE);
    }
    public void AktywujEdytuj(View view){
        editID.setVisibility(View.VISIBLE);
        btn_canceledit.setVisibility(View.VISIBLE);
        btn_edytuj.setVisibility(View.VISIBLE);
        btn_usun.setVisibility(View.INVISIBLE);
        btn_delete.setVisibility(View.INVISIBLE);
        btn_add.setVisibility(View.INVISIBLE);
        btn_edit.setVisibility(View.INVISIBLE);
        editFirstname.setVisibility(View.INVISIBLE);
        editLastname.setVisibility(View.INVISIBLE);
        editCity.setVisibility(View.INVISIBLE);
        editHousenumber.setVisibility(View.INVISIBLE);
        editStreet.setVisibility(View.INVISIBLE);
        editPhone.setVisibility(View.INVISIBLE);
    }

    public void AnulujDelete (View view){
        editID.setVisibility(View.INVISIBLE);
        bcancel.setVisibility(View.INVISIBLE);
        btn_usun.setVisibility(View.INVISIBLE);
        btn_delete.setVisibility(View.VISIBLE);
        btn_add.setVisibility(View.VISIBLE);
        btn_edit.setVisibility(View.VISIBLE);
        editFirstname.setVisibility(View.VISIBLE);
        editLastname.setVisibility(View.VISIBLE);
        editCity.setVisibility(View.VISIBLE);
        editHousenumber.setVisibility(View.VISIBLE);
        editStreet.setVisibility(View.VISIBLE);
        editPhone.setVisibility(View.VISIBLE);
    }

    public void AnulujEdit (View view){
        editID.setVisibility(View.INVISIBLE);
        btn_canceledit.setVisibility(View.INVISIBLE);
        bcancel.setVisibility(View.INVISIBLE);
        btn_edytuj.setVisibility(View.INVISIBLE);
        btn_delete.setVisibility(View.VISIBLE);
        btn_add.setVisibility(View.VISIBLE);
        btn_edit.setVisibility(View.VISIBLE);
        editFirstname.setVisibility(View.VISIBLE);
        editLastname.setVisibility(View.VISIBLE);
        editCity.setVisibility(View.VISIBLE);
        editHousenumber.setVisibility(View.VISIBLE);
        editStreet.setVisibility(View.VISIBLE);
        editPhone.setVisibility(View.VISIBLE);
    }

    public void pokazwiadomosc(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void PokazAll(View view) {
        Cursor cursor = baza.ShowWorkers();
        if (cursor.getCount() == 0) {
            pokazwiadomosc("Baza danych pusta", "Niczego nie znaleziono");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("ID : " + cursor.getString(0) + "\n");
            buffer.append("Imię : " + cursor.getString(1) + "\n");
            buffer.append("Nazwisko : " + cursor.getString(2) + "\n");
            buffer.append("Miasto : " + cursor.getString(3) + "\n");
            buffer.append("Numer domu : " + cursor.getString(4) + "\n");
            buffer.append("Ulica : " + cursor.getString(5) + "\n");
            buffer.append("Telefon : " + cursor.getString(6) + "\n");
        }

        pokazwiadomosc("", buffer.toString());
    }


}