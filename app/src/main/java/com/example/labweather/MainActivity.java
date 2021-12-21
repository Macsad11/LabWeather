package com.example.labweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.labweather.service.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String apiK = "4e0b5269a7dd9d5584f8e586827b6139";
    public static String BaseUrlApi = "http://api.openweathermap.org/";

    TextView textPogod;
    Button pogodaSey;
    Button pogodaSpisok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textPogod = findViewById(R.id.textView);
        pogodaSey = findViewById(R.id.pogodaKn);
        pogodaSpisok = findViewById(R.id.spisokG);


        pogodaSey.setOnClickListener(v -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrlApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Service service = retrofit.create(Service.class);

            Call<ResponseWeatherPojo> call = service.getWeather("Moscow", apiK);


            call.enqueue(new Callback<ResponseWeatherPojo>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<ResponseWeatherPojo> call, @NonNull Response<ResponseWeatherPojo> response) {
                    if (response.code() == 200) {
                        ResponseWeatherPojo weatherResponse = response.body();
                        assert weatherResponse != null;
                        double temp = weatherResponse.main.temp - 274.15;

                        textPogod.setText("Температура сейчас: " + temp);
                    }
                }

                @Override
                public void onFailure(Call<ResponseWeatherPojo> call, Throwable t) {
                    textPogod.setText(t.getMessage());
                    Log.d("TAG", t.getMessage());
                }

            });

        });

        pogodaSpisok.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        });


    }





}