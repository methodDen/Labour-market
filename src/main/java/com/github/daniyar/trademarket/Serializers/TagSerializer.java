package com.github.daniyar.trademarket.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Employer;
import com.github.daniyar.trademarket.POJO.Tag;

import java.io.IOException;
import java.util.List;

public class TagSerializer extends StdSerializer<Tag> {


    protected TagSerializer(Class<Tag> t) {
        super(t);
    }

    public TagSerializer() {
        this(null);
    }

    @Override
    public void serialize(Tag tag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("tagId", tag.getId());
        jsonGenerator.writeStringField("tagName", tag.getTagName());

        jsonGenerator.writeArrayFieldStart("employers");
        List<Employer> employers = tag.getEmployers();
        for (Employer employer : employers) {
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
