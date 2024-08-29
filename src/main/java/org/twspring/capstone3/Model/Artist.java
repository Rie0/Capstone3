package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Model.ArtistProfile;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(35) NOT NULL UNIQUE")
    @NotEmpty(message = "username cannot be empty")
    @Size(min=4,max = 25, message = "Username must have between 4 to 35 characters")
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


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "artist")
    @PrimaryKeyJoinColumn
    private ArtistProfile artistProfile;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "artist")
    private Set<ArtWork> artWorks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private Set<CommissionRequest> commissionRequests;

    @OneToOne(mappedBy = "artist", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="artist")
    private Set<Exhibition> exhibitions;
}
