package com.mycompany.controller;

import com.mycompany.service.UserFileWorker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class FileController {

    @GetMapping("/upload_file")
    public String uploadFile(Model model) {
        model.addAttribute("path", "uploadfile.txt");
        return "uploading/uploadFile";
    }

    @PostMapping("/upload_file")
    public String uploadFile(@RequestParam(value = "path") String path) {

        UserFileWorker userFileWorker = new UserFileWorker("userlist.txt");

        try {
            userFileWorker.uploadUsers(path);
        } catch (IOException e) {
            return "redirect:wrong_filepath";
        }
        return "uploading/uploadResult";
    }

    @GetMapping("/wrong_filepath")
    public String wrongFilepath(){
        return "uploading/wrongPath";
    }

}
