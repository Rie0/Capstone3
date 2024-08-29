package org.twspring.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class ArtWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;


    @NotEmpty(message = "description cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private  String description;

    @NotEmpty(message = "image url cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private  String imageUrl ;

//    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "enum('BID', 'NORMAL')")
//    private SellType sellType;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;

    @ManyToOne
    //@JoinColumn(name = "art_work_id",referencedColumnName = "id")
    @JsonIgnore
    private Artist artist;

    @NotNull
    @Column(columnDefinition = "INT DEFAULT 0")
    private int likeCount = 0;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "artwork_likes",
            joinColumns = @JoinColumn(name = "artwork_id"),
            inverseJoinColumns = @JoinColumn(name = "artenthusiast_id")
    )
    @JsonIgnore
    private Set<ArtEnthusiast> likedBy = new HashSet<>();

   // @ManyToMany(mappedBy = "likedArtWorks")
    //@JsonIgnore
    //private Set<ArtEnthusiast> likedByEnthusiasts = new HashSet<>();

    //move to ArtPieceForSale
    public enum SellType{
        BID,
        NORMAL
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artWork")
    private Set<Comment> comments;
}
