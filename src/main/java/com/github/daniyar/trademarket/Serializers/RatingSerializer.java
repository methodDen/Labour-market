package com.github.daniyar.trademarket.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Rating;

import java.io.IOException;

public class RatingSerializer extends StdSerializer<Rating> {


    protected RatingSerializer(Class<Rating> t) {
        super(t);
    }

    public RatingSerializer() {
        this(null);
    }

    @Override
    public void serialize(Rating rating, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("ratingId", rating.getRatingId());
        jsonGenerator.writeNumberField("ratingOfEmployee", rating.getRatingOfEmployee());
        jsonGenerator.writeNumberField("ratingOfEmployer", rating.getRatingOfEmployer());
        jsonGenerator.writeStringField("commentOfEmployee", rating.getCommentOfEmployee());
        jsonGenerator.writeStringField("commentOfEmployer", rating.getCommentOfEmployer());
        jsonGenerator.writeEndObject();

    }
}
