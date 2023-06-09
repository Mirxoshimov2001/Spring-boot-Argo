package ODDIY.LOYHA.lyh.repository.SecurityRepository;

import ODDIY.LOYHA.lyh.entity.Security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select e from User e where e.userName =:userName")
    User findByUserName(String userName);

    boolean existsByUserName(String userName);
}
