package ODDIY.LOYHA.lyh.repository.order_content;

import ODDIY.LOYHA.lyh.entity.Admin_menu.Help_entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpRepository extends JpaRepository<Help_entity, Long> {
    List<Help_entity> findAll();
    Help_entity findById(long id);
}
