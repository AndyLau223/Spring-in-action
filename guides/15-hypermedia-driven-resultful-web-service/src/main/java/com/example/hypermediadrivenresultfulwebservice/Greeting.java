package com.example.hypermediadrivenresultfulwebservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class Greeting extends RepresentationModel<Greeting> {
    private final String content;

    /**
     * @JsonCreator: Signals how Jackson can create an instance of this POJO
     * @JsonProperty: Marks the field into which Jackson should put this constructor argument
     * @param content
     */
    @JsonCreator
    public Greeting(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent(){
        return content;
    }


}
