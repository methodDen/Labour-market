package Controller;

import Dao.TagDAO;
import POJO.Tag;
import Utils.Constants;
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
            tagDAO.openCurrentSessionwithTransaction();
            tagDAO.persist(tag);
            tagDAO.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int tagId = Integer.valueOf(s);
        try {
            tagDAO.openCurrentSessionwithTransaction();
            Tag tag = tagDAO().findById(tagId);
            tagDAO().delete(tag);
            tagDAO().closeCurrentSessionwithTransaction();
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
            tagDAO().openCurrentSession();
            context.json(tagDAO().findAll());
            tagDAO().closeCurrentSession();
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
            tagDAO.openCurrentSession();
            Tag tag = tagDAO.findById(tagId);
            if (tag != null){
                context.json(tag);
            } else {
                context.status(Constants.NOT_FOUND);
            }
            tagDAO().closeCurrentSession();
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
            tagDAO().openCurrentSessionwithTransaction();
            tagDAO().update(newTag);
            tagDAO().closeCurrentSessionwithTransaction();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
