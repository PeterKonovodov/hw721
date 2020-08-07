package com.example.hw721;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button searchBtn;
    EditText editTextCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = findViewById(R.id.searchBtn);
        editTextCoordinates = findViewById(R.id.editTextCoordinates);

        searchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String uriString = getUriString();
                Uri uri = Uri.parse(uriString);
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    private String getUriString() {
        String outString = editTextCoordinates.getText().toString();
        outString = outString.replaceFirst("\\s+", "");
        boolean isAddress = false;
        for (char c : outString.toCharArray()) {
            if (Character.isLetter(c)) {
                isAddress = true;
                break;
            }
        }
        if (isAddress) return "geo:" + "?q=" + outString;
        return "geo:" + outString;
    }


}