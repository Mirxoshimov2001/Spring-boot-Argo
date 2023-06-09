package ODDIY.LOYHA.lyh.service.File;

import ODDIY.LOYHA.lyh.entity.Entity_file.FileEntity;
import ODDIY.LOYHA.lyh.repository.File.FileRepo;
import ODDIY.LOYHA.lyh.service.ContentService;
import ODDIY.LOYHA.lyh.entity.Entity_file.fileEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileService {
    private final FileRepo fileRepo;


    @Value("${storage.location}")
    private String uploadPath;

    public FileService(FileRepo fileRepo, ContentService contentService, FileRepo fileRepo1) {
        this.fileRepo = fileRepo1;
    }

    public String save(MultipartFile file, String hashId) {
        FileEntity entity = new FileEntity();
        if (fileRepo.findByHashId(hashId) != null){
            entity = fileRepo.findByHashId(hashId);
            deletId(hashId);
        }

        entity.setName(file.getOriginalFilename());
        entity.setFileSize(file.getSize());
        entity.setContentType(file.getContentType());
        entity.setFileSize(file.getSize());
        entity.setExtension(extension(file.getOriginalFilename()));
        entity.setFileStatus(fileEnum.ACTIVE);
        fileRepo.save(entity);


        Date nowDay = new Date();
        File UplodeFolder = new File(String.format("%s/%d/%d/%d", this.uploadPath, 1900 + nowDay.getYear(), 1 + nowDay.getMonth(), nowDay.getDate()));

        if (!UplodeFolder.exists() && UplodeFolder.mkdirs()) {
            System.out.println("filelar uchun directoriyalar ochildi!");
        }

        entity.setHashId(hashId);
        entity.setUplodePath(String.format("%d/%d/%d/%s.%s",
                1900+nowDay.getYear(),
                1+nowDay.getMonth(),
                nowDay.getDate(),
                entity.getHashId(),
                entity.getExtension()));

        fileRepo.save(entity);


        UplodeFolder = UplodeFolder.getAbsoluteFile();
        File absolut_file = new File(UplodeFolder, String.format("%s.%s", entity.getHashId(), entity.getExtension()));
        try {
            file.transferTo(absolut_file);
            return "saqlandi";
        }catch (IOException exseption){
            exseption.printStackTrace();
            return "nimadur hato ketdi";
        }
    }

    @Transactional(readOnly = true)
    public FileEntity findHashId(String hashId){
        return fileRepo.findByHashId(hashId);
    }


    private void deletId(String hashId){
        FileEntity file1 = fileRepo.findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.uploadPath,file1.getUplodePath()));
        file.delete();
    }
    private static String extension(String fileName) {
        String ext = null;
        if (fileName != null && fileName.length() > 3) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }

        return ext;
    }
 public String OutExt(String OrgName){
        return extension(OrgName);
 }

}
