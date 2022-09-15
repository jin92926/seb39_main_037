package com.main.project.review.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.review.entity.Review;
import com.main.project.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(long userId, long foodTypeId, long restaurantId, Review review) {

        verifyReview(review);


        return reviewRepository.save(review);
    }

    public Review updateReview(long reviewId, Review review) {

        //수정할 리뷰가 존재하는지 체크
        Review foundReview = findVerifiedReview(reviewId);
        //작성자와 회원이 동일한지 체크
//        if(작성자와 회원이 동일){
//            //수정할 사항(제목, 내용, 별점)이 존재하는지 체크
//
//
//            return reviewRepository.save(foundReview);
//        }else {
//            new BusinessLogicException(ExceptionCode.WRITER_IS_NOT_MATCH);
//        }
        return null;
    }

    public Review findReview(long reviewId) {

        return findVerifiedReview(reviewId);
    }

    public Page<Review> findAllReview(int page, int size) {

        return reviewRepository.findAll(PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    public void deleteReview(long reviewId) {

        Review review = findVerifiedReview(reviewId);
        reviewRepository.delete(review);

    }

    @Transactional
    public int updateView(long reviewId) {
        return reviewRepository.view(reviewId);
    }

    private void verifyReview(Review review) {
        // 회원이 존재하는지 확인

        // 식당이 존재하는지 확인


        // 음식 타입이 존재하는지 확인
    }

    public Review findVerifiedReview(long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);

        Review findReview = review.orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));

        return findReview;
    }

}