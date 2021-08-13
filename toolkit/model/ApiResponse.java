
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author ruanchenhao
 * @Date 2021/3/24 3:04 下午
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {


    public static final int SUCCESS_CODE = 0;

    public static final int FAILURE_CODE = 1;

    public static final String SUCCESS_MSG = "请求成功";

    public static final String FAILURE_MSG = "请求失败";


    private Integer code;

    private String message;

    private Object detailMessage;

    private T results;


    public static <T> ApiResponse<T> response(Integer code, String message, Object detailMessage, T results) {
        return new ApiResponse<T>()
                .setCode(code)
                .setMessage(message)
                .setDetailMessage(detailMessage)
                .setResults(results);
    }


    public static <T> ApiResponse<T> success() {
        return response(SUCCESS_CODE, SUCCESS_MSG, null, null);
    }


    public static <T> ApiResponse<T> success(T results) {
        return response(SUCCESS_CODE, SUCCESS_MSG, null, results);
    }


    public static <T> ApiResponse<T> failure() {
        return response(FAILURE_CODE, FAILURE_MSG, null, null);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return response(FAILURE_CODE, message, null, null);
    }

    public static <T> ApiResponse<T> failure(Integer code, String message) {
        return response(code, message, null, null);
    }


    public static <T> ApiResponse<T> failure(Integer code, String message, Object detail) {
        return response(code, message, detail, null);
    }


}
