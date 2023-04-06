package com.example.bitebook_new;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import java.util.ArrayList;

public class HomePage extends Fragment {
    RecyclerView homeRecycler;
    RecyclerView.Adapter adapter;
    ArrayList<String> items;
    ArrayList<Entry> entries;
    Spinner areaSpinner;
    Spinner cuisineSpinner;
    Spinner priceSpinner;


    public HomePage()
    {
        // require a empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        // TODO question: what's the purpose of items arrayList
        items = new ArrayList<>();
        items.add("First item");
        items.add("se item");
        items.add("s item");

        areaSpinner = view.findViewById(R.id.areaSpinner);
        cuisineSpinner = view.findViewById(R.id.cuisineSpinner);
        priceSpinner = view.findViewById(R.id.priceSpinner);

        // set up the spinners
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                getContext(), R.array.areaSpinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                getContext(), R.array.cuisineSpinner, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuisineSpinner.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                getContext(), R.array.priceSpinner, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(adapter3);


        entries = new ArrayList<>();

        Entry item1 = new Entry("McDonalds", "BigMac","3", "Central", "Western",3, "Best food ever");
        Entry item2 = new Entry("Spize","menu" ,"2", "East", "Mix", 3, "Great burgers");
        Entry item3 = new Entry("Ya Kun", "kaya toast", "4", "Central", "Bfast",5, "Love da toast");

        entries.add(item1);
        entries.add(item2);
        entries.add(item3);



        homeRecycler = (RecyclerView) view.findViewById(R.id.homeRecycler);


        Log.i("asdf","ASDFASDFASDFASFD");

        Context context = container.getContext();



        LinearLayoutManager layoutManager =new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Log.i("asdf", String.valueOf(layoutManager));
        homeRecycler.setLayoutManager(layoutManager);
        adapter = new HomeCardAdapter(context, entries);
        homeRecycler.setAdapter(adapter);



//        homeRecycler.setLayoutManager(new LinearLayoutManager(context));
//        adapter = new HomeCardAdapter(container.getContext(), items);
//        homeRecycler.setAdapter(adapter);
        return view;
    }
}
