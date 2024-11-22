package dev.farhan.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    //when i create a review I should post it to the DB
    @Autowired
    private ReviewRepository reviewRepository;

    //i need a template to assosiate the review with the movie
    @Autowired
    private MongoTemplate mongoTemplate;

    //We find the movie with imdbID and then assosiate the imdb with the given review
    public Review createReview(String reviewBody, String imdbId){

        //        Review review = new Review(reviewBody);
        //        reviewRepository.insert(review); //save the review to the DB
        // The two lines above can merge to the following line

        Review review = reviewRepository.insert(new Review(reviewBody));

        //We use the template to perform an update call to the movie class
        mongoTemplate.update(Movie.class)
                //This line updates the imdbId of the DB which is equals with the imdbId where user gives
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review));
                //.first();//this line create a BUG

        return review;
    }
}
