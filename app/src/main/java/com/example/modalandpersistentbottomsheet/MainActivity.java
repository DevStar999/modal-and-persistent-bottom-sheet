package com.example.modalandpersistentbottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button gotoModalSheetButton;
    private Button gotoPersistentSheetButton;

    private void initialise() {
        gotoModalSheetButton = findViewById(R.id.modal_button);
        gotoPersistentSheetButton = findViewById(R.id.persistent_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        gotoModalSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ModalSheetActivity.class);
                startActivity(intent);
            }
        });

        gotoPersistentSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PersistentSheetActivity.class);
                startActivity(intent);
            }
        });
    }
}