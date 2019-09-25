package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.Dao.EmployerDAO;
import com.github.daniyar.trademarket.POJO.Employer;
import com.github.daniyar.trademarket.POJO.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagDeserializer extends StdDeserializer<Tag> {


    protected TagDeserializer(Class<?> vc) {
        super(vc);
    }

    public TagDeserializer() {
        this(null);
    }

    @Override
    public Tag deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String tagName = jsonNode.get("tagName").asText(); // retrieving from REST and storing to database
        ArrayNode employerNode = (ArrayNode) jsonNode.get("employers");

        List<Employer> employers = new EmployerDAO().findAll(); // employers got from database
//        int employerId = // required employer got from arraylist by finding his id

        for (Iterator<JsonNode> it = employerNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
//            Employer e
            Employer employer = new Employer(0, element.get("firstName").asText(), element.get("lastName").asText());
            employers.add(employer);
        }

        return new Tag(0, tagName, employers);
    }
}
