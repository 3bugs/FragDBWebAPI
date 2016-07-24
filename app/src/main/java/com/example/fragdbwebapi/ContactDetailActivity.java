package com.example.fragdbwebapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fragdbwebapi.adapter.ContactsPagerAdapter;

public class ContactDetailActivity extends AppCompatActivity {

    private ViewPager mPager;
    private Button mPreviousButton, mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        // อ้างอิงไปยัง ViewPager
        mPager = (ViewPager) findViewById(R.id.view_pager);

        // สร้างออบเจ็ค Adapter
        ContactsPagerAdapter adapter = new ContactsPagerAdapter(getSupportFragmentManager());

        // ผูก ViewPager เข้ากับ Adapter
        mPager.setAdapter(adapter);
        // กำหนดหน้าเริ่มต้นที่จะให้ ViewPager แสดงออกมา
        mPager.setCurrentItem(position);

        // อ้างอิงไปยัง TabLayout และผูก TabLayout เข้ากับ ViewPager
        TabLayout tab = (TabLayout) findViewById(R.id.tab_layout);
        tab.setupWithViewPager(mPager);

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mNextButton = (Button) findViewById(R.id.next_button);

        updateButtonStatus();

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() > 0) {
                    mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                    updateButtonStatus();
                }
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() < MainActivity.mContactsList.size() - 1) {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    updateButtonStatus();
                }
            }
        });

/*
        // สร้างออบเจ็ค Fragment (อินสแทนซ์ของคลาส ContactDetailFragment)
        ContactDetailFragment fragment = ContactDetailFragment.newInstance(position);

        // เข้าถึง Fragment Manager
        FragmentManager fm = getSupportFragmentManager();
        // สร้าง Transaction
        FragmentTransaction transaction = fm.beginTransaction();
        // ใส่ Fragment ลงใน FrameLayout ที่อยู่ใน Layout ของ Activity
        //transaction.replace(R.id.fragment_container, fragment);
        //transaction.commit();
*/
    }

    private void updateButtonStatus() {
        if (mPager.getCurrentItem() == 0) {
            mPreviousButton.setEnabled(false);
        } else {
            mPreviousButton.setEnabled(true);
        }

        if (mPager.getCurrentItem() == MainActivity.mContactsList.size() - 1) {
            mNextButton.setEnabled(false);
        } else {
            mNextButton.setEnabled(true);
        }

    }
}
