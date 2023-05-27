package com.example.loginscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.loginscreen.adapter.Message;
import com.example.loginscreen.adapter.MessageAdapter;
import com.example.loginscreen.network.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RasaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    Button bck,vol,up,logout_rasa;
    Boolean mode;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasa);
        messageList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view1);
        messageEditText = findViewById(R.id.message_edit_text1);
        sendButton = findViewById(R.id.send_btn1);
        bck = findViewById(R.id.back1);
        vol = findViewById(R.id.button1);
        logout_rasa=findViewById(R.id.log2);

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        if (AcademicsTextActivity.flag2==1) {
            AcademicsTextActivity.flag2=0;
            String question = AcademicsTextActivity.text2.trim();
            addToChat(question,Message.SENT_BY_ME);
            messageEditText.setText("");
            callAPI(question);
        }

        logout_rasa.setOnClickListener((v -> {
            SessionManager.INSTANCE.clearData(this);
            SessionManager.INSTANCE.clearData2(this);

            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
            overridePendingTransition(0,0);
            finish();
        }));

        vol.setOnClickListener(v -> {
            int nightModeFlags =  v.getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;

                case Configuration.UI_MODE_NIGHT_NO:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;

                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    //   doStuff();
                    break;
            }
        });

        bck.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),AcademicsTextActivity.class));
            overridePendingTransition(0,0);
        });

        sendButton.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            if (question.equals("")) {
                Toast.makeText(this,"Enter a Message",Toast.LENGTH_LONG).show();
            }
            else {
                addToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");
                callAPI(question);
            }
        });
    }
    void addToChat(String message,String sentBy){
        runOnUiThread(() -> {
            messageList.add(new Message(message,sentBy));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
        });
    }

    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,Message.SENT_BY_BOT);
    }

    void callAPI(String question){
        //okhttp
        messageList.add(new Message("Typing... ",Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("sender","user");
            jsonBody.put("message",question);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://5393-117-239-78-56.ngrok-free.app/webhooks/rest/webhook")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if(response.isSuccessful()){
//                    JSONObject  jsonObject = null;
//                    try {
//                        jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
//                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
//                        String result = jsonArray.getJSONObject(0).getString("text");
//                        addResponse(result.trim());
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    addResponse("Failed to load response due to "+ Objects.requireNonNull(response.body()).string());
//                }
//            }
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String def="";
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        if (jsonArray.length() > 0) {
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject firstObject = jsonArray.getJSONObject(i); // get the first object in the array
                                String value = firstObject.getString("text"); // replace "key" with the name of the key you want to extract
                                def =  def + "\n" + value;
                            }
                            addResponse(def.trim());
                        } else {
                            addResponse("Empty response");
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    addResponse("Failed to load response due to " + response.body().string());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AcademicsTextActivity.class));
        overridePendingTransition(0,0);
    }
}