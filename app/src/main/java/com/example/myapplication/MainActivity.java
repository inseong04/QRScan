package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    private TextView text;
    private TextView name;
    private Button scan_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        name = findViewById(R.id.name);
        scan_button = findViewById(R.id.scan_button);

        qrScan = new IntentIntegrator(this);

        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.setPrompt("스캔중");
                qrScan.initiateScan();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
            }
            else {
                Toast.makeText(this, "스캔완료", Toast.LENGTH_SHORT).show();
                try{

                    JSONObject obj = new JSONObject(result.getContents());
                    name.setText(obj.getString("name"));
                    text.setText(obj.getString("text"));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }
    }
}