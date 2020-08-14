package swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import swagger.api.CommonResult;
import swagger.controller.vo.HelloVO;

@Api( tags = "HelloSwaggerController")
@RestController
public class HelloSwaggerController {

    @ApiOperation(value = "查看欢迎信息")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public CommonResult<HelloVO> hello() {
        return CommonResult.success(new HelloVO("hello swagger"));
    }
}
