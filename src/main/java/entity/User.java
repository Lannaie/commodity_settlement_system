package entity;

/**
 * created by Bonnie on 2022/1/9
 */
public class User {
    private static String username;
    private static String password;
    private static String payword;

    public static String getPayword() {
        return payword;
    }

    public static void setPayword(String payword) {
        User.payword = payword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
