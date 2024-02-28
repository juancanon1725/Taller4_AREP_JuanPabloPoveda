package org.example.controllers;

import org.example.requesters.RequestImage;
import org.example.annotations.Component;
import org.example.annotations.RequestMapping;

import java.io.IOException;

@Component

public class ImageController {

    private static final RequestImage requestImage = RequestImage.getInstance();


    @RequestMapping("/Saturno")
    public static String ok(){
        requestImage.setPath("/Saturno.png");
        requestImage.setType("image/png");
        requestImage.setCode("/200 OK");
        try {
            requestImage.setBody();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestImage.getResponse();
    }

}
