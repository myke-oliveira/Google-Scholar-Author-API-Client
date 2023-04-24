package com.myke.googlescholarauthorapiclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class GoogleScholarAuthorAPIClientController {
    private final AuthorModel model;
    private final GoogleScholarAuthorAPIClientView view;
    
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
        } catch (AppError ex) {
            return "An Error might have been logged at \"\\temp\\log\\\"";
        }
        return this.model.toString();
    }
}
