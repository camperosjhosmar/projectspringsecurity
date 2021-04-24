package cat.itb.projectspringsecurity.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean executive;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Address address;

    public Employee(String name, String email, String phoneNumber, boolean executive, Address address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.executive = executive;

        address.setEmployee(this);
        this.address = address;
    }

    public void addAddress(Address address) {
        address.setEmployee(this);
        this.address = address;
    }

    public void removeAddress() {
        if (address != null) {
            address.setEmployee(null);
            this.address = null;
        }
    }

}
