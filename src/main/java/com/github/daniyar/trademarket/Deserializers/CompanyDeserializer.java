package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.Dao.EmployerDAO;
import com.github.daniyar.trademarket.POJO.Company;
import com.github.daniyar.trademarket.POJO.Employer;

import java.io.IOException;
import java.util.*;

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
        Set<Employer> employers = new HashSet<>(); // List of employers is retrieved from database and stored here
        for (Iterator<JsonNode> it = employerNode.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            int id = element.asInt();
            Employer newEmployer = new EmployerDAO().findById(id);
            employers.add(newEmployer);
        }
        return new Company(0, companyName, employers);
    }
}
