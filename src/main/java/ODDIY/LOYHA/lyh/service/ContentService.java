package ODDIY.LOYHA.lyh.service;

import ODDIY.LOYHA.lyh.entity.Content;
import ODDIY.LOYHA.lyh.repository.order_content.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<Content> findAll(){
        return contentRepository.findAll();
    }

    public List<Content> findByClass(String className){
        return contentRepository.findContentsByContent(className);
    }

    public Content findById(long id){
        return contentRepository.findById(id);
    }
    public Content Crete(Content content){
        contentRepository.save(content);
        return content;
    }

    public List<Content> findByContent(String title){
        System.out.println("men ishlamayapman");
       return contentRepository.fintByContent(title);
    }
    public void Delete(long id){
        Content content = findById(id);
        if (content != null) {
            contentRepository.delete(content);
        }
    }
}
