package com.github.daniyar.trademarket.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.daniyar.trademarket.Dao.EmployeeDAO;
import com.github.daniyar.trademarket.Deserializers.EmployeeDeserializer;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.Serializers.EmployeeSerializer;
import com.github.daniyar.trademarket.Utils.Constants;
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
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        mapper.registerModule(module);
        String json = context.body();
//        Employee employee = context.bodyAsClass(Employee.class);
        try {
            Employee employee = mapper.readValue(json, Employee.class);
            employeeDAO().persist(employee);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.OK_200);
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int employeeId = Integer.valueOf(s);
        try {
            Employee employee = employeeDAO().findById(employeeId);
            employeeDAO().delete(employee);
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
        module.addSerializer(Employee.class, new EmployeeSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(employeeDAO().findAll()));
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
        module.addSerializer(Employee.class, new EmployeeSerializer());
        mapper.registerModule(module);
        int employeeId = Integer.valueOf(s);
        try {
            Employee employee = employeeDAO.findById(employeeId);
            if (employee != null){
                context.result(mapper.writeValueAsString(employee));
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
        int employeeId = Integer.valueOf(s);
        Employee newEmployee = context.bodyAsClass(Employee.class);
        newEmployee.setId(employeeId);
        try {
            employeeDAO().update(newEmployee);
            context.status(Constants.OK_200);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
