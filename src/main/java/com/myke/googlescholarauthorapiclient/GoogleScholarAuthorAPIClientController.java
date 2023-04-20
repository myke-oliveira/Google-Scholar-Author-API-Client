/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myke.googlescholarauthorapiclient;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author myke
 */
public class GoogleScholarAuthorAPIClientController {
    private AuthorModel model;
    private GoogleScholarAuthorAPIClientView view;
    
    GoogleScholarAuthorAPIClientController(
        AuthorModel model,
        GoogleScholarAuthorAPIClientView view
    ) {
        this.model = model;
        this.view = view;
        
        this.view.setController(this);
    }
    
    public String queryAuthor(String authorId) {
        try {
            this.model.query(authorId);
        } catch (GoogleScholarAuthorAPIError ex) {
            Logger.getLogger(GoogleScholarAuthorAPIClientController.class.getName()).log(Level.SEVERE, null, ex);
            return "Google Scholar Author API has given an error response!!!";
        } catch (Exception e) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, e);
            return "This query has given an error!!";
        }
        return this.model.toString();
    }
}
