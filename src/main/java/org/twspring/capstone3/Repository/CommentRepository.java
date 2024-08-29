package org.twspring.capstone3.Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.Comment;
import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByArtWorkId(Integer artWorkId);

}


