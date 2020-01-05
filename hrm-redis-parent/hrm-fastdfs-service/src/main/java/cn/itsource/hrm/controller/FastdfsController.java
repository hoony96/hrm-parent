package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.util.FastDfsApiOpr;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
        try {
            String file_id = uploadFile(file);
            return AjaxResult.me().setResultObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败");
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

    /**
     *   文件下载  不需要层传参 response这个
     * @param file_id
     * @param response
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void download(@RequestParam("fileId")String file_id, HttpServletResponse response){
        file_id = file_id.substring(1);//去掉第一个  /
        int index = file_id.indexOf("/");
        String groupName = file_id.substring(0,index);
        String fileName = file_id.substring(index+1);
        byte[] bytes = FastDfsApiOpr.download(groupName, fileName);

        try {
            OutputStream out = response.getOutputStream(); // 获取输出流
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传 - feign
     * feign 实现文件上传
     * @return
     */
    @RequestMapping(
            value = "/feignUpload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult feignUpload(@RequestPart(value = "file")MultipartFile file){
        try {
            String file_id = uploadFile(file);
            return AjaxResult.me().setResultObj(file_id);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败!"+e.getMessage());
        }
    }


    private String uploadFile(MultipartFile file)throws IOException{
        String fileName = file.getName();
        String originalFilename = file.getOriginalFilename();

        //文件后缀名
        int index = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(index + 1);


        return FastDfsApiOpr.upload(file.getBytes(), extName);

    }




}
