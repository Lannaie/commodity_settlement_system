package entity;

/**
 * created by Bonnie on 2022/1/16
 */
public class Settlement {
    private static String username;
    private static int imgid;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Settlement.username = username;
    }

    public static int getImgid() {
        return imgid;
    }

    public static void setImgid(int imgid) {
        Settlement.imgid = imgid;
    }
}
