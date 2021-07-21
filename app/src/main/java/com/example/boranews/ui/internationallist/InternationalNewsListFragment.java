package com.example.boranews.ui.internationallist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.boranews.R;
import com.example.boranews.SearchActivity;

public class InternationalNewsListFragment extends Fragment implements View.OnClickListener {

    CardView cvUnitedStates, cvKorea, cvJepang;
    ImageView searchLogo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_international_list, container, false);

        cvUnitedStates = view.findViewById(R.id.cardViewUS);
        cvUnitedStates.setOnClickListener(this);

        cvKorea = view.findViewById(R.id.cardViewKorea);
        cvKorea.setOnClickListener(this);

        cvJepang = view.findViewById(R.id.cardViewJepang);
        cvJepang.setOnClickListener(this);

        searchLogo = view.findViewById(R.id.search2);
        searchLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(i);
            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), InternationalNewsActivity.class);
        if (v.getId() == R.id.cardViewUS) {
            intent.putExtra("country","us");
        } else if (v.getId() == R.id.cardViewKorea) {
            intent.putExtra("country","kr");
        } else if (v.getId() == R.id.cardViewJepang) {
            intent.putExtra("country", "jp");
        }

        startActivity(intent);
    }
}
