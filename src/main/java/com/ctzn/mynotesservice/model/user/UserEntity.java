package com.ctzn.mynotesservice.model.user;

import com.ctzn.mynotesservice.model.notebook.NotebookEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    Long id;

    @Column(nullable = false, length = 36, unique = true)
    String userId = UserIdFactory.produce();

    @Column(nullable = false, length = 50)
    @NonNull
    private String firstName;

    @Column(length = 50)
    @NonNull
    private String lastName;

    @Column(nullable = false, length = 50, unique = true)
    @NonNull
    private String email;

    @Column(nullable = false, length = 60)
    private String encodedPassword;

    @Column(nullable = false)
    private Integer rolesMask = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NotebookEntity> notebooks = new ArrayList<>();

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public void setPassword(String rawPassword) {
        encodedPassword = UserPasswordEncoder.encode(rawPassword);
    }

    public void setRolesMask(List<UserRole> roles) {
        rolesMask = UserRole.rolesToMask(roles);
    }

}
