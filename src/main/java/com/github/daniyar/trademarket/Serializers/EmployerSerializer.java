package com.github.daniyar.trademarket.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Employer;
import com.github.daniyar.trademarket.POJO.Job;
import com.github.daniyar.trademarket.POJO.Tag;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class EmployerSerializer extends StdSerializer<Employer> {
    public EmployerSerializer(Class<Employer> t) {
        super(t);
    }

    public EmployerSerializer() {
        this(null);
    }

    @Override
    public void serialize(Employer employer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("employerId", employer.getId());
        jsonGenerator.writeStringField("firstName", employer.getFirstName());
        jsonGenerator.writeStringField("lastName", employer.getLastName());
        jsonGenerator.writeStringField("region", employer.getRegion());
        jsonGenerator.writeStringField("email", employer.getEmail());
        jsonGenerator.writeStringField("password", employer.getPassword());
        jsonGenerator.writeStringField("profileDescription", employer.getProfileDescription());
        jsonGenerator.writeNumberField("paypalPurse", employer.getCreditCardId());
        jsonGenerator.writeStringField("employerRole", employer.getEmployerRole());
        jsonGenerator.writeStringField("phoneNumber", employer.getPhoneNumber());

        jsonGenerator.writeArrayFieldStart("jobs");
        Collection<Job> jobs = employer.getJobs();
        for (Job job : jobs) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", job.getId());
            jsonGenerator.writeStringField("jobName", job.getJobName());
            jsonGenerator.writeStringField("placeOfPerfomance", job.getPlaceOfPerfomance());
            jsonGenerator.writeStringField("dateOfCompletion", job.getDateOfCompletion());
            jsonGenerator.writeStringField("paymentSum", job.getPaymentSum());
            jsonGenerator.writeStringField("processStatus", job.getProcessStatus());
            jsonGenerator.writeStringField("jobDescription", job.getJobDescription());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("tags");
        List<Tag> tags = employer.getTags();
        for (Tag tag : tags) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", tag.getId());
            jsonGenerator.writeStringField("tagName", tag.getTagName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
