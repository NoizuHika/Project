package com.example.API.controller;

import com.example.API.models.ALLBatoniki;
import com.example.API.models.Batoniki;
import com.example.API.models.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class WebController {

    @RequestMapping(value = "/{name}", method = GET)
    public String getEveryProdukt(@PathVariable String name){

            long id = 0;
            String nameW = null;
            String nameB = null;
            String nameT = null;
            String Gram = null;
            String nameBat = null;
            String bat = "batoniki";
            final String TAG_NAME = "name";


        Root root = new Root();

            JSONParser parser = new JSONParser();

            try (FileReader reader = new FileReader("Produkty.json")) {

                JSONObject rootJsonObject = (JSONObject) parser.parse(reader);

                JSONArray batonikiJsonArray = (JSONArray) rootJsonObject.get("batoniki");

                List<Batoniki> batonikiList = new ArrayList<>();
                List<ALLBatoniki> allBatonikiList = new ArrayList<>();
                for (Object it : batonikiJsonArray) {
                    JSONObject batonikiJsonObject = (JSONObject) it;

                    String nameBatoniki = (String) batonikiJsonObject.get(TAG_NAME);

                    if(name.toLowerCase().equals(bat.toLowerCase())){
                        nameBat = (String) batonikiJsonObject.get(TAG_NAME);

                        ALLBatoniki allBatoniki = new ALLBatoniki(nameBat);
                        allBatonikiList.add(allBatoniki);

                    }else if(nameBatoniki.toLowerCase().equals(name.toLowerCase())){
                        id = (Long)batonikiJsonObject.get("id");

                        nameW = (String) batonikiJsonObject.get("W");

                        nameB = (String) batonikiJsonObject.get("B");

                        nameT = (String) batonikiJsonObject.get("T");

                        Gram = (String) batonikiJsonObject.get("Gram");

                    }

                }
                if(nameW != null){
                    Batoniki batoniki = new Batoniki((int) id, nameW, nameB, nameT, Gram);
                    batonikiList.add(batoniki);

                    root.setBatoniki(batonikiList);
                    //return root;
                    return StringUtils.capitalize(name.toLowerCase()) + " " + batoniki;
                }else if(nameBat != null){
                    String list = "";
                    for(int i = 0; i < allBatonikiList.size(); i++){
                        if(list != ""){
                            list = list + " , " + allBatonikiList.get(i);
                        }else{
                            list = list + allBatonikiList.get(i);
                        }
                    }

                    return list;
                }else{
                    return "Error 404";
                }


            } catch (Exception e) {
                System.out.println("Parsing error " + e.toString());
            }

            return null;

    }
    
    http://localhost:8080/swagger-ui/

    /*@RequestMapping(value = "/{nazwa}/{W}/{B}/{T}/{Gramy}", method = GET){
        *//*kod*//*
    }*/
    /*@RequestMapping(value = "/test", method = RequestMethod.POST)
    public PostResponse Test(@RequestBody PostRequest inputPayload) {
        PostResponse response = new PostResponse();
        response.setId(inputPayload.getId()*100);
        response.setMessage("Batonik " + inputPayload.getName());
        response.setExtra("Some text");
        return response;
    }*/
}
