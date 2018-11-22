package com.youjia.system.youplus.core.company.goods;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuweifeng wrote on 2018/11/22.
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public BaseData index(@RequestBody PtGoodsPlan ptGoodsPlan) {
        System.out.println(ptGoodsPlan);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/index1")
    public BaseData index1(@RequestHeader("userId") String userId, PtGoodsPlan ptGoodsPlan) {
        System.out.println("ptGoodsPlan + " + userId);
        return ResultGenerator.genSuccessResult();
    }
}
