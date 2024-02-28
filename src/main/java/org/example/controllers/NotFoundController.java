package org.example.controllers;


import org.example.annotations.Component;
import org.example.annotations.RequestMapping;
import org.example.requesters.RequestFile;

@Component

public class NotFoundController {

    private static final RequestFile requestFile = RequestFile.getInstance();

    @RequestMapping("/")
    public static String index(){
        requestFile.setPath("/formulario.html");
        requestFile.setType("text/html");
        requestFile.setCode("/200 OK");
        requestFile.setBody();

        return requestFile.getResponse();
    }

    @RequestMapping("/script.js")
    public static String colorJS(){
        requestFile.setPath("/script.js");
        requestFile.setType("text/js");
        requestFile.setCode("/200 OK");
        requestFile.setBody();

        return requestFile.getResponse();
    }

    @RequestMapping("/style.css")
    public static String indexCSS(){
        requestFile.setPath("/style.css");
        requestFile.setType("text/css");
        requestFile.setCode("/200 OK");
        requestFile.setBody();

        return requestFile.getResponse();
    }


    @RequestMapping("/hello")
    public static String hello(){
        return "HTTP/1.1 200 \r\n" +
                "Content-Type:text/html\r\n" +
                "\r\n"+
                "Greetings from Spring Boot!";
    }


}
