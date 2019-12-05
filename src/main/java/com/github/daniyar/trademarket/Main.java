package com.github.daniyar.trademarket;

import com.github.daniyar.trademarket.Controller.*;
import com.github.daniyar.trademarket.Utils.AuthManagment;
import com.github.daniyar.trademarket.Utils.Constants;
import com.github.daniyar.trademarket.Utils.JacksonUtils;
import com.github.daniyar.trademarket.Utils.Role;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import org.h2.tools.Server;


import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.security.SecurityUtil.roles;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create() // creating and starting javalin server app
                .port(Constants.PORT);
        JavalinJackson.configure(JacksonUtils.getMapper());

        app.accessManager(((handler, ctx, permittedRoles) -> { // setting up access via roles
            Role role = AuthManagment.getUserRole(ctx); // get user role
            if(permittedRoles.contains(role)) { // if role is withing permitted roles list
                handler.handle(ctx); // handle methods
            }
            else {
                ctx.status(Constants.FORBIDDEN); // show status
            }
        }));


        app.routes(()-> { // app routes -> when you open web browser after starting the app, you can use these routes in order to implement crud methods

            path("employer", ()-> {
                get("/unsecured", ctx -> new EmployerController().getAllForUsers(ctx), roles(Role.ANONYMOUS, Role.AUTHORIZED));
                get("/unsecured/:id", ctx -> new EmployerController().getOneForUsers(ctx, ctx.pathParam("id")), roles(Role.ANONYMOUS, Role.ADMIN, Role.AUTHORIZED));
                get("/secured/:id", ctx -> new EmployerController().getOne(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED));
                post(ctx -> new EmployerController().create(ctx), roles(Role.ANONYMOUS));
                patch(":id", ctx -> new EmployerController().update(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED));
                delete(":id", ctx -> new EmployerController().delete(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED));
            });

            path("employee", ()-> { // route for employee
                get("/unsecured", ctx -> new EmployeeController().getAllForUsers(ctx), roles(Role.ANONYMOUS, Role.AUTHORIZED)); // get employee list (unsecured), permitted roles are listed
                get("/unsecured/:id", ctx -> new EmployeeController().getOneForUsers(ctx, ctx.pathParam("id")), roles(Role.ANONYMOUS, Role.ADMIN, Role.AUTHORIZED)); // get one employee object (unsecured), permitted roles are listed
                get("/secured/:id", ctx -> new EmployeeController().getOne(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED)); // get employee list (secured), permitted roles are listed
                post(ctx -> new EmployeeController().create(ctx), roles(Role.ANONYMOUS)); // get one employee object (secured), permitted roles are listed
                patch(":id", ctx -> new EmployeeController().update(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED)); // update employee record in database table, permitted roles are listed
                delete(":id", ctx -> new EmployeeController().delete(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED)); // delete employee record from database table, permitted roles are listed
            });

            crud("job/:id", new JobController());           // write all paths manually
            crud("rating/:id", new RatingController());
            crud("tag/:id", new TagController());

            path("company", ()-> {
                get("/unsecured", ctx -> new CompanyController().getAllForUsers(ctx), roles(Role.ANONYMOUS));
                get("/secured", ctx -> new CompanyController().getAll(ctx), roles(Role.AUTHORIZED));
                get("/unsecured/:id", ctx -> new CompanyController().getOneForUsers(ctx, ctx.pathParam("id")), roles(Role.ANONYMOUS));
                get("/secured/:id", ctx -> new CompanyController().getOne(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED));
                post(ctx -> new CompanyController().create(ctx), roles(Role.ANONYMOUS));
                patch(":id", ctx -> new EmployerController().update(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED));
                delete(":id", ctx -> new EmployerController().delete(ctx, ctx.pathParam("id")), roles(Role.AUTHORIZED));
            });


//            path("job", ()-> {
//                get("/unsecured", ctx -> new JobController().getAllForUsers(ctx));
//                get("/secured", ctx -> new JobController().getAll(ctx));
//                get("/unsecured/:id", ctx -> new JobController().getOneForUsers(ctx, ctx.pathParam("id")));
//                get("/secured/:id", ctx -> new JobController().getOne(ctx, ctx.pathParam("id")));
//            });
        });
        app.start(); // starting app
        try { // try-catch technology
            startH2(); // starting H2 database
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void startH2() throws SQLException { // method to start H2 database
        Server server = Server.createTcpServer("-tcpAllowOthers").start(); // starting server
    }
}
