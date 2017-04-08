package com.imdb.SimulacionCarnet;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Martin on 07/04/2017.
 */

public class JsonConverter {

    public static String queueToJson(ArrayList<Client> queue){
        try {
            JSONArray list = new JSONArray();
            for (Client client : queue ) {
                JSONObject clientToJson = new JSONObject();
                clientToJson.put("elapsedTime",client.getElapsedTime());
                clientToJson.put("state", client.getState());
                clientToJson.put("id",client.getId());
                list.put(clientToJson);
            }
            return list.toString();
        }catch (Exception ex){
            return "error";
        }
    }
}
