package com.youjia.system.youplus.core.product;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 七牛token
 * @author wuweifeng wrote on 2018/12/24.
 */
@Component
public class QiniuService {
    @Value("${qiniu.ak}")
    private String qiniuAk;
    @Value("${qiniu.sk}")
    private String qiniuSk;
    @Value("${qiniu.bucket}")
    private String bucket;

    public String token() {
        Auth auth = Auth.create(qiniuAk, qiniuSk);
        return auth.uploadToken(bucket);
    }
}
