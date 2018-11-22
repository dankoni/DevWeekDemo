package com.example.devopsapp.devweek.quiz_ui.controls;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.quiz_ui.AnswerEvent;
import com.example.devopsapp.devweek.uidata.QuizViewModel;
import com.example.devopsapp.devweek.uidata.models.AnswerData;

import org.greenrobot.eventbus.EventBus;

public class BooleanFragment extends Fragment {

    private QuizViewModel mViewModel;
    private RadioButton mTrue;
    private RadioButton mFalse;

    private AnswerData data;

    public static BooleanFragment newInstance(AnswerData answerData) {
        BooleanFragment fragment = new BooleanFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", answerData);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getParcelable("data");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.boollean_answer, container, false);
        mTrue = (RadioButton) view.findViewById(R.id.true_radio);
        mFalse = (RadioButton) view.findViewById(R.id.false_radio);
        initAnswers();
        return view;
    }



    private void initAnswers() {
        mTrue.setOnClickListener(this::whichButtonIsChecked);
        mFalse.setOnClickListener(this::whichButtonIsChecked);
    }


    public void whichButtonIsChecked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.true_radio:
                if (checked)
                    mFalse.setChecked(false);
                checkAnswer(true);
                break;
            case R.id.false_radio:
                if (checked)
                    mTrue.setChecked(false);
                checkAnswer(false);
                break;
        }
    }

    private void checkAnswer(boolean answer) {

        if (answer == Boolean.valueOf(data.getCorrectAnswer())) {
            EventBus.getDefault().post(new AnswerEvent(true));
        } else {
            EventBus.getDefault().post(new AnswerEvent(false));
        }


    }


}
