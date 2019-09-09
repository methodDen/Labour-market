package com.github.daniyar.trademarket.Controller;

import com.github.daniyar.trademarket.Dao.TagDAO;
import com.github.daniyar.trademarket.POJO.Tag;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagController implements CrudHandler {
    private static TagDAO tagDAO;
    private Logger logger;

    public TagController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try{
            tagDAO = new TagDAO();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");
        }
    }

    public TagDAO tagDAO() {
        return tagDAO;
    }


    @Override
    public void create(@NotNull Context context) {
        Tag tag = context.bodyAsClass(Tag.class);
        try {
            tagDAO.persist(tag);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int tagId = Integer.valueOf(s);
        try {
            Tag tag = tagDAO().findById(tagId);
            tagDAO().delete(tag);
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
            context.json(tagDAO().findAll());
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int tagId = Integer.valueOf(s);
        try {
            Tag tag = tagDAO.findById(tagId);
            if (tag != null){
                context.json(tag);
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
        int tagId = Integer.valueOf(s);
        Tag newTag = context.bodyAsClass(Tag.class);
        newTag.setId(tagId);
        try {
            tagDAO().update(newTag);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
