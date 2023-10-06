package com.example.fetchmobileassess;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_LINK = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

    }
    private void fetchData(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> jsonFuture = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return fetchJson();
            }
        });
        executor.shutdown();
        try{
            String json = jsonFuture.get();
            List<Item> itemList = parseJson(json);
            displayData(itemList);
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Failed to get JSON data", Toast.LENGTH_SHORT).show();
        }
    }
    private String fetchJson() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(JSON_LINK).build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        } else {
            throw new IOException("Network Error");
        }
    }
    private List<Item> parseJson(String json){
        Gson gson = new Gson();
        Item[] items = gson.fromJson(json, Item[].class);
        List<Item> itemList = new ArrayList<>();
        Collections.addAll(itemList, items);

        //Remove names that are blank or null
        itemList.removeIf(item -> item.getName() == null || item.getName().isEmpty());

        Collections.sort(itemList, (item1, item2) -> {
            if (item1.getListId() != item2.getListId()) {
                return Integer.compare(item1.getListId(), item2.getListId());
            } else {
                int itemNumber1 = extractItemNumber(item1.getName());
                int itemNumber2 = extractItemNumber(item2.getName());

                return Integer.compare(itemNumber1, itemNumber2);
            }
        });

        return itemList;
    }
    private int extractItemNumber(String name) {
        try {
            String number = name.replaceAll("\\D+", "");
            return Integer.parseInt(number);
        } catch (NumberFormatException e){
            return Integer.MAX_VALUE;
        }
    }

    private void displayData(List<Item> itemList) {
        itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
    }
}
