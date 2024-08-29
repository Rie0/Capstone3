package org.twspring.capstone3.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Model.Comment;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Repository.ArtWorkRepository;
import org.twspring.capstone3.Repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArtWorkRepository artWorkRepository;
    private final ArtEnthusiastRepository artEnthusiastRepository;


    //EP
    public Comment leaveComment(Integer artWorkId, Integer artEnthusiastId, String text) {
        ArtWork artWork = artWorkRepository.findArtWorkById(artWorkId);

        if (artWork == null) {
            throw new ApiException("ArtWork not found");
        }
        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiastId);
        if (artEnthusiast == null) {
            throw new ApiException("ArtEnthusiast not found");
        }

        Comment comment = new Comment();
        comment.setArtWork(artWork);
        comment.setArtEnthusiast(artEnthusiast);
        comment.setText(text);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }
    //EP
    public List<Comment> getAllCommentsByArtWorkId(Integer artWorkId) {
        return commentRepository.findByArtWorkId(artWorkId);
    }

}
