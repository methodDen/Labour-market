package com.github.daniyar.trademarket.Controller; // importing packages

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.daniyar.trademarket.Dao.EmployeeDAO;
import com.github.daniyar.trademarket.Deserializers.EmployeeDeserializer;
import com.github.daniyar.trademarket.POJO.Employee;
import com.github.daniyar.trademarket.Serializers.EmployeeSerializer;
import com.github.daniyar.trademarket.UserSerializers.EmployeeUserSerializer;
import com.github.daniyar.trademarket.Utils.Constants;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeController implements CrudHandler { // Model controller class that implements CRUD technology
    private static EmployeeDAO employeeDAO; // inputting DAO for Employee.class (Employee model)
    private Logger logger; // inputting Logger for displaying messages during compilation

    public EmployeeController() {
        logger = LoggerFactory.getLogger(this.getClass()); // initializing logger
        try{                                    // try-catch technology to handle errors
            employeeDAO = new EmployeeDAO();    // initializing DAO
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating DAO");         // message displayed by logger when error have occurred
        }
    }

    public EmployeeDAO employeeDAO() {
        return employeeDAO;
    } // constructor  for DAO


    @Override
    public void create(@NotNull Context context) { // create method for Employee class/entity/record/object
        ObjectMapper mapper = new ObjectMapper(); // inputting and initializing ObjectMapper
        SimpleModule module = new SimpleModule(); // inputting and initializing SimpleModule
        module.addDeserializer(Employee.class, new EmployeeDeserializer()); // adding deserializer to SimpleModule
        mapper.registerModule(module); // adding module to ObjectMapper
        String json = context.body(); // adapting data to json String in order to send data as http request

        try { // try-catch technology to handle errors
            Employee employee = mapper.readValue(json, Employee.class); // Creating Employee object and assigning json String to its parameters
            employeeDAO().persist(employee); // persisting Employee object to database via Employee DAO method
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");  // message displayed by logger when error have occurred
            context.status(Constants.OK_200); // status of http response displayed when error occurs
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) { // Delete method for Employee class/entity/record/object
        int employeeId = Integer.valueOf(s); // id to find Employee object in database table
        try { // try-catch technology to handle errors
            Employee employee = employeeDAO().findById(employeeId); // finding Employee object from database table by using DAO method and id
            employeeDAO().delete(employee); // deleting Employee object from database table
            context.status(Constants.OK_200); // status displayed when Employee object/record is deleted from database table
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured deleting a record"); // message displayed by logger when error have occurred
            context.status(Constants.INTERNAL_SERVER_ERROR); // status of http response displayed when error occurs
        }
    }

    @Override
    public void getAll(@NotNull Context context) { // Secure retrieving (with sensitive data) of all records of Employee objects
        ObjectMapper mapper = new ObjectMapper(); // inputting and initializing ObjectMapper
        SimpleModule module = new SimpleModule(); // inputting and initializing SimpleModule
        module.addSerializer(Employee.class, new EmployeeSerializer()); // adding serializer to SimpleModule
        mapper.registerModule(module);  // adding module to ObjectMapper
        try { // try-catch technology to handle errors
            context.result(mapper.writeValueAsString(employeeDAO().findAll())); // displaying ArrayList of Employee records via context and mapper + DAO methods
            context.status(Constants.OK_200); // status displayed when Employee objects/records are retrieved from database table
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records"); // message displayed by logger when error have occurred
            context.status(Constants.INTERNAL_SERVER_ERROR); // status of http response displayed when error occurs
        }
    }

    public void getAllForUsers(@NotNull Context context) { // insecure retrieving (without sensitive data) of all records of Employee objects
        ObjectMapper mapper = new ObjectMapper(); // inputting and initializing ObjectMapper
        SimpleModule module = new SimpleModule(); // inputting and initializing SimpleModule
        module.addSerializer(Employee.class, new EmployeeUserSerializer()); // adding serializer to SimpleModule
        mapper.registerModule(module); // adding module to ObjectMapper
        try { // try-catch technology to handle errors
            context.result(mapper.writeValueAsString(employeeDAO().findAll())); //  displaying ArrayList of Employee records (without sensitive data) via context and mapper + DAO methods
            context.status(Constants.OK_200); // status displayed when Employee objects/records are retrieved from database table
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting records"); // message displayed by logger when error have occurred
            context.status(Constants.INTERNAL_SERVER_ERROR); // status of http response displayed when error occurs
        }
    }


    @Override
    public void getOne(@NotNull Context context, @NotNull String s) { // secure retrieving (with sensitive data) of only one Employee object via given id
        ObjectMapper mapper = new ObjectMapper(); // inputting and initializing ObjectMapper
        SimpleModule module = new SimpleModule(); // inputting and initializing SimpleModule
        module.addSerializer(Employee.class, new EmployeeSerializer()); // adding serializer to SimpleModule
        mapper.registerModule(module); // adding module to ObjectMapper
        int employeeId = Integer.valueOf(s); // id to find Employee object in database table
        try { // try-catch technology to handle errors
            Employee employee = employeeDAO.findById(employeeId); // Assigning to Employee object found Employee record from database table via DAO method and id
            if (employee != null){ // if Employee object is not empty object... (if an object with given id exists)
                context.result(mapper.writeValueAsString(employee)); // display json with Employee object data
                context.status(Constants.OK_200); // status displayed when Employee object is retrieved
            } else { // if Employee object with given id doesn't exist
                context.status(Constants.NOT_FOUND); // status displayed when Employee object for given id doesn't exist
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record"); // message displayed by logger when error have occurred
            context.status(Constants.INTERNAL_SERVER_ERROR); // status of http response displayed when error occurs
        }
    }

    public void getOneForUsers(@NotNull Context context, @NotNull String s) { // insecure (without sensitive data) retrieving of Employee object via given id
        ObjectMapper mapper = new ObjectMapper(); // inputting and initializing ObjectMapper
        SimpleModule module = new SimpleModule(); // inputting and initializing SimpleModule
        module.addSerializer(Employee.class, new EmployeeUserSerializer()); // adding serializer to SimpleModule
        mapper.registerModule(module); // adding module to ObjectMapper
        int employeeId = Integer.valueOf(s); // id to find Employee object in database table
        try { // try-catch technology to handle errors
            Employee employee = employeeDAO.findById(employeeId); // Assigning to Employee object found Employee record from database table via DAO method and id
            if (employee != null){ // if Employee object is not empty object... (if an object with given id exists)
                context.result(mapper.writeValueAsString(employee)); // display json with Employee object data
                context.status(Constants.OK_200); // status displayed when Employee object is retrieved
            } else {
                context.status(Constants.NOT_FOUND); // status displayed when Employee object for given id doesn't exist
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured getting a record"); // message displayed by logger when error have occurred
            context.status(Constants.INTERNAL_SERVER_ERROR); // status of http response displayed when error occurs
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) { // updating Employee record via given id
        int employeeId = Integer.valueOf(s); // id to find Employee object in database table
        ObjectMapper mapper = new ObjectMapper(); // inputting and initializing ObjectMapper
        SimpleModule module = new SimpleModule(); // inputting and initializing SimpleModule
        module.addDeserializer(Employee.class, new EmployeeDeserializer()); // adding serializer to SimpleModule
        mapper.registerModule(module); // adding module to ObjectMapper
        String json = context.body(); // adapting data to json String in order to send data as http request
        try { // try-catch technology to handle errors
            Employee newEmployee = mapper.readValue(json, Employee.class); // updating Employee record in database table by assigning parameters of new Employee object
            newEmployee.setId(employeeId); // setting given id to new Employee object
            employeeDAO().update(newEmployee); // updating database table record via new Employee object
            context.status(Constants.OK_200); // http status displayed when record was updated successfully
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occured updating a record"); // message displayed by logger when error have occurred
            context.status(Constants.INTERNAL_SERVER_ERROR); // status of http response displayed when error occurs
        }
    }
}
