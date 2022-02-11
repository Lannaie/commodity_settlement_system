/**
 * created by Bonnie on 2022/1/10
 */
import entity.User;
import impl.UserImpl;

import java.sql.SQLException;

public class connectTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");

//        UserImpl.add(user);
        System.out.println(UserImpl.findUser("abc", "ab", 0));
    }
}
