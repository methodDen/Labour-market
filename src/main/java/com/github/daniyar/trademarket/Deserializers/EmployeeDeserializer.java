package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.POJO.Job;
import com.github.daniyar.trademarket.POJO.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeDeserializer extends StdDeserializer<Employee> {

    protected EmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    public EmployeeDeserializer() {
        this(null);
    }

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String firstName = jsonNode.get("firstName").asText();
        String lastName = jsonNode.get("lastName").asText();
        String region = jsonNode.get("region").asText();
        String mobilePhone = jsonNode.get("mobilePhone").asText();
        String profileDescription = jsonNode.get("profileDescription").asText();
        String password = jsonNode.get("password").asText();
        String email = jsonNode.get("email").asText();
        String extraEmail = jsonNode.get("extraEmail").asText();
        long creditCardId = jsonNode.get("paypalPurse").asLong();
        ArrayNode jobNode = (ArrayNode) jsonNode.get("jobs");

        List<Job> jobs = new ArrayList<>();
        for (Iterator<JsonNode> it = jobNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Job j = new Job(0, element.get("jobName").asText());
            jobs.add(j);
        }

        ArrayNode tagNode = (ArrayNode) jsonNode.get("tags");
        List<Tag> tags = new ArrayList<Tag>();
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Tag t = new Tag(0, element.get("tagName").asText());
            tags.add(t);
        }

        return new Employee(0, firstName, lastName, region, mobilePhone, profileDescription, password, email, extraEmail, creditCardId, jobs, tags, null);
    }
}
