package com.example.fragdbwebapi.net;

import android.util.Log;

import com.example.fragdbwebapi.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Promlert on 7/23/2016.
 */
public class WebServices {

    private static final String BASE_URL = "http://10.0.3.2/FragDBWebAPI/";
    private static final String GET_CONTACTS_URL = BASE_URL + "get_contacts.php";
    public static final String IMAGES_URL = BASE_URL + "images/";

    private static final OkHttpClient mClient = new OkHttpClient();
    private static ArrayList<Contact> mContactArrayList;

    public interface GetContactsCallback {
        void onError(String errMessage);
        void onSuccess(ArrayList<Contact> contactArrayList);
    }

    public static void getContacts(final GetContactsCallback callback) {
        Request request = new Request.Builder()
                .url(GET_CONTACTS_URL)
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("WebServices", "ERROR: " + e.getMessage());
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonResult = response.body().string();
                Log.i("WebServices", jsonResult);

                try {
                    JSONObject jsonObject = new JSONObject(jsonResult);
                    int success = jsonObject.getInt("success");

                    if (success == 1) {
                        mContactArrayList = new ArrayList<>();
                        JSONArray contactsJsonArray = jsonObject.getJSONArray("contact_data");

                        for (int i = 0; i < contactsJsonArray.length(); i++) {
                            JSONObject contactJsonObject = contactsJsonArray.getJSONObject(i);

                            int id = contactJsonObject.getInt("id");
                            String name = contactJsonObject.getString("name");
                            String phone = contactJsonObject.getString("phone");
                            String picture = contactJsonObject.getString("picture");

                            Contact contact = new Contact(name, phone, picture);
                            mContactArrayList.add(contact);
                        }

                    } else if (success == 0) {
                        mContactArrayList = null;
                    }

                    callback.onSuccess(mContactArrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("WebServices", "Error parsing JSON.");
                }
            }
        });
    }

}
