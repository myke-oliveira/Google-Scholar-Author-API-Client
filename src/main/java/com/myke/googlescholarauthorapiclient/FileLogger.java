package com.myke.googlescholarauthorapiclient;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class FileLogger {   
    public static void logErrorGoogleScholarAuthorAPIJsonException(JSONException jsonException, JSONObject jsonObject) {
        Date date = new Date();
        String filename = String.format("google_scholar_author_api_jsoin_exception %s.log", date.toString());
        Path filepath = Paths.get("temp", "log", filename);

        StringBuilder stringBuilderFileContent = new StringBuilder(jsonException.getMessage());
        stringBuilderFileContent.append("\n");
        stringBuilderFileContent.append(jsonException.getStackTrace());
        stringBuilderFileContent.append("\n");
        stringBuilderFileContent.append(jsonObject);
        
        String fileContent = stringBuilderFileContent.toString();
        
        try (FileWriter fileWriter = new FileWriter(filepath.toString())) {
            fileWriter.write(fileContent);
            System.out.printf("AppError: Successful logged to %s.\n", filepath);
        } catch (IOException e) {
            System.out.println("AppError: It was not possible to log error to file.");
            e.printStackTrace();
        }
    }
    
}
