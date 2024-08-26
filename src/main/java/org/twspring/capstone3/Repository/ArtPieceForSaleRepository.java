package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.twspring.capstone3.Model.ArtPieceForSale;

import java.util.Optional;

public interface ArtPieceForSaleRepository extends JpaRepository<ArtPieceForSale, Integer> {

       ArtPieceForSale findArtPieceForSaleById(Integer id);

}
