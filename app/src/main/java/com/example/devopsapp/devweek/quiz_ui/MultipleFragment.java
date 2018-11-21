package com.example.devopsapp.devweek.quiz_ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.uidata.QuizViewModel;
import com.example.devopsapp.devweek.uidata.models.AnswerData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MultipleFragment extends Fragment {

    private QuizViewModel mViewModel;

    private AnswerData data;

    private List<RadioFragment> fragmentBag;

    public static MultipleFragment newInstance(AnswerData data) {

        MultipleFragment fragment = new MultipleFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", data);
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
        View view = inflater.inflate(R.layout.multiple_fragment, container, false);
        initView();
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


    private void initView() {
        FragmentManager fragmentManager = getChildFragmentManager();
        int number = 0;

        fragmentBag = new ArrayList<>();

        for (String text : data.getAnswersSet()
                ) {
            RadioFragment radioFragment = RadioFragment.newInstance(text);
            fragmentManager.beginTransaction().add(R.id.answer_holder_set, radioFragment, "radio " + number).commit();
            number++;
            fragmentBag.add(radioFragment);
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRadioClicked(RadioClicked event) {
        String tag = event.getTag();

        for (RadioFragment fragment : fragmentBag
                ) {

            if (tag.contentEquals(fragment.getTag())) {
                fragment.setClicked(true);
            } else {
                fragment.setClicked(false);
            }
        }

    }





}
