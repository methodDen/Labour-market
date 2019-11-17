package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.Dao.CompanyDAO;
import com.github.daniyar.trademarket.Dao.JobDAO;
import com.github.daniyar.trademarket.Dao.RatingDAO;
import com.github.daniyar.trademarket.Dao.TagDAO;
import com.github.daniyar.trademarket.POJO.*;
import com.github.daniyar.trademarket.Utils.Role;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.*;

public class EmployerDeserializer extends StdDeserializer<Employer> { // tested

    protected EmployerDeserializer(Class<?> vc) {
        super(vc);
    }

    public EmployerDeserializer() {
        this(null);
    }

    @Override
    public Employer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String firstName = jsonNode.get("firstName").asText();
        String lastName = jsonNode.get("lastName").asText();
        String region = jsonNode.get("region").asText();
        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();
        String profileDescription = jsonNode.get("profileDescription").asText();
        long paypalPurse = jsonNode.get("paypalPurse").asLong();
        String dateOfBirth = jsonNode.get("dateOfBirth").asText();

        String employerRole =  jsonNode.get("employerRole").asText();
        String employerUserRole = jsonNode.get("employerUserRole").asText();

        String phoneNumber = jsonNode.get("phoneNumber").asText();

        int ratingId = jsonNode.get("ratingId").asInt();
        Rating rating = new RatingDAO().findById(ratingId);

        ArrayNode jobNode = (ArrayNode) jsonNode.get("jobs"); // testing
        Set<Job> jobs = new HashSet<>();
        for (Iterator<JsonNode> it = jobNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            int id = element.asInt();
            Job j = new JobDAO().findById(id);
            jobs.add(j);
        }

         int companyId = jsonNode.get("companyId").asInt();  // tested
         Company company = new CompanyDAO().findById(companyId);


        ArrayNode tagNode = (ArrayNode) jsonNode.get("tags");
        List<Tag> tags = new ArrayList<>();
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            int id = element.asInt();
            Tag t = new TagDAO().findById(id);
            tags.add(t);
        }




        return new Employer(0, firstName, lastName, region, dateOfBirth, email,
                BCrypt.hashpw(password, BCrypt.gensalt()), phoneNumber,
                paypalPurse, profileDescription, employerRole, Role.valueOf(employerUserRole),
                jobs , tags, company, rating);

    }


}
