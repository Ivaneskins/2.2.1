package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user1 = new User("Test1", "Ivanov", "Ivan@gmail.com");
      Car car1 = new Car("Niva", 5);
      user1.setCars(car1);

      User user2 = new User("Test2", "Sidorov", "userSid@gmail.com");
      Car car2 = new Car("Ford", 10);
      user2.setCars(car2);

      User user3 = new User("Test3", "Makarena", "mak@gmail.com");
      Car car3 = new Car("Citroen", 4);
      user3.setCars(car3);

      User user4 = new User("Test4", "Hey", "hey@gmail.com");
      user4.setCars(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCars());
         System.out.println();
      }

      System.out.println(userService.getUser("Citroen", 4));

      context.close();
   }
}
