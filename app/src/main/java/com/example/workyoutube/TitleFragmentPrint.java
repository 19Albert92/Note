package com.example.workyoutube;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TitleFragmentPrint extends Fragment {

    private boolean isLandscape;
    private static final String CURRENT_TEXT = "currentText";
    private Note currenNote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_title_fragment_print, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currenNote = savedInstanceState.getParcelable(CURRENT_TEXT);
        } else {
            currenNote = new Note(getResources().getStringArray(R.array.text)[0], getResources().getStringArray(R.array.title_text)[0]);
        }

        if (isLandscape) {
            showLandText(currenNote);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) view;
        String[] titles = getResources().getStringArray(R.array.title_text);
        for (int i = 0; i < titles.length; i++) {
            String text = titles[i];
            TextView textView = new TextView(getContext());
            textView.setText(text);
            textView.setTextSize(40);
            linearLayoutCompat.addView(textView);
            final int test = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currenNote = new Note(titles[test], getResources().getStringArray(R.array.title_text)[test]);
                    showPrint(currenNote);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_TEXT, currenNote);
        super.onSaveInstanceState(outState);
    }

    public void showPrint(Note currenNote) {
        if (isLandscape) {
            showLandText(currenNote);
        } else {
            showPortText(currenNote);
        }
    }

    private void showLandText(Note currenNote) {
        TextFragment textF = TextFragment.newInstate(currenNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.right_text, textF);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void showPortText(Note currenNote) {
        Intent intent = new Intent(getActivity(), TextActivity.class);
        intent.putExtra(TextFragment.ARG_TEXT, currenNote);
        startActivity(intent);
    }
}