package Controller;

import Dao.EmployeeDAO;
import POJO.Employee;
import Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeController implements CrudHandler {
    private static EmployeeDAO employeeDAO;
    private Logger logger;

    public EmployeeController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try{
            employeeDAO = new EmployeeDAO();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");
        }
    }

    public EmployeeDAO employeeDAO() {
        return employeeDAO;
    }


    @Override
    public void create(@NotNull Context context) {
        Employee employee = context.bodyAsClass(Employee.class);
        try {
            employeeDAO().openCurrentSessionwithTransaction();
            employeeDAO().persist(employee);
            employeeDAO().closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int employeeId = Integer.valueOf(s);
        try {
            employeeDAO().openCurrentSessionwithTransaction();
            Employee employee = employeeDAO().findById(employeeId);
            employeeDAO().delete(employee);
            employeeDAO().closeCurrentSessionwithTransaction();
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
            employeeDAO.openCurrentSession();
            context.json(employeeDAO().findAll());
            employeeDAO().closeCurrentSession();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int employeeId = Integer.valueOf(s);
        try {
            employeeDAO.openCurrentSession();
            Employee employee = employeeDAO.findById(employeeId);
            if (employee != null){
                context.json(employee);
            } else {
                context.status(Constants.NOT_FOUND);
            }
            employeeDAO().closeCurrentSession();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        int employeeId = Integer.valueOf(s);
        Employee newEmployee = context.bodyAsClass(Employee.class);
        newEmployee.setId(employeeId);
        try {
            employeeDAO().openCurrentSessionwithTransaction();
            employeeDAO().update(newEmployee);
            employeeDAO().closeCurrentSessionwithTransaction();
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
