package dev.farhan.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//This class talks to the DB to fetch the data and send the data to Movie Service
@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    //Because i dont want to expose the ID of the movie i use Automated Query to expose the imdbID instead
    //The Java is that intelligent to understand what i want to do with only this line. No SQL queries required
    Optional<Movie> findMovieByImdbId(String imdbId);
}
