package spring.intro;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("John", 33));
        userService.add(new User("Jack", 25));
        userService.add(new User("Josh", 20));
        userService.add(new User("Monica", 22));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println();
        }
    }
}
