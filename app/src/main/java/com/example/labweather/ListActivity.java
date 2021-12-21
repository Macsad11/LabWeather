package com.example.labweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.labweather.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    public static String apiK = "4e0b5269a7dd9d5584f8e586827b6139";
    public static String BaseUrlApi = "http://api.openweathermap.org/";

    ArrayList<Goroda> gorodaTemperat = new ArrayList<>();

    String[] goroda = {"Cartagena",
            "Moscow",
            "Los Angeles",
            "Rio",
            "Barcelona",
            "Beijing",
            "Beirut",
            "Bergen",
            "Bruges",
            "Budapest",
            "Buenos Aires",
            "Chefchaouen",
            "Copenhagen",
            "Cusco",
            "Doha",
            "Tashkent",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        RecyclerView recyclerView = findViewById(R.id.recyView);

        for (int i = 0; i < goroda.length; i++) {
            // делаем запрос на сервер через ретрофит
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrlApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Service service = retrofit.create(Service.class);


            Call<ResponseWeatherPojo> call = service.getWeather(goroda[i], apiK);

            // пишем функцию callback
            int finalI = i;
            call.enqueue(new Callback<ResponseWeatherPojo>() {
                @Override
                public void onResponse(@NonNull Call<ResponseWeatherPojo> call, @NonNull Response<ResponseWeatherPojo> response) {
                    if (response.code() == 200) {
                        ResponseWeatherPojo weatherR = response.body();
                        assert weatherR != null;
                        double gradus = weatherR.main.temp - 273.15;
                        gorodaTemperat.add(new Goroda(goroda[finalI], gradus + "g" ));
                        recyclerView.setHasFixedSize(true);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        AdapterRecycleView adapter = new AdapterRecycleView(ListActivity.this, gorodaTemperat);
                        recyclerView.setAdapter(adapter);

                    }

                }


                @Override
                public void onFailure(Call<ResponseWeatherPojo> call, Throwable t) {
                    Log.d("TAG", t.getMessage());
                }

            });
        }


    }





}