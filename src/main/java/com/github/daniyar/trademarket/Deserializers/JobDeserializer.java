package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.POJO.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        String placeOfPerfomance = jsonNode.get("placeOfPerfomance").asText();
        String dateOfCompletion = jsonNode.get("dateOfCompletion").asText();
        String paymentSum = jsonNode.get("paymentSum").asText();
        String processInfo = jsonNode.get("processInfo").asText();
        String jobDescription = jsonNode.get("jobDescription").asText();
        ArrayNode tagNode = (ArrayNode) jsonNode.get("tags");
        List<Tag> tags = new ArrayList<>();
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Tag tag = new Tag(0, element.get("tagName").asText());
            tags.add(tag);
        }

        ArrayNode employerNode = (ArrayNode) jsonNode.get("employers");
        List<Employer> employers = new ArrayList<>();
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Employer employer = new Employer(0, element.get("firstName").asText(), element.get("lastName").asText());
            employers.add(employer);
        }

        int ratingId = jsonNode.get("ratingId").asInt();

        return new Job(0, jobName, placeOfPerfomance,
                dateOfCompletion, paymentSum, processInfo,
                jobDescription, tags, employers,
                new Rating(ratingId, 0, 0, null, null, null, null, null));
    }
}
