package com.example.devopsapp.devweek.quiz_ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.uidata.QuizViewModel;
import com.example.devopsapp.devweek.uidata.models.AnswerData;

public class MultipleFragment extends Fragment {

    private QuizViewModel mViewModel;

    private AnswerData data;

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

    private void initView() {
        FragmentManager fragmentManager = getChildFragmentManager();

        for (String text : data.getAnswersSet()
                ) {
            RadioFragment radioFragment = RadioFragment.newInstance(text);
            fragmentManager.beginTransaction().add(R.id.answer_holder_set, radioFragment).commit();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        // TODO: Use the ViewModel
    }


    public void whichButtonIsChecked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {

        }
        //TODO check other to uncheck
    }

}
