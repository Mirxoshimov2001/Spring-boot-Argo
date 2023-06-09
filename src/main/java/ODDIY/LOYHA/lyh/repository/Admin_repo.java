package ODDIY.LOYHA.lyh.repository;

import ODDIY.LOYHA.lyh.entity.Admin_menu.Admin_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface Admin_repo extends JpaRepository<Admin_entity,Long> {

    Admin_entity findById(long id);
    @Query("select e from Admin_entity e where e.newDate =:data")
    Admin_entity findByNewDate(Date data);
}
