package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.Dao.*;
import com.github.daniyar.trademarket.POJO.*;

import java.io.IOException;
import java.util.*;

public class JobDeserializer extends StdDeserializer<Job> {


    protected JobDeserializer(Class<?> vc) {
        super(vc);
    }

    public JobDeserializer() {
        this(null);
    }

    @Override
    public Job deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String jobName = jsonNode.get("jobName").asText();
        String jobDescription = jsonNode.get("jobDescription").asText();
        String paymentSum = jsonNode.get("paymentSum").asText();
        String requirements = jsonNode.get("requirements").asText();
        String responsibilities = jsonNode.get("resps").asText();
        String terms = jsonNode.get("workingTerms").asText();
        String dateOfCompletion = jsonNode.get("dateOfCompletion").asText();
        int duration = jsonNode.get("durationInHours").asInt();
        String placeOfPerformance = jsonNode.get("placeOfPerformance").asText();
        String processInfo = jsonNode.get("processStatus").asText();

        ArrayNode tagNode = (ArrayNode) jsonNode.get("tags");
        List<Tag> tags = new ArrayList<>();
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            int id = element.asInt();
            Tag t = new TagDAO().findById(id);
            tags.add(t);
        }

        ArrayNode employerNode = (ArrayNode) jsonNode.get("employers"); // testing
        Set<Employer> employers = new HashSet<>();
        for (Iterator<JsonNode> it = employerNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            int id = element.asInt();
            Employer employer = new EmployerDAO().findById(id);
            employers.add(employer);
        }

        int ratingId = jsonNode.get("rating").asInt();
        Rating rating = new RatingDAO().findById(ratingId);

        return new Job(0, jobName, jobDescription, paymentSum,
                requirements, responsibilities, terms,
                dateOfCompletion, duration, placeOfPerformance,
                processInfo, tags, employers,rating);
    }
}
