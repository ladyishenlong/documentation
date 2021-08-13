
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * redis前缀以及过期时间
 */
@Getter
@AllArgsConstructor
public enum RedisPrefixEnum {

    USER_INFO(RedisPrefix.USER_INFO, 60 * 60 * 24),
    ;

    String prefix;
    long seconds;

    public static class RedisPrefix {
        public static final String USER_INFO = "user-info";
    }

}
