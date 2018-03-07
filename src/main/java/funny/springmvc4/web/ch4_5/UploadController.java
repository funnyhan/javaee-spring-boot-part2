package funny.springmvc4.web.ch4_5;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 14:42 2018/3/5
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file){    //  1   使用MultipartFile 接受上传的文件
        try {
            //  2   使用FileUtils.writeByteArrayToFile快速写文件到磁盘
            System.out.println(file.getOriginalFilename());
            FileUtils.writeByteArrayToFile(new File("d:/upload/"+file.getOriginalFilename()),file.getBytes());
            System.out.println("ok");
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("wrong");
            return "wrong";
        }
    }
}
