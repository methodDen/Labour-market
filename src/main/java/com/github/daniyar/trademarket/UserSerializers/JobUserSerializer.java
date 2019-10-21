package com.github.daniyar.trademarket.UserSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Job;

import java.io.IOException;

public class JobUserSerializer extends StdSerializer<Job> {


    protected JobUserSerializer(Class<Job> t) {
        super(t);
    }

    @Override
    public void serialize(Job job, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
