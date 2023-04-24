package com.myke.googlescholarauthorapiclient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorModel {
    private String id;
    private String name;
    private String affiliations;
    private String email;
    private String website;
    
    private final GoogleScholarAuthorAPI api;
    private final DBUtil dbUtil;
    
    AuthorModel(GoogleScholarAuthorAPI api, DBUtil dbUtil) {
        this.api = api;
        this.dbUtil = dbUtil;
    }
    
    public void query(String authorId) throws GoogleScholarAuthorAPIError, AppError  {
        try {
            this.queryToDataBase(authorId);
        } catch (NotFound e) {
            this.queryToGoogleScholarAuthorAPI(authorId);
            this.insertIntoDataBase();
        }
    }
    
    private void queryToGoogleScholarAuthorAPI(String authorId) throws GoogleScholarAuthorAPIError, AppError {
        String response = this.api.request(authorId);
        JSONObject jsonObject = new JSONObject(response);
        
        try {
            JSONObject author = (JSONObject) jsonObject.get("author");
            this.id = authorId;
            this.name = (String) author.get("name");
            this.affiliations = (String) author.get("affiliations");
            this.email = (String) author.get("email");
            this.website = (String) author.get("website");
        } catch (JSONException e) {
            FileLogger.logErrorGoogleScholarAuthorAPIJsonException(e, jsonObject);
            throw new AppError();
        }
    }
    
    private void queryToDataBase(String authorId) throws NotFound {
        String query = "SELECT * FROM authors WHERE google_api_author_id = ? LIMIT 1";
        List<String> binds = Arrays.asList(authorId);
        ResultSet result = this.dbUtil.executeQuery(query, binds);
        
        try {
            if(!result.next()) throw new NotFound();
            this.id = result.getString("google_api_author_id");
            this.name = result.getString("name");
            this.affiliations = result.getString("affiliations");
            this.email = result.getString("email");
            this.website = result.getString("website");
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insertIntoDataBase() {
        String sql = "INSERT INTO authors"
                + "(google_api_author_id, name, affiliations, email, website)"
                + "values (?, ?, ?, ?, ?)";
        List<String> binds = Arrays.asList(this.id, this.name, this.affiliations, this.email, this.website);
        
        this.dbUtil.executeManipulation(sql, binds);
    }

    public String getName() {
        return name;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "AuthorModel{" + "name=" + name + ", affiliations=" + affiliations + ", email=" + email + ", website=" + website + '}';
    }
}
