package com.example.modalandpersistentbottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ModalSheetActivity extends AppCompatActivity implements ModalSheetDialog.BottomSheetListener {
    private Button buttonOpenModalBottomSheet;
    private TextView buttonClickTextView;

    private void initialise() {
        buttonOpenModalBottomSheet = findViewById(R.id.button_open_modal_bottom_sheet);
        buttonClickTextView = findViewById(R.id.text_view_button_clicked);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_sheet);

        initialise();

        buttonOpenModalBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalSheetDialog modalBottomSheet = new ModalSheetDialog();
                modalBottomSheet.show(getSupportFragmentManager(), "modalBottomSheet");
            }
        });
    }

    @Override
    public void onButtonClicked(String text, Integer buttonNumber) {
        buttonClickTextView.setText("Button " + buttonNumber + " clicked, Using string: " + text);
    }
}