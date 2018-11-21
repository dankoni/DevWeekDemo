package com.example.devopsapp.devweek.quiz_ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.devopsapp.devweek.R;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RadioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RadioFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARGUMENT = "text";

    private String answerText;

    private RadioButton mRadioButton;


    public RadioFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RadioFragment newInstance(String param1) {
        RadioFragment fragment = new RadioFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            answerText = getArguments().getString(ARGUMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.radio_button_answer, container, false);
        mRadioButton = view.findViewById(R.id.radioButton);
        mRadioButton.setText(answerText);

        mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RadioClicked(RadioFragment.this.getTag()));
            }
        });
        return view;
    }

    public void setClicked(boolean clicked) {
        mRadioButton.setChecked(clicked);
    }

}
