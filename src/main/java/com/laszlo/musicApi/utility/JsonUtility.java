package com.laszlo.musicApi.utility;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtility {
    public static String SONGSPATH = "src/main/resources/songs.json";
    public static String ARTISTSPATH = "src/main/resources/artists.json";

    public static String readFileAsString(String file)
    {
        String content = "";
        try{
            content = new String(Files.readAllBytes(Paths.get(file)));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
