package ODDIY.LOYHA.lyh.repository.order_content;

import ODDIY.LOYHA.lyh.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findContentsByContent(String classes);

    Content findById(long id);

    List<Content> findAll();

    @Query(value = "select C from Content C where C.title =:title")
    List<Content> fintByContent(String title);
}
