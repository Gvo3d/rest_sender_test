package org.yakimovdenis.demo;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yakimovdenis.demo.model.IntObject;
import org.yakimovdenis.demo.model.RequestTask;
import org.yakimovdenis.demo.model.StringObject;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private final String SEPARATOR = "**************************************************";

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/empty_get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getResponse(HttpRequest request){
        System.out.println(SEPARATOR);
        System.out.println("get invoked");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getResponse(){
        String data = "response_text";
        System.out.println(SEPARATOR);
        System.out.println("get with response envoked");
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/empty_post", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void printText(@RequestBody Object object){
        System.out.println(SEPARATOR);
        System.out.println("post envoked");
        printObject(object);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity printText2(@RequestBody Object object){
        Entity data = new Entity("response_text");
        System.out.println(SEPARATOR);
        System.out.println("post with response envoked");
        printObject(object);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/single", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void test1(@RequestBody RequestTask object){
        System.out.println("This is object: "+object+" of class: "+object.getClass());
    }

    private void printObject(Object object){
        if (object instanceof String){
            System.out.println("STRING: "+(String) object);
        } else if (object instanceof Entity) {
            System.out.println("ENTITY: "+(Entity) object);
        } else {
            System.out.println("Unknown entityType: "+object.toString());
        }
    }
}
