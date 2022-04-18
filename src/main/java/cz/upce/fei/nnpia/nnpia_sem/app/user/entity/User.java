package cz.upce.fei.nnpia.nnpia_sem.app.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity.Watchlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank
    private String userName;

    @Column
    @NotBlank
    private String firstName;

    @Column
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    private String password;

    @Column
    @NotBlank
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rating> ratings;

    //@JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Watchlist> watchlist;
}
