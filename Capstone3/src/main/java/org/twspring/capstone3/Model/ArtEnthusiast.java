package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtEnthusiast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(35) NOT NULL")
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

    @NotNull(message = "Created date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate createdAt = LocalDate.now();

    @NotNull(message = "Updated date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate updatedAt = LocalDate.now();

}
