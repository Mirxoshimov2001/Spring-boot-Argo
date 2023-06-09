package ODDIY.LOYHA.lyh.repository.HomepageRepository;

import ODDIY.LOYHA.lyh.entity.HomePage.HomePageAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeAdviceRepository extends JpaRepository<HomePageAdvice, Long> {

    @Query(value = "select C from HomePageAdvice C where C.aboutUse =:aboutAdvice")
    List<HomePageAdvice> findByAboutAdvice(String aboutAdvice);

    HomePageAdvice findById(long id);

}
