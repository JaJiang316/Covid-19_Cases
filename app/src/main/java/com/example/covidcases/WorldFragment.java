package com.example.covidcases;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorldFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorldFragment extends Fragment {
    TextView Cases;
    TextView Deaths;
    TextView Recovered;

    public static WorldFragment getInstance(){
        WorldFragment worldFragment = new WorldFragment();
        return worldFragment;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    public class doit extends AsyncTask<Void, Void, Void> {
        final WorldData worldData = new WorldData();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").timeout(6000).get();
                Elements counter = doc.body().getElementsByClass("maincounter-number");
                ArrayList<String> count_num = new ArrayList<>();
                for(Element count: counter){
                    Elements texts = count.getElementsByTag("span");
                    Element text2 = texts.first();
                    String nums = text2.text();
                    count_num.add(nums);
                }
                worldData.setCases(count_num.get(0));
                worldData.setDeath(count_num.get(1));
                worldData.setRecovered(count_num.get(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Cases.setText(worldData.getCases());
            Deaths.setText(worldData.getDeath());
            Recovered.setText(worldData.getRecovered());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Cases = view.findViewById(R.id.Cases);
        Deaths = view.findViewById(R.id.Deaths);
        Recovered = view.findViewById(R.id.Recovered);
        new doit().execute();
        return view;
    }


}
