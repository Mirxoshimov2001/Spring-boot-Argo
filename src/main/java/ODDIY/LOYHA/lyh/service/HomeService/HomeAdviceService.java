package ODDIY.LOYHA.lyh.service.HomeService;

import ODDIY.LOYHA.lyh.entity.HomePage.HomePageAdvice;
import ODDIY.LOYHA.lyh.repository.HomepageRepository.HomeAdviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeAdviceService {

    private final HomeAdviceRepository repository;

    public void save(HomePageAdvice homePageAdvice){
        repository.save(homePageAdvice);
    }

    public HomeAdviceService(HomeAdviceRepository repository) {
        this.repository = repository;
    }

    public List<HomePageAdvice> findAll(){
        return repository.findAll();
    }

    public  HomePageAdvice findById(long id){
        return repository.findById(id);
    }

    public List<HomePageAdvice> findByAboutUse(String aboutAdvice){
        return repository.findByAboutAdvice(aboutAdvice);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }
}
