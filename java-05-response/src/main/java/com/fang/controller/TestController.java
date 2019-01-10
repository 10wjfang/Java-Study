package com.fang.controller;

import com.fang.response.BusinessException;
import com.fang.response.ErrorEnum;
import com.fang.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * 测试控制器
 *
 * @author fwj
 * @date 2019-01-10 21:19
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public TestVO test() {
        TestVO testVO = new TestVO();
        testVO.setName("战神");
        testVO.setAge(23);
        testVO.setSex((byte)1);
        return testVO;
    }

    @GetMapping("/test2")
    public ResponseResult test2() {
        TestVO testVO = test();
        return ResponseResult.create(testVO);
    }

    @GetMapping("/test3")
    public ResponseResult test3() throws Exception {
        TestVO testVO = null;
        if (testVO == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        return ResponseResult.create(testVO);
    }
}
