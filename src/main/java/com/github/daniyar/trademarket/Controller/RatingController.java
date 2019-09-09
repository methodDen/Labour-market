package com.github.daniyar.trademarket.Controller;

import com.github.daniyar.trademarket.Dao.RatingDAO;
import com.github.daniyar.trademarket.POJO.Rating;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RatingController implements CrudHandler {
    private static RatingDAO ratingDAO;
    private Logger logger;

    public RatingController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try{
            ratingDAO = new RatingDAO();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");
        }
    }

    public RatingDAO ratingDAO() {
        return ratingDAO;
    }


    @Override
    public void create(@NotNull Context context) {
        Rating rating = context.bodyAsClass(Rating.class);
        try {
            ratingDAO.persist(rating);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int ratingId = Integer.valueOf(s);
        try {
            Rating rating = ratingDAO().findById(ratingId);
            ratingDAO().delete(rating);
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
            context.json(ratingDAO().findAll());
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int ratingId = Integer.valueOf(s);
        try {
            Rating rating = ratingDAO.findById(ratingId);
            if (rating != null){
                context.json(rating);
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
        int ratingId = Integer.valueOf(s);
        Rating newRating = context.bodyAsClass(Rating.class);
        newRating.setRatingId(ratingId);
        try {
            ratingDAO().update(newRating);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
