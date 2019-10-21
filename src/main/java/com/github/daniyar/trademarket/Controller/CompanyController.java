package com.github.daniyar.trademarket.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.daniyar.trademarket.Dao.CompanyDAO;
import com.github.daniyar.trademarket.Deserializers.CompanyDeserializer;
import com.github.daniyar.trademarket.POJO.Company;
import com.github.daniyar.trademarket.Serializers.CompanySerializer;
import com.github.daniyar.trademarket.UserSerializers.CompanyUserSerializer;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyController implements CrudHandler {
    private static CompanyDAO companyDAO;
    private Logger logger;

    public CompanyController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try{
            companyDAO = new CompanyDAO();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");
        }
    }

    public CompanyDAO companyDAO() {
        return companyDAO;
    }




    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int companyId = Integer.valueOf(s);
        try {
            Company c = companyDAO().findById(companyId);
            companyDAO().delete(c);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured deleting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getAll(@NotNull Context context) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Company.class, new CompanySerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(companyDAO().findAll()));
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    public void getAllForUsers(@NotNull Context context) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Company.class, new CompanyUserSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(companyDAO().findAll()));
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Company.class, new CompanySerializer());
        mapper.registerModule(module);
        int companyId = Integer.valueOf(s);
        try {
            Company c = companyDAO.findById(companyId);
            if (c != null){
                context.result(mapper.writeValueAsString(c));
                context.status(Constants.OK_200);
            } else {
                context.status(Constants.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    public void getOneForUsers(@NotNull Context context, @NotNull String s) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Company.class, new CompanyUserSerializer());
        mapper.registerModule(module);
        int companyId = Integer.valueOf(s);
        try {
            Company c = companyDAO.findById(companyId);
            if (c != null){
                context.result(mapper.writeValueAsString(c));
                context.status(Constants.OK_200);
            } else {
                context.status(Constants.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void create(@NotNull Context context) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Company.class, new CompanyDeserializer());
        mapper.registerModule(module);
        String json = context.body();

        try {
            Company company = mapper.readValue(json, Company.class);
            companyDAO().persist(company);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        int companyId = Integer.valueOf(s);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Company.class, new CompanyDeserializer());
        mapper.registerModule(module);
        String json = context.body();

        try {

            Company newCompany = mapper.readValue(json, Company.class);
            newCompany.setId(companyId);
            companyDAO().update(newCompany);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
