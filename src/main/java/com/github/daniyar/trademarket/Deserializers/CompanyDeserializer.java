package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.POJO.Company;
import com.github.daniyar.trademarket.POJO.Employer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompanyDeserializer extends StdDeserializer<Company> {


    protected CompanyDeserializer(Class<?> vc) {
        super(vc);
    }

    public CompanyDeserializer() {
        this(null);
    }

    @Override
    public Company deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String companyName = jsonNode.get("companyName").asText();
        ArrayNode employerNode = (ArrayNode) jsonNode.get("employers");

        List<Employer> employers = new ArrayList<>(); // make somehow something same in EmployerSerializer -> One to many and Many to one mapping
        for (Iterator<JsonNode> it = employerNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Employer e = new Employer(0, element.get("firstName").asText(), element.get("lastName").asText());
            employers.add(e);
        }
        return new Company(0, companyName, employers);
    }
}
