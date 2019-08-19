package Controller;

import Dao.CompanyDAO;
import POJO.Company;
import Utils.Constants;
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
    public void create(@NotNull Context context) {
        Company company =context.bodyAsClass(Company.class);

        try {
            companyDAO().openCurrentSessionwithTransaction();
            companyDAO().persist(company);
            companyDAO().closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int companyId = Integer.valueOf(s);
        try {
            companyDAO().openCurrentSessionwithTransaction();
            Company c = companyDAO().findById(companyId);
            companyDAO().delete(c);
            companyDAO().closeCurrentSessionwithTransaction();
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
            companyDAO.openCurrentSession();
            context.json(companyDAO().findAll());
            companyDAO().closeCurrentSession();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int companyId = Integer.valueOf(s);
        try {
            companyDAO.openCurrentSession();
            Company c = companyDAO.findById(companyId);
            if (c != null){
                context.json(c);
            } else {
                context.status(Constants.NOT_FOUND);
            }
            companyDAO().closeCurrentSession();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        int companyId = Integer.valueOf(s);
        Company newCompany = context.bodyAsClass(Company.class);
        newCompany.setId(companyId);
        try {
            companyDAO().openCurrentSessionwithTransaction();
            companyDAO().update(newCompany);
            companyDAO().closeCurrentSessionwithTransaction();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
