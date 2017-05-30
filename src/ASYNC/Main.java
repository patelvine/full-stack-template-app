package ASYNC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String...args) throws InterruptedException, ExecutionException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	MailSender mailSender = context.getBean(MailSender.class);

        System.out.println("about to run");
        Future<Boolean> future = mailSender.sendMail();
        System.out.println("this will run immediately.");

        Boolean result = future.get();

        System.out.println("mail send result: " + result);
    }
}
