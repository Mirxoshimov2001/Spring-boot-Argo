package ODDIY.LOYHA.lyh.controller.fileController;

import ODDIY.LOYHA.lyh.entity.Entity_file.FileEntity;
import ODDIY.LOYHA.lyh.service.File.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/file")
public class ControllerFile {
    private final FileService fileService;

    @Value("${storage.location}")
    private String uploadPath;

    public ControllerFile(FileService fileService) {
        this.fileService = fileService;
    }


    @GetMapping("/get_file/{hashId}")
    public ResponseEntity getFile(@PathVariable String hashId) throws IOException {

        FileEntity fileEntity = fileService.findHashId(hashId);


//        System.out.println(fileEntity.getContentType());
        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\""+fileEntity.getName())
//                .contentType(MediaType.parseMediaType(fileEntity.getContentType()))
//                .contentLength(fileEntity.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",this.uploadPath, fileEntity.getUplodePath())));
    }
}
