package com.example.fragdbwebapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fragdbwebapi.model.Contact;
import com.example.fragdbwebapi.net.WebServices;

public class ContactDetailFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int mPosition;

    public static ContactDetailFragment newInstance(int position) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Contact contact = MainActivity.mContactsList.get(mPosition);

        ImageView pictureImageView = (ImageView) view.findViewById(R.id.picture_image_view);
        TextView nameTextView = (TextView) view.findViewById(R.id.name_text_view);
        TextView phoneTextView = (TextView) view.findViewById(R.id.phone_text_view);
        ImageView phoneImageView = (ImageView) view.findViewById(R.id.phone_image_view);

        nameTextView.setText(contact.name);
        phoneTextView.setText(contact.phone);
        phoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contact.phone));
                startActivity(intent);
            }
        });

        String pictureUrl = WebServices.IMAGES_URL + contact.picture;
        Glide.with(this).load(pictureUrl).into(pictureImageView);
    }
}
