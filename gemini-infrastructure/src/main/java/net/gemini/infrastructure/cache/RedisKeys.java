package net.gemini.infrastructure.cache;

/**
 * Redis Kye管理
 * @author edison
 */
public class RedisKeys {

    /**
     * 登录用户信息
     * @param loginId userId
     * @return 用户信息key
     */
    public static String getLoginInfoKey(String loginId) {
        return "sys:login-info:" + loginId;
    }
}
