package cn.itsource.hrm.client;


import cn.itsource.basic.util.AjaxResult;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(path="file",value = "FILE-SERVICE")
public interface FileClient {

    /**
     * 下载
     * @param file_id
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response download(@RequestParam("fileId")String file_id);


    /**
     *  上传文件
     * @param file
     * @return
     */
    @RequestMapping(
            value = "/feignUpload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    AjaxResult feignUpload(@RequestPart(value = "file")MultipartFile file);
}
