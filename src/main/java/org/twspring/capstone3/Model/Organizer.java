package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(35) NOT NULL UNIQUE")
    @NotEmpty(message = "username cannot be empty")
    @Size(min=4,max = 25, message = "Username must have between 4 to 25 characters")
    private String username;


    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Password must be strong (at least eight characters: one uppercase letter, one lowercase letter, one number, and one special character)")
    @Column(columnDefinition = "VARCHAR(35) NOT NULL")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "VARCHAR(35) NOT NULL UNIQUE")
    private String email;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status= Status.PENDING;

    public enum Status{
        APPROVED,
        PENDING,
        REJECTED
    }

    //relationships
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer")
    private Set<Exhibition> exhibitions;
}


