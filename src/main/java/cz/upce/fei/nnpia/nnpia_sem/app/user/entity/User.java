package cz.upce.fei.nnpia.nnpia_sem.app.user.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Rating> ratings;
}
