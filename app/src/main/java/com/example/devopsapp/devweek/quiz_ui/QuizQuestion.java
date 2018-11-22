package com.example.devopsapp.devweek.quiz_ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.uidata.QuizViewModel;
import com.example.devopsapp.devweek.uidata.models.AnswerData;
import com.example.devopsapp.devweek.uidata.models.QuestionData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class QuizQuestion extends Fragment {
    private QuizViewModel mViewModel;

    private TextView mQuestionTextView;
    private String mType;
    private String mTextOfQuestion;

    private List<QuestionData> mQuestionList;

    private int questionIndex;

    public QuizQuestion() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_quiz, container, false);
        mQuestionTextView = view.findViewById(R.id.question_text);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AnswerEvent(AnswerEvent event) {
        Toast.makeText(getActivity(), String.valueOf(event.isCorrect()), Toast.LENGTH_LONG).show();
        setNextQuestion();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        mViewModel.getQuestionLiveData().observe(this, this::onQuestionLoaded);
        mViewModel.loadQuestion();
    }

    private void onQuestionLoaded(List<QuestionData> questionData) {
        mQuestionList = questionData;
        if (mQuestionList.size() == 10) {
            setNextQuestion();
        }

    }

    private void setNextQuestion() {


        if (questionIndex < mQuestionList.size()) {
            Log.d("TESTDATA", "setNextQuestion view");
            mTextOfQuestion = mQuestionList.get(questionIndex).getText();
            mType = mQuestionList.get(questionIndex).getType();
            initViews(mQuestionList.get(questionIndex).getAnswerData());
            questionIndex++;
        } else {
            Log.d("TESTDATA", "setNextQuestion load");
            mViewModel.loadQuestion();
            questionIndex = 0;
        }
    }


    private void initViews(AnswerData data) {
        mQuestionTextView.setText(mTextOfQuestion);
        FragmentManager fragmentManager = getChildFragmentManager();

        if (mType.contentEquals("multiple")) {
            MultipleFragment multipleFragment = MultipleFragment.newInstance(data);
            fragmentManager.beginTransaction().replace(R.id.answer_holder, multipleFragment).commit();
        } else if (mType.contentEquals("boolean")) {
            BooleanFragment booleanFragment = BooleanFragment.newInstance(data);
            fragmentManager.beginTransaction().replace(R.id.answer_holder, booleanFragment).commit();
        }


    }


}
