package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.twspring.capstone3.Model.ArtPieceForSale;
import org.twspring.capstone3.Model.Shop;

import java.util.List;
import java.util.Optional;

public interface ArtPieceForSaleRepository extends JpaRepository<ArtPieceForSale, Integer> {

       ArtPieceForSale findArtPieceForSaleById(Integer id);
       List<ArtPieceForSale> findArtPieceForSaleByShopId(Integer shopId);

       @Query("SELECT a FROM ArtPieceForSale a WHERE a.id IN :ids")
       List<ArtPieceForSale> findAllByIds(@Param("ids") List<Integer> ids);
}
