package com.github.daniyar.trademarket;

import com.github.daniyar.trademarket.Controller.*;
import com.github.daniyar.trademarket.Utils.Constants;
import com.github.daniyar.trademarket.Utils.JacksonUtils;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import org.h2.tools.Server;


import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.crud;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .port(Constants.PORT);
        JavalinJackson.configure(JacksonUtils.getMapper());
        app.routes(()->{
            crud("company/:id", new CompanyController());
            crud("employee/:id", new EmployeeController());
            crud("employer/:id", new EmployerController());
            crud("job/:id", new JobController());
            crud("rating/:id", new RatingController());
            crud("tag/:id", new TagController());
        });
        app.start();
        try {
            startH2();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void startH2() throws SQLException {
        Server server = Server.createTcpServer("-tcpAllowOthers").start();
    }
}
