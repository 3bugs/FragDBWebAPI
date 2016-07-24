package com.example.fragdbwebapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fragdbwebapi.R;
import com.example.fragdbwebapi.model.Contact;
import com.example.fragdbwebapi.net.WebServices;

import java.util.ArrayList;

/**
 * Created by Promlert on 7/23/2016.
 */
public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Contact> mContactArrayList;
    private OnContactItemClickListener mListener;

    public interface OnContactItemClickListener {
        void onContactItemClick(Contact contact, int position);
    }

    public ContactsListAdapter(Context context, ArrayList<Contact> contactArrayList, OnContactItemClickListener listener) {
        mContext = context;
        mContactArrayList = contactArrayList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mContactArrayList.get(position).name);
        holder.mPhoneTextView.setText(mContactArrayList.get(position).phone);

        String pictureFileName = mContactArrayList.get(position).picture;
        String pictureUrl = WebServices.IMAGES_URL + pictureFileName;

        Glide.with(mContext).load(pictureUrl).into(holder.mPictureImageView);

/*
        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable image = Drawable.createFromStream(stream, null);
            holder.mPictureImageView.setImageDrawable(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onContactItemClick(mContactArrayList.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mItemLayout;
        private ImageView mPictureImageView;
        private TextView mNameTextView;
        private TextView mPhoneTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemLayout = itemView;
            mPictureImageView = (ImageView) itemView.findViewById(R.id.picture_image_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            mPhoneTextView = (TextView) itemView.findViewById(R.id.phone_text_view);
        }
    }
}
