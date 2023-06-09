package ODDIY.LOYHA.lyh.repository.File;

import ODDIY.LOYHA.lyh.entity.Entity_file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface FileRepo extends JpaRepository<FileEntity, Long> {
    @Query("select e from FileEntity e where e.hashId =:hashId")
    FileEntity findByHashId(String hashId);

    FileEntity findById(long id);

}
