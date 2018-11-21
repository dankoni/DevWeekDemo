package com.example.devopsapp.devweek.quiz_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.uidata.QuizViewModel;
import com.example.devopsapp.devweek.uidata.models.AnswerData;

public class BooleanFragment extends Fragment {

    private QuizViewModel mViewModel;
    private RadioButton mTest;
    private AnswerData data;

    private OnUserAnswered mUserAnsweredListener;

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
        mTest = view.findViewById(R.id.true_radio);
        initAnswers();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserAnswered) {
            mUserAnsweredListener = (OnUserAnswered) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUserAnswered");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUserAnsweredListener = null;
    }


    private void initAnswers() {
        mTest.setText(data.getCorrectAnswer());
    }




}
