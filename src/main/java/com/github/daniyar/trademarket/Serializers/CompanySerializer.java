package com.github.daniyar.trademarket.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.daniyar.trademarket.POJO.Company;
import com.github.daniyar.trademarket.POJO.Employer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CompanySerializer extends StdSerializer<Company> {


    protected CompanySerializer(Class<Company> t) {
        super(t);
    }

    public CompanySerializer() {
        this(null);
    }

    @Override
    public void serialize(Company company, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("companyId", company.getId());
        jsonGenerator.writeStringField("companyName", company.getCompanyName());

        jsonGenerator.writeArrayFieldStart("employers");
        Collection<Employer> employers = company.getEmployers();
        for (Employer employer : employers){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", employer.getId());
            jsonGenerator.writeStringField("firstName", employer.getFirstName());
            jsonGenerator.writeStringField("lastName", employer.getLastName());
            jsonGenerator.writeStringField("region", employer.getRegion());
            jsonGenerator.writeStringField("dateOfBirth", employer.getDateOfBirth());
            jsonGenerator.writeStringField("email", employer.getEmail());       // sensitive
            jsonGenerator.writeStringField("phoneNumber", employer.getPhoneNumber());
            jsonGenerator.writeStringField("profileDescription", employer.getProfileDescription());
            jsonGenerator.writeStringField("employerUserRole", String.valueOf(employer.getRole())); // sensitive
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeEndObject();
    }
}
