package com.example.modalandpersistentbottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_bottom_sheet, container, false);

        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked("Button 1 was clicked", 1);
                dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked("Button 2 was clicked", 2);
                dismiss();
            }
        });

        return view;
    }

    public interface BottomSheetListener {
        // 1. We will use this method for the clicks on the buttons on the Modal Bottom Sheet.
        // 2. Note here in the above parameters of the methods more/less parameters can be used to send
        // whatever information we want to send.
        void onButtonClicked(String text, Integer buttonNumber);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }
}
