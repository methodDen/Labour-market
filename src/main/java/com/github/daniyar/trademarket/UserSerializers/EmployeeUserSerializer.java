package com.github.daniyar.trademarket.UserSerializers; // importing packages

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.POJO.Job;

import java.io.IOException;
import java.util.Collection;

public class EmployeeUserSerializer extends StdSerializer<Employee> { // Serializer is a
    // technology used to create GET http requests for objects which have connections with other objects (database connections) or the other situation where code developer does not need to get too many data inside object json
    // this is serializer for insecure data. That implies that during http requests users will get pieces of data without sensitive data


    protected EmployeeUserSerializer(Class<Employee> t) {
        super(t);
    } // parametrized constructor

    public EmployeeUserSerializer() {
        this(null);
    } // empty constructor

    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException { // main method for serialization


        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("firstName", employee.getFirstName()); // displaying object parameter in JSON
        jsonGenerator.writeStringField("lastName", employee.getLastName()); // displaying object parameter in JSON
        jsonGenerator.writeStringField("status", employee.getStatus()); // displaying object parameter in JSON
        jsonGenerator.writeStringField("region", employee.getRegionName()); // displaying object parameter in JSON
        jsonGenerator.writeStringField("profileDescription", employee.getProfileDescription()); // displaying object parameter in JSON
        jsonGenerator.writeStringField("dateOfBirth", employee.getDateOfBirth());   // displaying object parameter in JSON
        jsonGenerator.writeStringField("mobilePhone", employee.getMobilePhone()); // displaying object parameter in JSON
        jsonGenerator.writeStringField("email", employee.getEmail()); // displaying object parameter in JSON

        jsonGenerator.writeArrayFieldStart("jobs"); // creating ArrayFieldStart for jobs -> (Employee and Job tables have one-to-many relationship) -> that's why in Employee class set of Job objects is stored
        Collection<Job> jobs = employee.getJobs(); // getting jobs Collection from Employee object

        for (Job job : jobs) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", job.getId()); // displaying object parameter in JSON
            jsonGenerator.writeStringField("jobName", job.getJobName()); // displaying object parameter in JSON
            jsonGenerator.writeStringField("jobDescription", job.getJobDescription()); // displaying object parameter in JSON
            jsonGenerator.writeStringField("paymentSum", job.getPaymentSum()); // displaying object parameter in JSON
            jsonGenerator.writeStringField("dateOfCompletion", job.getDateOfCompletion()); // displaying object parameter in JSON
            jsonGenerator.writeNumberField("durationInHours", job.getDuration()); // displaying object parameter in JSON
            jsonGenerator.writeStringField("placeOfPerformance", job.getPlaceOfPerformance()); // displaying object parameter in JSON
            jsonGenerator.writeStringField("processStatus", job.getProcessStatus()); // displaying object parameter in JSON
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeEndObject();
    }
}
