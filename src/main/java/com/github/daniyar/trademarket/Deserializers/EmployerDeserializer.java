package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.POJO.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployerDeserializer extends StdDeserializer<Employer> {

    protected EmployerDeserializer(Class<?> vc) {
        super(vc);
    }

    public EmployerDeserializer() {
        this(null);
    }

    @Override
    public Employer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
//        int id = Integer.valueOf(jsonNode.get("employerId").asText());
        String firstName = jsonNode.get("firstName").asText();
        String lastName = jsonNode.get("lastName").asText();
        String region = jsonNode.get("region").asText();
        String email = jsonNode.get("email").asText();
        String extraEmail = jsonNode.get("extraEmail").asText();
        String password = jsonNode.get("password").asText();
        String profileDescription = jsonNode.get("profileDescription").asText();
        long creditCardId = jsonNode.get("paypalPurse").asLong();
        String employerRole =  jsonNode.get("employerRole").asText();
        String phoneNumber = jsonNode.get("phoneNumber").asText();
        ArrayNode tagNode = (ArrayNode) jsonNode.get("tags");
        List<Tag> tags = new ArrayList<Tag>();
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Tag t = new Tag(0, element.get("tagName").asText());
            tags.add(t);
        }

//        int ratingId = jsonNode.get("ratingId").asInt();
//        int companyId = jsonNode.get("companyId").asInt();
//        Create Company and Rating entities; find by Id using DAO; get rating Id and companyId; store them in deserializer
        return new Employer(0, firstName, lastName, region, email, extraEmail,
                        password, profileDescription,
                        creditCardId, employerRole, phoneNumber, tags, null, null);

//        Employer employer = new Employer();
//        employer.setTags(tags);
//        employer.setEmail(email);
//        employer.setFirstName(firstName);
//        return employer;
    }


}
