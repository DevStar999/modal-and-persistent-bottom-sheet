package com.example.modalandpersistentbottomsheet;

import android.graphics.Outline;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class PersistentSheetActivity extends AppCompatActivity {
    private View bottomSheet;
    private TextView statusTextView;
    private Button expandButton;
    private Button collapseButton;

    private BottomSheetBehavior bottomSheetBehavior;

    private void initialise() {
        bottomSheet = findViewById(R.id.persistent_bottom_sheet);
        // Setting outline to bottom sheet as follows
        bottomSheet.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                float curveRadius = 50f;
                outline.setRoundRect(0, 0, view.getWidth(),
                        Float.valueOf(view.getHeight()+curveRadius).intValue(), curveRadius);
            }
        });
        bottomSheet.setClipToOutline(true);
        statusTextView = findViewById(R.id.persistent_bottom_sheet_status_text_view);
        expandButton = findViewById(R.id.expand_button);
        collapseButton = findViewById(R.id.collapse_button);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistent_sheet);

        initialise();

        /* Notes related to Persistent Bottom Sheet are as follows :
        1) The Persistent Bottom Sheet only works in a Coordinator layout, so we need to change the root layout
        from the default Constraint layout to Coordinator layout.
        2) The Coordinator layout is just a container which helps it's child views to move relative to each other.
        */

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        collapseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        statusTextView.setText("Status: Collapsed");
                        break;
                    }
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        // Dragging is only called when we adjust the bottom sheet manually with our hands or mouse
                        statusTextView.setText("Status: Dragging...");
                        break;
                    }
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        statusTextView.setText("Status: Expanded");
                        break;
                    }
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        statusTextView.setText("Status: Hidden");
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        // This is called at the exact moment when we leave the bottom sheet and it has to snap either to
                        // an expanded state or collapsed state
                        statusTextView.setText("Status: Settling...");
                        break;
                    }
                    case BottomSheetBehavior.STATE_HALF_EXPANDED: {
                        statusTextView.setText("Status: Half-Expanded");
                        break;
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // This method is called when the method is sliding, so when adjust the bottom sheet using buttons,
                // this method will be called.
                statusTextView.setText("Status: Sliding...");
            }
        });
    }
}