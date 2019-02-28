package com.youjia.system.youplus.core.product;

import com.youjia.system.youplus.core.person.PtGroundPerson;
import com.youjia.system.youplus.core.person.PtGroundPersonManager;
import com.youjia.system.youplus.core.product.flow.PtOrderFlow;
import com.youjia.system.youplus.core.product.flow.PtOrderFlowManager;
import com.youjia.system.youplus.core.product.order.PtProductOrder;
import com.youjia.system.youplus.core.product.order.PtProductOrderManager;
import com.youjia.system.youplus.core.wechat.ChangeStateEvent;
import com.youjia.system.youplus.core.wechat.event.ChangeStateBean;
import com.youjia.system.youplus.global.util.ZipUtils;
import com.youjia.system.youplus.global.zip.ZipStreamEntity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/12/19.
 */
@Component
public class OrderFlowService {
    @Resource
    private PtOrderFlowManager ptOrderFlowManager;
    @Resource
    private PtProductOrderManager ptProductOrderManager;
    @Resource
    private PtGroundPersonManager ptGroundPersonManager;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;


    public PtOrderFlow addOrUpdate(PtOrderFlow ptOrderFlow) {
        Long id = ptOrderFlow.getId();
        if (id == null || !ptOrderFlowManager.exist(id)) {
            return ptOrderFlowManager.add(ptOrderFlow);
        } else {
            return ptOrderFlowManager.update(ptOrderFlow);
        }
    }

    public void stateChange(Long productOrderId, Integer stateChange) {
        PtProductOrder ptProductOrder = ptProductOrderManager.find(productOrderId);

        PtGroundPerson ptGroundPerson = ptGroundPersonManager.find(ptProductOrder.getGroundPersonId());

        String statesMsg = "审核通过";
        if (stateChange % 2 == 0) {
            statesMsg = "审核失败";
        }
        String orderStatus = "";
        if (stateChange <= 2) {
            orderStatus = "提交押金垫付文件审核";
        } else if (stateChange <= 4) {
            orderStatus = "上传住院押金条审核";
        } else if (stateChange <= 6) {
            orderStatus = "出院结算明细审核";
        }
        applicationEventPublisher.publishEvent(new ChangeStateEvent(new ChangeStateBean(ptGroundPerson.getOpenid(),
                statesMsg, orderStatus)));
    }

    public void packageBag(HttpServletResponse response, String files, String fileName) throws Exception {
        List<ZipStreamEntity> list = new ArrayList<>();
        fileName += ".zip";

        String[] paths = files.split(",");
        for (String path : paths) {
            File file = readFile(path);
            InputStream fileInputStream = new FileInputStream(file);
            list.add(new ZipStreamEntity(file.getName(), fileInputStream));
        }

        ZipUtils.listStreamToZipStream(list, fileName, response);
    }

    private File readFile(String path) throws Exception {
        //new一个URL对象
        URL url = new URL("http://img.hexun.com/2011-06-21/130726386.jpg");
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File(System.currentTimeMillis() + ".png");
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();

        return imageFile;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
