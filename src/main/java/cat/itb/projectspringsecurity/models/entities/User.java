package cat.itb.projectspringsecurity.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements Serializable {

    @Id
    private String userName;
    private String password;
    private String rol;

    private final static String ROL_ADMIN = "ADMIN";
    private final static String ROL_DEFAULT = "USER";


    public void setRol(String rol) {

        if (rol.equals(ROL_DEFAULT)) {
            this.rol = ROL_DEFAULT;
        } else if (rol.equals(ROL_ADMIN)) {
            this.rol = ROL_ADMIN;
        } else {
            this.rol = ROL_DEFAULT;
        }
    }
}
