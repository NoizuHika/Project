package com.example.Project.model;

import java.util.List;
import java.util.Map;

public class BackupData {
    private String code;
    private List<Map<String, Object>> userSettings;

    // get & set
    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }
}
