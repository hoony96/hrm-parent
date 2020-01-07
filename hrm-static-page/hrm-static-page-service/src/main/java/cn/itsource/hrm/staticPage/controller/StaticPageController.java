package cn.itsource.hrm.staticPage.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.client.FileClient;
import cn.itsource.hrm.client.PageClient;
import cn.itsource.hrm.client.RedisClient;
import cn.itsource.hrm.domain.Pager;
import cn.itsource.hrm.staticPage.util.FileUtil;
import cn.itsource.hrm.staticPage.util.VelocityUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:18 2020/1/5
 * @Version : v1.0
 */
@RestController
public class StaticPageController {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private PageClient pageClient;

    @Autowired
    private FileClient fileClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/staticPageInSP")
    public void staticPageInSP(@RequestParam("key") String key,@RequestParam("pageId") Long pageId){
        // 获取数据
        String str = redisClient.get(key);
        JSONArray types = JSONObject.parseArray(str);  // 转成什么类型 ?  => 转成 数组  用于加载数据到模板中 foreach


        // 下载模板
        Pager pager = pageClient.get(pageId);
        String fileId = pager.getTemplateUrl();

        Response response = fileClient.download(fileId);

        Response.Body body = response.body();
        InputStream inputStream = null;
        try {
            inputStream = body.asInputStream();
            // 文件保存位置
            String savePath = "D:/Course/nginx/temp/template.zip";
            OutputStream outputStream = new FileOutputStream(savePath);
            IOUtils.copy(inputStream,outputStream);  // 工具类  进行 拷贝 保存

            // 解压模板

            // 文件 解压位置
            String releasePath = "D:/Course/nginx/temp/";
            FileUtil.unZipFiles(new File(savePath),releasePath);  // 工具类 解压



            // 模板加数据  整合
            Map<String,Object> map = new HashMap<>();
            map.put("staticRoot",releasePath);  // 保存路径
            map.put("courseTypes",types);   // 为什么存为 数组形式

            // 第一个 参数 数据    二: 模板位置   三: 页面位置
            VelocityUtils.staticByTemplate(map,releasePath+"home.vm",releasePath+"home.html");


            // 页面上传到 fastdfs   > 转为MultipartFile

            FileItem fileItem = createFileItem(new File(releasePath+"home.html"));
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            AjaxResult ajaxResult = fileClient.feignUpload(multipartFile);// 接收上传后返回的 fileId
            String htmlFileId = ajaxResult.getResultObj().toString();
            System.out.println(htmlFileId);


            // 推送消息给MQ
            Map<String,Object> map1 = new HashMap<>();
            map1.put("pageId", pageId);
            map1.put("fileId", htmlFileId);
            map1.put("physicalPath", pager.getPhysicalPath());
            String msgStr = JSONObject.toJSONString(map1);
            rabbitTemplate.convertAndSend("hrm-course",msgStr);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        FileItem item = factory.createItem("file", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }


}
