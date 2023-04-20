package com.myke.googlescholarauthorapiclient;

import java.util.Scanner;
import org.json.JSONObject;

// AuthorId: EicYvbwAAAAJ
public class GoogleScholarAuthorAPIClient {

    public static void main(String[] args) {
        GoogleScholarAuthorAPI api = new GoogleScholarAuthorAPI();
        AuthorModel model = new AuthorModel(api);
        GoogleScholarAuthorAPIClientView view = new GoogleScholarAuthorAPIClientView();
        GoogleScholarAuthorAPIClientController controller = new GoogleScholarAuthorAPIClientController(model, view);
        view.run();
    }
}
