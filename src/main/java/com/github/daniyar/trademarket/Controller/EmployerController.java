package com.github.daniyar.trademarket.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.daniyar.trademarket.Dao.EmployerDAO;
import com.github.daniyar.trademarket.Deserializers.EmployerDeserializer;
import com.github.daniyar.trademarket.POJO.Employer;
import com.github.daniyar.trademarket.Serializers.EmployerSerializer;
import com.github.daniyar.trademarket.UserSerializers.EmployerUserSerializer;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployerController implements CrudHandler {
    private static EmployerDAO employerDAO;
    private Logger logger;

    public EmployerController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try{
            employerDAO = new EmployerDAO();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");
        }
    }

    public EmployerDAO employerDAO() {
        return employerDAO;
    }


    @Override
    public void create(@NotNull Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employer.class, new EmployerDeserializer());
        objectMapper.registerModule(module);
        String json = context.body();
        try {
            Employer employer = objectMapper.readValue(json, Employer.class);
            employerDAO().persist(employer);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.BAD_REQUEST);
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int employerId = Integer.valueOf(s);
        try {
            Employer employer = employerDAO().findById(employerId);
            employerDAO().delete(employer);
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
        module.addSerializer(Employer.class, new EmployerSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(employerDAO().findAll()));
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
        module.addSerializer(Employer.class, new EmployerUserSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(employerDAO().findAll()));
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
        module.addSerializer(Employer.class, new EmployerSerializer());
        mapper.registerModule(module);
        int employerId = Integer.valueOf(s);
        try {
            Employer employer = employerDAO.findById(employerId);
            if (employer != null){
                context.result(mapper.writeValueAsString(employer));
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
        module.addSerializer(Employer.class, new EmployerUserSerializer());
        mapper.registerModule(module);
        int employerId = Integer.valueOf(s);
        try {
            Employer employer = employerDAO.findById(employerId);
            if (employer != null){
                context.result(mapper.writeValueAsString(employer));
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
    public void update(@NotNull Context context, @NotNull String s) {
        int employerId = Integer.valueOf(s);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employer.class, new EmployerDeserializer());
        mapper.registerModule(module);
        String json = context.body();
        try {
            Employer newEmployer = mapper.readValue(json, Employer.class);
            newEmployer.setId(employerId);
            employerDAO().update(newEmployer);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
