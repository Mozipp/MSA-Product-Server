package com.mozipp.product.domain.review.repository;

import com.mozipp.product.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewee(Long id);
}
