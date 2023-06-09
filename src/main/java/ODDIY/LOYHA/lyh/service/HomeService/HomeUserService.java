package ODDIY.LOYHA.lyh.service.HomeService;


import ODDIY.LOYHA.lyh.entity.HomePage.HomePageUser;
import ODDIY.LOYHA.lyh.repository.HomepageRepository.HomeUserRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeUserService {

    private final HomeUserRepository repository;

    public HomeUserService(HomeUserRepository repository) {
        this.repository = repository;
    }

    public List<HomePageUser> findAll(){
        return repository.findAll();
    }

    public HomePageUser findById(long id){
        return repository.findById(id);
    }

    public void delete(long id){
        repository.deleteById(id);
    }
    public List<HomePageUser> findByNumber(String number){return repository.findByNumber(number);}

    public void save(HomePageUser pageUser){
        repository.save(pageUser);
    }
}
