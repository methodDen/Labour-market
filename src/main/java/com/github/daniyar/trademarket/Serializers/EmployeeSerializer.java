package com.github.daniyar.trademarket.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.POJO.Job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSerializer extends StdSerializer<Employee> {


    protected EmployeeSerializer(Class<Employee> t) {
        super(t);
    }

    public EmployeeSerializer() {
        this(null);
    }

    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("employeeId", employee.getId());
        jsonGenerator.writeStringField("firstName", employee.getFirstName());
        jsonGenerator.writeStringField("lastName", employee.getLastName());
        jsonGenerator.writeStringField("region", employee.getRegionName());
        jsonGenerator.writeStringField("mobilePhone", employee.getMobilePhone());
        jsonGenerator.writeStringField("profileDescription", employee.getProfileDescription());
        jsonGenerator.writeStringField("email", employee.getEmail());
        jsonGenerator.writeStringField("extraEmail", employee.getExtraEmail());
        jsonGenerator.writeNumberField("paypalPurse", employee.getCreditCardId());
        jsonGenerator.writeArrayFieldStart("jobs");
        List<Job> jobs = employee.getJobs();
        for (Job job : jobs) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", job.getId());
            jsonGenerator.writeStringField("jobName", job.getJobName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
