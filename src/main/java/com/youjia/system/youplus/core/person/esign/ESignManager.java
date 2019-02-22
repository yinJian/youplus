package com.youjia.system.youplus.core.person.esign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youjia.system.youplus.core.person.PtGroundPerson;
import com.youjia.system.youplus.global.http.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2019/2/13.
 */
@Component
public class ESignManager {
    @Resource
    private HttpUtil httpUtil;
    @Value("${sign.url}")
    private String url;
    @Value("${sign.docId}")
    private String docId;

    public String createPersonAccountId(PtGroundPerson ptGroundPerson) {
        Map<String, Object> map = new HashMap<>();
        map.put("thirdId", ptGroundPerson.getPaper());
        map.put("name", ptGroundPerson.getUserName());
        map.put("idNo", ptGroundPerson.getPaper());
        map.put("idType", 19);
        String s = httpUtil.buildSign(url + "/account/create/person", map);
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        String personAccountid = "";
        if (0 != errCode) {
            return null;
        } else {
            String data = result.getString("data");
            JSONObject obj = JSON.parseObject(data);
            personAccountid = obj.getString("accountId");
            System.out.println("创建个人账户成功！" + result);
        }
        return personAccountid;
    }

    public void upload() {
        Map<String, Object> map = new HashMap<>();
        map.put("fileKey", "$79fee77f-8786-4967-b0bc-2ea714efaf94$566994283");
        String s = httpUtil.buildSign(url + "/doc/createbyfilekey", map);
        System.out.println(s);
        //"data":{"docId":"0e1c106c2cb445c5a58557fb9a35f498","docUrl":"https://esignoss.oss-cn-hangzhou.aliyuncs
        // .com/4438758707/7d556253-4f65-42fc-a4c7-366a3ce9e857/%E6%B5%8B%E8%AF%95%E5%90%88%E5%90%8C_test
        // .pdf?Expires=1550130530&OSSAccessKeyId=LTAIdvHfiVrzDKbE&Signature=Soz9kDaGzlKtZ%2BIn6vy75UDZkTM%3D"}}
    }

    /**
     * 创建个人合同
     */
    public String createAgreement(PtGroundPerson ptGroundPerson) {
        Map<String, Object> map = new HashMap<>();
        map.put("templateId", "");
        map.put("name", ptGroundPerson.getUserName() + "合同");

        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("", "");
        map.put("simpleFormFields", fieldMap);
        String s = httpUtil.buildSign(url + "/doc/createbytemplate", map);
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        String personAgreementId = "";
        if (0 != errCode) {
            return null;
        } else {
            String data = result.getString("data");
            JSONObject obj = JSON.parseObject(data);
            personAgreementId = obj.getString("docUrl");
            System.out.println("创建合同" + result);
        }
        return personAgreementId;
    }

    /**
     * 创建签署流程
     */
    public String createSignFlow() {
        Map<String, Object> map = new HashMap<>();
        map.put("businessScene", "线下兼职人员聘用协议");
        map.put("docId", docId);
        map.put("signPlatform", "1");
        String s = httpUtil.buildSign(url + "/sign/contract/addProcess", map);
        //"flowId":"c49be53c7e2347759eaf5f320a84c82d"
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        String flowId = "";
        if (0 != errCode) {
            return null;
        } else {
            String data = result.getString("data");
            JSONObject obj = JSON.parseObject(data);
            flowId = obj.getString("flowId");
        }
        return flowId;
    }

    /**
     * 个人手动签署
     */
    public String sign(String accountId, String flowId) {
        Map<String, Object> map = new HashMap<>();
        map.put("flowId", flowId);
        map.put("accountId", accountId);
        map.put("signPlatform", "1");
        String s = httpUtil.buildSign(url + "/sign/contract/handPersonSignTask", map);
        //"flowId":"c49be53c7e2347759eaf5f320a84c82d"
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        String signUrl = "";
        if (0 != errCode) {
            return null;
        } else {
            String data = result.getString("data");
            JSONObject obj = JSON.parseObject(data);
            signUrl = obj.getString("signUrl");
        }
        return signUrl;
    }

    /**
     * 查询签署状态
     */
    public boolean signState(String flowId) {
        Map<String, Object> map = new HashMap<>();
        map.put("flowId", flowId);
        String s = httpUtil.buildSign(url + "/sign/contract/detail", map);
        //"flowId":"c49be53c7e2347759eaf5f320a84c82d"
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        if (0 != errCode) {
            return false;
        } else {
            String data = result.getString("data");
            JSONObject obj = JSON.parseObject(data);
            int state = obj.getInteger("flowStatus");
            if (state == 2) {
                guidang(flowId);
            }
            return state == 2;
        }
    }

    /**
     * 归档
     */
    public String guidang(String flowId) {
        Map<String, Object> map = new HashMap<>();
        map.put("flowId", flowId);
        String s = httpUtil.buildSign(url + "/sign/contract/archiveProcess", map);
        //"flowId":"c49be53c7e2347759eaf5f320a84c82d"
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        if (0 != errCode) {
            return null;
        } else {
            return "success";
        }
    }

    /**
     * 下载
     */
    public String download(String flowId) {
        Map<String, Object> map = new HashMap<>();
        map.put("flowId", flowId);
        String s = httpUtil.buildSign(url + "/sign/download", map);
        //"flowId":"c49be53c7e2347759eaf5f320a84c82d"
        JSONObject result = JSON.parseObject(s);
        int errCode = result.getInteger("errCode");
        if (0 != errCode) {
            return null;
        } else {
            String data = result.getString("data");
            JSONArray obj = JSON.parseArray(data);
            JSONObject jsonObject = (JSONObject) obj.get(0);
            return jsonObject.getString("docUrl");
        }
    }

}
