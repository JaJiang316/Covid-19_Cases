package com.example.covidcases;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatesFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatesFragment extends Fragment {
    TextView state_death;
    TextView state_deathIncrease;
    TextView states_positive;
    TextView states_positiveIncrease;
    TextView state_recovered;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;

    public static StatesFragment getInstance(){
        StatesFragment statesFragment = new StatesFragment();
        return statesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        state_death = view.findViewById(R.id.states_deaths);
        state_deathIncrease = view.findViewById(R.id.death_increase);
        states_positive = view.findViewById(R.id.states_positive);
        states_positiveIncrease = view.findViewById(R.id.states_positiveIncrease);
        state_recovered = view.findViewById(R.id.states_recovered);
        recyclerView = view.findViewById(R.id.States);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        statejsonParse();
        jsonParse();
        return view;
    }


    private void statejsonParse(){ //Object is trying to be converted to Array cant do that fix
        String url = "https://api.covidtracking.com/v1/states/current.json";
        ArrayList<CovidDataReportModel> report = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i<response.length(); i++){
                        JSONObject state = response.getJSONObject(i);
                        CovidDataReportModel model = new CovidDataReportModel();
                        model.setRecovered(String.valueOf(state.get("recovered")));
                        model.setState(state.getString("state"));
                        model.setDeathIncrease(String.valueOf(state.get("deathIncrease")));
                        model.setPositive(String.valueOf(state.get("positive")));
                        model.setPositiveIncrease(String.valueOf(state.get("positiveIncrease")));
                        model.setDeathsconfirmed(String.valueOf(state.get("deathConfirmed")));
                        report.add(model);
                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mAdapter = new RecyclerAdapter(report);
                    recyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
    }

    private void jsonParse() {
        String url = "https://api.covidtracking.com/v1/us/current.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    int deaths = (jsonObject.getInt("death")); //amount of deaths
                    int dincrease = (jsonObject.getInt("deathIncrease")); //deaths increase
                    int pincrease = (jsonObject.getInt("positiveIncrease"));
                    int positive = (jsonObject.getInt("positive"));
                    int recovered = (jsonObject.getInt("recovered"));
                    state_death.append(String.valueOf(deaths));
                    state_deathIncrease.append(String.valueOf(dincrease));
                    state_recovered.append(String.valueOf(recovered));
                    states_positive.append(String.valueOf(positive));
                    states_positiveIncrease.append(String.valueOf(pincrease));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Singleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
    }

}
