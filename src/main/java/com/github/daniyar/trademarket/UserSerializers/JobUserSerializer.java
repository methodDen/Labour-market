package com.github.daniyar.trademarket.UserSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Employer;
import com.github.daniyar.trademarket.POJO.Job;

import java.io.IOException;
import java.util.Collection;

public class JobUserSerializer extends StdSerializer<Job> { // Everything works


    protected JobUserSerializer(Class<Job> t) {
        super(t);
    }

    public JobUserSerializer() {
        this(null);
    }

    @Override
    public void serialize(Job job, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("jobName", job.getJobName());
        jsonGenerator.writeStringField("jobDescription", job.getJobDescription());
        jsonGenerator.writeStringField("paymentSum", job.getPaymentSum());
        jsonGenerator.writeStringField("requirements", job.getRequirements());
        jsonGenerator.writeStringField("responsibilities", job.getResponsibilities());
        jsonGenerator.writeStringField("workingTerms", job.getWorkingTerms());
        jsonGenerator.writeStringField("dateOfCompletion", job.getDateOfCompletion());
        jsonGenerator.writeNumberField("durationInHours", job.getDuration());
        jsonGenerator.writeStringField("placeOfPerformance", job.getPlaceOfPerformance());
        jsonGenerator.writeStringField("processStatus", job.getProcessStatus());

//
//        jsonGenerator.writeArrayFieldStart("tags");
//        List<Tag> tags = job.getTags();
//        for (Tag tag : tags) {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", tag.getId());
//            jsonGenerator.writeStringField("tagName", tag.getTagName());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("employers");
        Collection<Employer> employers = job.getEmployers();
        for (Employer employer : employers){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("firstName", employer.getFirstName());
            jsonGenerator.writeStringField("lastName", employer.getLastName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        // rating

        jsonGenerator.writeEndObject();
    }
}
