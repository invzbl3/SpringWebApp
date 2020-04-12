package client;

import com.web.app.dto.UsersDTO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.util.Random;

@SpringBootApplication
public class UserRegistrationSystemClientApplication {

    private static long id;

    public static void main(String[] args) {
        UserRegistrationClient userRegistrationClient = new UserRegistrationClient();

        UserRegistrationSystemClientApplication uRegistrationClient = new UserRegistrationSystemClientApplication();

        id = uRegistrationClient.createUser(userRegistrationClient);
        uRegistrationClient.listAllUsers(userRegistrationClient);
        uRegistrationClient.getUserById(userRegistrationClient);
        uRegistrationClient.getUserByIdUsingExchange(userRegistrationClient);
        uRegistrationClient.updateUserById(userRegistrationClient);
        // call delete at the last
        uRegistrationClient.deleteUser(userRegistrationClient);
    }

    private Long createUser(UserRegistrationClient userRegistrationClient) {
        UsersDTO user = new UsersDTO();
        user.setName("Steve" + new Random().nextInt());
        user.setAddress("42nd Street");
        user.setEmail("steve.author@gmail.com");
        userRegistrationClient.createUser(user);

        user.setName("Steve" + new Random().nextInt());
        user.setAddress("42nd Street");
        user.setEmail("steve.author@gmail.com");
        UsersDTO newUser = userRegistrationClient.createUser(user);
        System.out.println(newUser.getId());
        return newUser.getId();
    }

    private void listAllUsers(UserRegistrationClient userRegistrationClient) {
        System.out.println("listAllUsers");
        UsersDTO[] usersList = userRegistrationClient.getAllUsers();
        System.out.println(usersList.length);
    }

    private void getUserById(UserRegistrationClient userRegistrationClient) {
        System.out.println("getUserById");
        UsersDTO user = userRegistrationClient.getUserById(id);
        System.out.println(
                "User-ID" + user.getId() + " User-Name" + user.getName());
    }

    private void updateUserById(UserRegistrationClient userRegistrationClient) {
        System.out.println("updateUserById");
        UsersDTO user = userRegistrationClient.getUserById(id);
        System.out.println("old user name: " + user.getName());
        user.setName("Steve " + id);
        userRegistrationClient.updateUser(id, user);
        System.out.println("updated user name: " + user.getName());
    }

    private void deleteUser(UserRegistrationClient userRegistrationClient) {
        System.out.println("deleteUser");
        System.out.println("Old Users List: "
                + userRegistrationClient.getAllUsers().length);
        userRegistrationClient.deleteUser(id);
        System.out.println("New Users List: "
                + userRegistrationClient.getAllUsers().length);

    }

    private void getUserByIdUsingExchange(
            UserRegistrationClient userRegistrationClient) {
        System.out.println("getUserByIdUsingExchange");
        ResponseEntity<UsersDTO> responseEntity = userRegistrationClient
                .getUserByIdUsingExchangeAPI(id);
        UsersDTO user = responseEntity.getBody();
        assert user != null;
        System.out.println(
                "User-ID" + user.getId() + " User-Name" + user.getName());
    }
}
