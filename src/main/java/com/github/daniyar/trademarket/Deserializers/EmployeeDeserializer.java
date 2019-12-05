package com.github.daniyar.trademarket.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.daniyar.trademarket.Dao.JobDAO;
import com.github.daniyar.trademarket.Dao.RatingDAO;
import com.github.daniyar.trademarket.Dao.TagDAO;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.POJO.Job;
import com.github.daniyar.trademarket.POJO.Rating;
import com.github.daniyar.trademarket.POJO.Tag;
import com.github.daniyar.trademarket.Utils.Role;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.*;

public class EmployeeDeserializer extends StdDeserializer<Employee> { // Deserializer is a
    // technology used to create POST and PATCH http requests for objects which have connections with other objects (database connections) or the other situation where code developer does not need to write too many data inside object json
    protected EmployeeDeserializer(Class<?> vc) {
        super(vc);
    } // parametrized constructor

    public EmployeeDeserializer() {
        this(null);
    } // empty constructor

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException { // main deserialize method
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser); // new node for new JSON object
        String firstName = jsonNode.get("firstName").asText(); // field to write in the firstName entity attribute
        String lastName = jsonNode.get("lastName").asText(); // field to write in the lastName entity attribute
        String status = jsonNode.get("status").asText(); // field to write in the status entity attribute
        String region = jsonNode.get("region").asText(); // field to write in the region entity attribute
        String profileDescription = jsonNode.get("profileDescription").asText(); // field to write in the profileDescription entity attribute
        String dateOfBirth = jsonNode.get("dateOfBirth").asText(); // field to write in the dateOfBirth entity attribute
        String mobilePhone = jsonNode.get("mobilePhone").asText(); // field to write in the mobilePhone entity attribute
        String email = jsonNode.get("email").asText(); // field to write in the email entity attribute
        String password = jsonNode.get("password").asText(); // field to write in the password entity attribute
        long creditCardId = jsonNode.get("paypalPurse").asLong(); // field to write in the paypalPurse entity attribute
        String employeeUserRole = jsonNode.get("employeeUserRole").asText(); // field to write in the employeeUserRole entity attribute


        ArrayNode jobNode = (ArrayNode) jsonNode.get("jobs"); // creating new Array node for jobs object (Employee and Job tables have one-to-many relationship) -> that's why in Employee class set of Job objects is stored
        Set<Job> jobs = new HashSet<>(); // creating and initializing jobs HashSet
        for (Iterator<JsonNode> it = jobNode.elements(); it.hasNext(); ) { // looping through jobNode
            JsonNode element = it.next(); // assigning element object to next object in loop
            int id = element.asInt(); // converting element to integer and assigning it to id
            Job newJob = new JobDAO().findById(id); // creating new Job Object and assigning to it database record data by using DAO method
            jobs.add(newJob); // adding new Job Object to jobNode
        }

        ArrayNode tagNode = (ArrayNode) jsonNode.get("tags"); // creating new Array node for jobs object (Employee and Tag tables have many-to-many relationship) -> that's why in Employee class arraylist of Tag objects is stored
        List<Tag> tags = new ArrayList<>(); // creating and initializing tags ArrayList
        for (Iterator<JsonNode> it = tagNode.elements(); it.hasNext(); ) { // looping through tagNode
            JsonNode element = it.next(); // assigning element object to next object in loop
            int id = element.asInt(); // converting element to integer and assigning it to id
            Tag t = new TagDAO().findById(id); // creating new Tag Object and assigning to it database record data by using DAO method
            tags.add(t); // adding new Tag Object to jobNode
        }

        int ratingId = jsonNode.get("ratingId").asInt(); // field to write in the ratingId -> Employee and Rating objects have one-to-one relationship in database
        Rating rating = new RatingDAO().findById(ratingId); // creating and assigning data from database by using DAO method to new Rating object

        return new Employee(0, firstName, lastName, status,
                region, profileDescription, dateOfBirth, mobilePhone,
                email, BCrypt.hashpw(password, BCrypt.gensalt()), creditCardId,
                Role.valueOf(employeeUserRole), jobs, tags, rating); // returning new Employee object
    }
}
