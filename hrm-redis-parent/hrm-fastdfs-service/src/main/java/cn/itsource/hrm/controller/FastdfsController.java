package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 16:52 2019/12/30
 * @Version : v1.0
 */
@RestController
@RequestMapping("/file")
public class FastdfsController {

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        // 获取文件上传的后缀名
        int index = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(index + 1);

        // 上传文件到 FASTDFS
        try {
            String file_id = FastDfsApiOpr.upload(file.getBytes(), extName);
            return AjaxResult.me().setResultObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败,请重试");
        }
    }

    @GetMapping("/delete")
    public AjaxResult delete(String file_id){
        try {
            int index = file_id.indexOf("/");
            file_id = file_id.substring(index+1);
            // 组名
            String groupName = file_id.substring(0, file_id.indexOf("/"));
            // 文件名
            String fileName = file_id.substring(file_id.indexOf("/")+1);

            FastDfsApiOpr.delete(groupName,fileName);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败,请稍后再试!");
        }
    }

}
