package com.myke.googlescholarauthorapiclient;

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
