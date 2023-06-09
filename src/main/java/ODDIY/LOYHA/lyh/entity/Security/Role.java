package ODDIY.LOYHA.lyh.entity.Security;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
   private UserRoleStatic name;

    public UserRoleStatic getName() {
        return name;
    }

    public void setName(UserRoleStatic name) {
        this.name = name;

    }
}
