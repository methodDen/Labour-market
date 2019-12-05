package com.github.daniyar.trademarket.UserSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Company;
import com.github.daniyar.trademarket.POJO.Employer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CompanyUserSerializer extends StdSerializer<Company> {


    protected CompanyUserSerializer(Class<Company> t) {
        super(t);
    }

    public CompanyUserSerializer() {
        this(null);
    }

    @Override
    public void serialize(Company company, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("companyName", company.getCompanyName());
        jsonGenerator.writeArrayFieldStart("employers");
        Collection<Employer> employers = company.getEmployers();
        for (Employer employer : employers){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("firstName", employer.getFirstName());
            jsonGenerator.writeStringField("lastName", employer.getLastName());
            jsonGenerator.writeStringField("region", employer.getRegion());
            jsonGenerator.writeStringField("dateOfBirth", employer.getDateOfBirth());
            jsonGenerator.writeStringField("email", employer.getEmail());       // sensitive
            jsonGenerator.writeStringField("phoneNumber", employer.getPhoneNumber());
            jsonGenerator.writeStringField("profileDescription", employer.getProfileDescription());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();

    }
}
