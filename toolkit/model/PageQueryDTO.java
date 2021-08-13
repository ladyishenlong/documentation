
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author ruanchenhao
 * @Date 2021/4/27 9:56 上午
 * @Desc
 */
@Data
@ApiModel("分页查询参数")
public class PageQueryDTO {

    @ApiModelProperty("当前页数")
    private long current = 0;

    @ApiModelProperty("每页条数")
    private long size = 10;

}
