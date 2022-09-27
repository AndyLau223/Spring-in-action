package com.example.consumingservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @JsonIgnoreProperties indicates that any properties not bound in this type should be ignored.
 * <p>
 * If your data is not exactly match the JSON document, you can use @JsonPerperty annotation to
 * specify the exact key of the JSON document that's to build mapping between your custom data and JSON document.
 * <p>
 * for example: maps JSON key "type_json" to "type" field.
 * @JsonProperty("type_json")
 * private String type;
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;

    public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
