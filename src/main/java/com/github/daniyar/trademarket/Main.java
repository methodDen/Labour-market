package com.github.daniyar.trademarket;

import com.github.daniyar.trademarket.Controller.*;
import com.github.daniyar.trademarket.Utils.Constants;
import com.github.daniyar.trademarket.Utils.JacksonUtils;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import org.h2.tools.Server;


import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .port(Constants.PORT);
        JavalinJackson.configure(JacksonUtils.getMapper());
        app.routes(()->{
//            crud("company/:id", new CompanyController()); // write all paths manually
            crud("employee/:id", new EmployeeController());
//            crud("employer/:id", new EmployerController()); // write all paths manually
            crud("job/:id", new JobController());
            crud("rating/:id", new RatingController());
            crud("tag/:id", new TagController());

            path("company", ()-> {
                get("/unsecured", ctx -> new CompanyController().getAllForUsers(ctx));
                get("/secured", ctx -> new CompanyController().getAll(ctx));
                get("/unsecured/:id", ctx -> new CompanyController().getOneForUsers(ctx, ctx.pathParam("id")));
                get("/secured/:id", ctx -> new CompanyController().getOne(ctx, ctx.pathParam("id")));
            });

            path("employer", ()-> {
                get("/unsecured", ctx -> new EmployerController().getAllForUsers(ctx));
                get("/secured", ctx -> new EmployerController().getAll(ctx));
                get("/unsecured/:id", ctx -> new EmployerController().getOneForUsers(ctx, ctx.pathParam("id")));
                get("/secured/:id", ctx -> new EmployerController().getOne(ctx, ctx.pathParam("id")));
            });
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
