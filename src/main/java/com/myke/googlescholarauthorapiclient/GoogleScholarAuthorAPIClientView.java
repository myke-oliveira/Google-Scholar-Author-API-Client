/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myke.googlescholarauthorapiclient;

import java.util.Scanner;

/**
 *
 * @author myke
 */
public class GoogleScholarAuthorAPIClientView {
    private GoogleScholarAuthorAPIClientController controller;

    public void setController(GoogleScholarAuthorAPIClientController controller) {
        this.controller = controller;
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            this.prompt();
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) break;

            String authorId = input;
            String output = this.controller.queryAuthor(authorId);
            System.out.println(output);
        }
    }
    
    private void prompt() {
        System.out.print("Author id (or empty to quit): ");
    }
}
