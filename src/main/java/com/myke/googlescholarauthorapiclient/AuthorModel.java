/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myke.googlescholarauthorapiclient;

import org.json.JSONObject;

/**
 *
 * @author myke
 */
public class AuthorModel {
    private String name;
    private String affiliations;
    private String email;
    private String website;
    
    private GoogleScholarAuthorAPI api;
    
    AuthorModel(GoogleScholarAuthorAPI api) {
        this.api = api;
    }
    
    public void query(String authorId) throws GoogleScholarAuthorAPIError {
        String response = this.api.request(authorId);
        JSONObject jsonObject = new JSONObject(response);
        JSONObject author = (JSONObject) jsonObject.get("author");
        
        try {
            this.name = (String) author.get("name");
            this.affiliations = (String) author.get("affiliations");
            this.email = (String) author.get("email");
            this.website = (String) author.get("website");
        } catch (Exception e) {
            
        }
        
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
