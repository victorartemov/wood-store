/**
 * Created by Виктор on 11.01.2017.
 */

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import woodstore.config.DataConfig;
import woodstore.config.WebConfig;

import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        System.out.println(context);
    }
}