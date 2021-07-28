package com.example.workyoutube;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TextFragment extends Fragment {

    public static final String ARG_TEXT = "text";
    private Note note;

    public static TextFragment newInstate(Note note) {
        TextFragment text = new TextFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_TEXT, note);
        text.setArguments(args);
        return text;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note =  getArguments().getParcelable(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_text_fragmnt, container, false);

        TextView textView = view.findViewById(R.id.texts);
        TextView textViewDate = view.findViewById(R.id.date_text);
        textViewDate.setTextSize(11);
        String date = time();
        textView.setText(note.getText());
        textViewDate.setText(date);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public String time() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy 'и время' hh:mm:ss");
        String timer = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return timer;
    }
}