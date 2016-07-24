package com.example.fragdbwebapi;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fragdbwebapi.adapter.ContactsListAdapter;
import com.example.fragdbwebapi.db.DatabaseHelper;
import com.example.fragdbwebapi.model.Contact;
import com.example.fragdbwebapi.net.WebServices;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    public static ArrayList<Contact> mContactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebServices.getContacts(new WebServices.GetContactsCallback() {
            @Override
            public void onError(String errMessage) {

            }

            @Override
            public void onSuccess(ArrayList<Contact> contactArrayList) {
                mContactsList = contactArrayList;
                bindDataToRecyclerView();
            }
        });

/*
        // สร้างออบเจ็ค DatabaseHelper
        mHelper = new DatabaseHelper(this);

        // เชื่อมต่อฐานข้อมูล
        mDatabase = mHelper.getWritableDatabase();

        // คิวรีฐานข้อมูล
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        // วนลูป อ่านข้อมูลจาก Cursor มาเก็บลงโมเดล (คลาส Contact)
        // แล้วใส่โมเดลลงใน ArrayList
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
            String picture = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PICTURE));

            Contact contact = new Contact(name, phone, picture);
            mContactsList.add(contact);
        }

        // log ข้อมูลใน ArrayList ออกมาดู
        for (Contact contact : mContactsList) {
            String text = "Name: %s, Phone: %s, Picture: %s";
            String formatText = String.format(
                    text,
                    contact.name,
                    contact.phone,
                    contact.picture
            );
            Log.i("MainActivity", formatText);
        }

        bindDataToRecyclerView();
*/
    }

    private void bindDataToRecyclerView() {
        // สร้าง Adapter
        ContactsListAdapter adapter = new ContactsListAdapter(this, mContactsList, new ContactsListAdapter.OnContactItemClickListener() {
            @Override
            public void onContactItemClick(Contact contact, int position) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        // อ้างอิงไปยัง RecyclerView ใน layout (ไฟล์ activity_main.xml)
        RecyclerView contactRecyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);

        // สร้าง LinearLayoutManager เพื่อกำหนดทิศทางของ RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contactRecyclerView.setLayoutManager(llm);

        // ผูก Adapter เข้ากับ RecyclerView
        contactRecyclerView.setAdapter(adapter);
    }
}
