package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @Size(min=4,max = 25, message = "Username must have between 4 to 35 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Password must be strong (at least eight characters: one uppercase letter, one lowercase letter, one number, and one special character)")
    @Column(columnDefinition = "VARCHAR(40) NOT NULL")
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

    //relations

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artEnthusiast")
    private Set<ExhibitionTicket> exhibitionTickets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artEnthusiast")
    private Set<ArtOrder> artOrders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artEnthusiast")
    private Set<Bill> bills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artEnthusiast")
    private Set<CommissionRequest> commissionRequests;


    @ManyToMany
    @JoinTable(
            name = "art_enthusiast_likes",
            joinColumns = @JoinColumn(name = "art_enthusiast_id"),
            inverseJoinColumns = @JoinColumn(name = "art_work_id")
    )
    private Set<ArtWork> likedArtWorks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artEnthusiast")
    private Set<Comment> comments;
}

