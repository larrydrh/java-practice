package swagger.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ApiModel(value = "欢迎", description = "欢迎词")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloVO {
    @ApiModelProperty("欢迎信息")
    String message;

}
