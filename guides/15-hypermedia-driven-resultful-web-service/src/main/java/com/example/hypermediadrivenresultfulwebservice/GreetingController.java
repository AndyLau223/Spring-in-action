package com.example.hypermediadrivenresultfulwebservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GreetingController {

    private static final String TEMPLATE ="Hello, %s!";


    /**
     * linkTo() and methodOn() are static methods on `ControllerBuilder` that let
     * you fake a method invocation on the controller. The returned `LinkBuilder`
     * will have inspected the controller method's annotation to build up exactly
     * the URI to which the method is mapped.
     *
     * withSelfRel() creates a `Link` instance that you add to the `Greeting` representation model.
     * @param name
     * @return
     */
    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", defaultValue = "World", required = false)
            String name
    ){
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));

        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
