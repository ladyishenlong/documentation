
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.htwater.sso.core.model.BizException;
import net.htwater.sso.enums.error.BizErrorEnum;

/**
 * jackson json格式转化工具
 */
@Slf4j
public class JsonUtils {


    public static String toJsonStr(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Json格式化异常：", e);
            throw new BizException(BizErrorEnum.JSON_ERROR);
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Json转化异常：", e);
            throw new BizException(BizErrorEnum.JSON_ERROR);
        }
    }


    public static <T> T toBean(String json, TypeReference<T> typeReference) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error("Json转化异常：", e);
            throw new BizException(BizErrorEnum.JSON_ERROR);
        }
    }

}