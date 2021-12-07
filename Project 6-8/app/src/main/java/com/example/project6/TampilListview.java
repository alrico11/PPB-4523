package com.example.project6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TampilListview extends AppCompatActivity {
    BantuDatabase bd;
    public static ListView listView;
    public static EditText editText;
    Button tblTambah;

    ArrayAdapter adapter;
    ArrayList<String> lisviewku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_listview);

        listView=findViewById(R.id.listdatabuah);
        editText=findViewById(R.id.databuah);
        tblTambah=findViewById(R.id.tbltambah);
        lisviewku=new ArrayList<>();
        tampilkan_buah();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position=0;
                final String noid=lisviewku.get(position);
                final String nomor=noid.substring(0,2);
                //editText.setText(nomor.toString());
                new AlertDialog.Builder(TampilListview.this)
                        .setTitle("Tunggu !")
                        .setMessage("Yakin Di Hapus ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                bd.hapusRecord(Integer.parseInt(nomor));
                                lisviewku.remove(position);
                                listView.invalidate();

                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return false;
            }
        });
        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.tambahData(editText.getText().toString());
                Toast.makeText(TampilListview.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                lisviewku.clear();
                tampilkan_buah();
            }
        });
    }

    private void tampilkan_buah() {
        Cursor cursor=bd.tampilBuah();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "Record Kosong Gan", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext())
            {
                lisviewku.add(String.valueOf(cursor.getInt(0))+" "+cursor.getString(1));
            }
            adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lisviewku);
            listView.setAdapter(adapter);
        }
    }
}