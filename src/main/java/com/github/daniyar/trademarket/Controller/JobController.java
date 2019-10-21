package com.github.daniyar.trademarket.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.daniyar.trademarket.Dao.JobDAO;
import com.github.daniyar.trademarket.Deserializers.JobDeserializer;
import com.github.daniyar.trademarket.POJO.Job;
import com.github.daniyar.trademarket.Serializers.JobSerializer;
import com.github.daniyar.trademarket.UserSerializers.JobUserSerializer;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobController implements CrudHandler { // everything works,
    // but when I try to assign other employerId to one certain job, it doesn't work ->
    // should I create joinTable for Job and Employer??
    private static JobDAO jobDAO;
    private Logger logger;

    public JobController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try{
            jobDAO = new JobDAO();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");
        }
    }

    public JobDAO jobDAO() {
        return jobDAO;
    }


    @Override
    public void create(@NotNull Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Job.class, new JobDeserializer());
        objectMapper.registerModule(module);
        String json = context.body();
        try {
            Job job = objectMapper.readValue(json, Job.class);
            jobDAO.persist(job);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.BAD_REQUEST);
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int jobId = Integer.valueOf(s);
        try {
            Job job = jobDAO().findById(jobId);
            jobDAO().delete(job);
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
        module.addSerializer(Job.class, new JobSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(jobDAO().findAll()));
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
        module.addSerializer(Job.class, new JobUserSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(jobDAO().findAll()));
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
        module.addSerializer(Job.class, new JobSerializer());
        mapper.registerModule(module);
        int jobId = Integer.valueOf(s);
        try {
            Job job = jobDAO.findById(jobId);
            if (job != null){
                context.result(mapper.writeValueAsString(job));
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
        module.addSerializer(Job.class, new JobUserSerializer());
        mapper.registerModule(module);
        int jobId = Integer.valueOf(s);
        try {
            Job job = jobDAO.findById(jobId);
            if (job != null){
                context.result(mapper.writeValueAsString(job));
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
        int jobId = Integer.valueOf(s);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Job.class, new JobDeserializer());
        mapper.registerModule(module);
        String json = context.body();
        try {
            Job newJob = mapper.readValue(json, Job.class);
            newJob.setId(jobId);
            jobDAO().update(newJob);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
