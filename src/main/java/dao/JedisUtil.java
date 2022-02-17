package dao;


import redis.clients.jedis.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by Bonnie on 2022/2/17
 */
public class JedisUtil {
    // JedisPool
    JedisCommands jedisCommands;
    JedisPool jedisPool;
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    String ip = "192.168.186.100";
    int port = 6379;
    int timeout = 2000;
    String auth = "niit1234";
    // 数据格式
    private String TABLE = "commodity:inventory";
    private String PREFIX_KEY = "imgid:";

    public JedisUtil()
    {
        // 初始化Jedis
        // 设置配置
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPoolConfig.setTestOnBorrow(false);//jedis 第一次启动时，会报错
        jedisPoolConfig.setTestOnReturn(true);
        // 初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, ip, port, timeout, auth);
        // 获取一个jedis
        Jedis jedis = jedisPool.getResource();

        jedisCommands = jedis;
    }

    public void setValue(String key, String value)
    {
        this.jedisCommands.hset(TABLE, PREFIX_KEY + key, value);
    }

    public int getValue(String key) {
        String value = this.jedisCommands.hget(TABLE, PREFIX_KEY + key);
        if( null != value )
        {
            return Integer.parseInt(value);
        }else
        {
            String sql = "SELECT inventory FROM COMMODITY WHERE imgid = ?";
            try(Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = DBUtil.executeQuery(ps, new Object[]{key}))
            {
                if(rs.next())
                {
                    setValue(key, String.valueOf(rs.getInt("inventory")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return Integer.parseInt(this.jedisCommands.hget(TABLE, PREFIX_KEY + key));
        }
    }

    public Long decrValue(String key)
    {
        return this.jedisCommands.hincrBy(TABLE, PREFIX_KEY + key, -1);
    }
}
