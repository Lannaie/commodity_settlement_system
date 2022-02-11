package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Bonnie on 2022/1/11
 */
public class Commodity {

    private static List<Good> goods;

    public static List<Good> getGoods() {
        return goods;
    }

    public static void setGoods(List<Good> goods) {Commodity.goods = new ArrayList<>(goods);
    }
}
