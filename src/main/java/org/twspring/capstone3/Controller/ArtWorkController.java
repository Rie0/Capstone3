package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Model.Comment;
import org.twspring.capstone3.Service.ArtWorkService;
import org.twspring.capstone3.Service.CommentService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/artWork")
public class ArtWorkController {
    private final ArtWorkService artWorkService;
    private final CommentService commentService;


    @GetMapping("/get")
    public ResponseEntity getAllArtWork() {
        return ResponseEntity.status(200).body(artWorkService.getAllArtWork());
    }

    @PostMapping("/add/{artist_id}")
    public ResponseEntity addArtWorkToArtist(@PathVariable Integer artist_id, @Valid @RequestBody ArtWork artWork) {
        artWorkService.addArtWorkToArtist(artist_id, artWork);
        return ResponseEntity.status(200).body(new ApiResponse("Art work added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArtWork(@PathVariable Integer id, @Valid @RequestBody ArtWork artWork) {
        artWorkService.updateArtWork(id, artWork);
        return ResponseEntity.status(200).body(new ApiResponse("Art work updated successfully"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArtWorkFromArtist(@PathVariable Integer id) {
        artWorkService.deleteArtWork(id);
        return ResponseEntity.status(200).body(new ApiResponse("Art work deleted successfully"));
    }
    //extra
    @PostMapping("/{artEnthusiastId}/like/{artWorkId}")

    public ResponseEntity likeArtWork(@PathVariable Integer artEnthusiastId, @PathVariable Integer artWorkId){
        artWorkService.likeArtWork(artEnthusiastId, artWorkId);
        return ResponseEntity.status(200).body("ArtWork liked successfully");

    }
    //extra
    @GetMapping("/{artWorkId}/likes")
    public ResponseEntity getLikeCount(@PathVariable Integer artWorkId) {
        int likeCount = artWorkService.getLikeCount(artWorkId);
        return ResponseEntity.status(200).body(likeCount);
    }
    //extra
    @GetMapping("/popular")
    public ResponseEntity<List<ArtWork>> getPopularArtWorks(@RequestParam(value = "limit", required = false) Integer limit) {
        List<ArtWork> popularArtWorks = artWorkService.getPopularArtWorks(limit);
        return ResponseEntity.status(200).body(popularArtWorks);
    }
    //extra
    @GetMapping("/artist/{artistId}")
    public ResponseEntity getArtworksByArtist(@PathVariable Integer artistId) {
        List<ArtWork> artworks = artWorkService.getArtworksByArtist(artistId);
        return ResponseEntity.status(200).body(artworks);
    }
    //extra
    @GetMapping("/search")
    public ResponseEntity searchArtWorksByKeyword(@RequestParam("keyword") String keyword) {
        List<ArtWork> artworks = artWorkService.searchArtWorks(keyword);
        return ResponseEntity.ok(artworks);
    }
    //extra
    @PostMapping("/{artEnthusiastId}/comment/{artWorkId}")
    public ResponseEntity leaveComment(@PathVariable Integer artWorkId,
                                       @PathVariable Integer artEnthusiastId,
                                       @RequestParam String text) {
        Comment comment = commentService.leaveComment(artWorkId, artEnthusiastId, text);
        return ResponseEntity.status(200).body(new ApiResponse("comment added"));
    }
    //extra
    @GetMapping("/get/{artWorkId}")
    public ResponseEntity getAllCommentsByArtWorkId(@PathVariable Integer artWorkId) {
        List<Comment> comments = commentService.getAllCommentsByArtWorkId(artWorkId);
        return ResponseEntity.status(200).body(comments);

    }
}