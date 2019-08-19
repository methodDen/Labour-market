package Controller;

import Dao.EmployerDAO;
import POJO.Employer;
import Utils.Constants;
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
        Employer employer = context.bodyAsClass(Employer.class);
        try {
            employerDAO().openCurrentSessionwithTransaction();
            employerDAO().persist(employer);
            employerDAO().closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int employerId = Integer.valueOf(s);
        try {
            employerDAO().openCurrentSessionwithTransaction();
            Employer employer = employerDAO().findById(employerId);
            employerDAO().delete(employer);
            employerDAO().closeCurrentSessionwithTransaction();
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
            employerDAO.openCurrentSession();
            context.json(employerDAO().findAll());
            employerDAO().closeCurrentSession();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int employerId = Integer.valueOf(s);
        try {
            employerDAO.openCurrentSession();
            Employer employer = employerDAO.findById(employerId);
            if (employer != null){
                context.json(employer);
            } else {
                context.status(Constants.NOT_FOUND);
            }
            employerDAO().closeCurrentSession();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        int employerId = Integer.valueOf(s);
        Employer newEmployer = context.bodyAsClass(Employer.class);
        newEmployer.setId(employerId);
        try {
            employerDAO().openCurrentSessionwithTransaction();
            employerDAO().update(newEmployer);
            employerDAO().closeCurrentSessionwithTransaction();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
