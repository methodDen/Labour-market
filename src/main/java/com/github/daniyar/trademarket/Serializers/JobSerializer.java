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
import java.util.Set;

public class JobSerializer extends StdSerializer<Job> {

    protected JobSerializer(Class<Job> t) {
        super(t);
    }

    public JobSerializer() {
        this(null);
    }

    @Override
    public void serialize(Job job, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("jobId", job.getId());
        jsonGenerator.writeStringField("jobName", job.getJobName());
        jsonGenerator.writeStringField("placeOfPerfomance", job.getPlaceOfPerfomance());
        jsonGenerator.writeStringField("dateOfCompletion", job.getDateOfCompletion());
        jsonGenerator.writeStringField("paymentSum", job.getPaymentSum());
        jsonGenerator.writeStringField("processStatus", job.getProcessStatus());
        jsonGenerator.writeStringField("jobDescription", job.getJobDescription());


        jsonGenerator.writeArrayFieldStart("tags");
        List<Tag> tags = job.getTags();
        for (Tag tag : tags) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", tag.getId());
            jsonGenerator.writeStringField("tagName", tag.getTagName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("employers");
        Collection<Employer> employers = job.getEmployers();
        for (Employer employer : employers){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", employer.getId());
            jsonGenerator.writeStringField("firstName", employer.getFirstName());
            jsonGenerator.writeStringField("lastName", employer.getLastName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeEndObject();
    }
}
