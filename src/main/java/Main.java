import Controller.*;
import Utils.Constants;
import Utils.JacksonUtils;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

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


    }
}
