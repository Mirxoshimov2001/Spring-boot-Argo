package ODDIY.LOYHA.lyh.repository.HomepageRepository;

import ODDIY.LOYHA.lyh.entity.HomePage.HomePageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeUserRepository extends JpaRepository<HomePageUser, Long> {
//    @Query(value = "select C from HomePageAdvice C where C.number =:number")
    List<HomePageUser> findByNumber(String number);
    HomePageUser findById(long id);

}
