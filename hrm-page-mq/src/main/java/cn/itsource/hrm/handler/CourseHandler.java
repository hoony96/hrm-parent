package cn.itsource.hrm.handler;

import cn.itsource.hrm.client.FileClient;
import cn.itsource.hrm.config.RabbitmqConfig;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 21:41 2020/1/5
 * @Version : v1.0
 */
@Component
public class CourseHandler {

    @Autowired
    private FileClient fileClient;

    /**
     * 监听 队列中 Course的变化
     */
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SITE})
    public void courseListenr(String msg, Message message, Channel channel){
        // 有变化执行的步骤
        // 下载   html=> msg 是一个 json字符串 要变成对象
        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 存放了 pageID  physicalPath  fileId
        String fileId = jsonObject.get("fileId").toString();
        System.out.println(fileId);
        String physicalPath = jsonObject.get("physicalPath").toString();

        // 写出到 hrm-course中
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            // 读入的文件
            Response response =  fileClient.download(fileId);
            inputStream = response.body().asInputStream();

            // 写出的位置
            outputStream = new FileOutputStream("D:/java/IdeaWorkSpace/hrm-vue/hrm-course/home.html");

            // 写出
            IOUtils.copy(inputStream,outputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
