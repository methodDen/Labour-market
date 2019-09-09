package com.github.daniyar.trademarket.Controller;

import com.github.daniyar.trademarket.Dao.JobDAO;
import com.github.daniyar.trademarket.POJO.Job;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobController implements CrudHandler {
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
        Job job = context.bodyAsClass(Job.class);
        try {
            jobDAO.persist(job);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
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
        try {
            context.json(jobDAO().findAll());
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int jobId = Integer.valueOf(s);
        try {
            Job job = jobDAO.findById(jobId);
            if (job != null){
                context.json(job);
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
        Job newJob = context.bodyAsClass(Job.class);
        newJob.setId(jobId);
        try {
            jobDAO().update(newJob);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
