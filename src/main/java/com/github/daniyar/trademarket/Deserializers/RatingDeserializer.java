package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.daniyar.trademarket.Dao.EmployeeDAO;
import com.github.daniyar.trademarket.Dao.EmployerDAO;
import com.github.daniyar.trademarket.Dao.JobDAO;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.POJO.Employer;
import com.github.daniyar.trademarket.POJO.Job;
import com.github.daniyar.trademarket.POJO.Rating;

import java.io.IOException;

public class RatingDeserializer extends StdDeserializer<Rating> {


    protected RatingDeserializer(Class vc) {
        super(vc);
    }

    public RatingDeserializer() {
        this(null);
    }

    @Override
    public Rating deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        int ratingOfEmployee = jsonNode.get("ratingOfEmployee").asInt();
        int ratingOfEmployer = jsonNode.get("ratingOfEmployer").asInt();
        String commentOfEmployee = jsonNode.get("commentOfEmployee").asText();
        String commentOfEmployer = jsonNode.get("commentOfEmployer").asText();
        int jobId = jsonNode.get("jobId").asInt();
        int employeeId = jsonNode.get("employeeId").asInt();
        int employerId = jsonNode.get("employerId").asInt();

        Job job = new JobDAO().findById(jobId);
        Employee employee = new EmployeeDAO().findById(employeeId);
        Employer employer = new EmployerDAO().findById(employerId);

        return new Rating(0, ratingOfEmployee, ratingOfEmployer, commentOfEmployee, commentOfEmployer, job, employee, employer);  // it's to weird. Maybe it would be better to create smaller constructors // transient instance -> related
        // to new Employer and new Job, to solve it I just need to get those objects with DAO findById, new object equals to that got by DAO
    }
}
