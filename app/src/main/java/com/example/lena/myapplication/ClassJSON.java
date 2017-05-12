package com.example.lena.myapplication;

/**
 * Created by Lena on 29.04.2017.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ClassJSON {

    public static double CountFreeBikes(String url) throws JSONException
    {
        double count=0;
        JSONObject json = new JSONObject(url);
        JSONObject net = json.getJSONObject("network");
        JSONArray array = net.getJSONArray("stations");

        for(int i=0; i<array.length(); i++)
        {
            count += Double.parseDouble(array.getJSONObject(i).getString("free_bikes"));
        }

        return count;
    }


    public static double CountSlots(String url) throws JSONException
    {
        double slot=0;
        JSONObject json = new JSONObject(url);
        JSONObject net = json.getJSONObject("network");
        JSONArray array = net.getJSONArray("stations");

        for(int i=0; i<array.length(); i++)
        {
            slot += Double.parseDouble(array.getJSONObject(i).getString("empty_slots"));
        }

        return slot;
    }
}
