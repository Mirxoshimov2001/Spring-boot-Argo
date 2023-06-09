package ODDIY.LOYHA.lyh.entity.Entity_file;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FileEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String extension;
    private long fileSize;
    private String hashId;
    private String contentType;
    private String uplodePath;
    @Enumerated(EnumType.STRING)
    private fileEnum fileStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUplodePath() {
        return uplodePath;
    }

    public void setUplodePath(String uplodePath) {
        this.uplodePath = uplodePath;
    }

    public fileEnum getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(fileEnum fileStatus) {
        this.fileStatus = fileStatus;
    }
}
