package com.example.nayanjyoti.sndpostrequest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nayanjyoti.sndpostrequest.R;
import com.example.nayanjyoti.sndpostrequest.api.model.User;
import com.example.nayanjyoti.sndpostrequest.api.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);

        Button createAccount = findViewById(R.id.btn_create);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(
                        name.getText().toString(),
                        email.getText().toString()
                );

                sendNetworkRequest(user);
            }
        });
    }

    private void sendNetworkRequest(User user) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.225.132/recharge/public/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("response", response.body().toString());
                Toast.makeText(MainActivity.this, "Id"+response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
