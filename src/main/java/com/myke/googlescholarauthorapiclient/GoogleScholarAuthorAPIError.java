package com.myke.googlescholarauthorapiclient;

public class GoogleScholarAuthorAPIError extends Exception {
    private final String message;
    
    GoogleScholarAuthorAPIError(Throwable e) {
        this.message = String.format(
            "Error on requesting data from Google Scholar Author API:\n%s",
            e.getMessage()
        );
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
