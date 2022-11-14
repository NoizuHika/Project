package com.example.API;

import com.example.API.models.Batoniki;
import com.example.API.models.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static final String TAG_BATONIKI = "batoniki";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_W = "W";
    private static final String TAG_B = "B";
    private static final String TAG_T = "T";
    private static final String TAG_Gram = "Gram";

    public Root parse() {

        Root root = new Root();

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("Produkty.json")) {

            JSONObject rootJsonObject = (JSONObject) parser.parse(reader);

            JSONArray batonikiJsonArray = (JSONArray) rootJsonObject.get(TAG_BATONIKI);

            List<Batoniki> batonikiList = new ArrayList<>();
            for (Object it : batonikiJsonArray) {
                JSONObject batonikiJsonObject = (JSONObject) it;

                long id = (Long)batonikiJsonObject.get(TAG_ID);

                String nameBatoniki = (String) batonikiJsonObject.get(TAG_NAME);

                String nameW = (String) batonikiJsonObject.get(TAG_W);

                String nameB = (String) batonikiJsonObject.get(TAG_B);

                String nameT = (String) batonikiJsonObject.get(TAG_T);

                String Gram = (String) batonikiJsonObject.get(TAG_Gram);

                Batoniki batoniki = new Batoniki((int) id, nameW, nameB, nameT, Gram);
                batonikiList.add(batoniki);
            }


            root.setBatoniki(batonikiList);

            return root;
        } catch (Exception e) {
            System.out.println("Parsing error " + e.toString());
        }

        return null;
    }
}
