package com.amigoscode.movie;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Movie> selectMovies() {
        String sql = """
                select id, name, release_date
                from movie;
                """;
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper()); // MovieRowMapper maps each row of the result set to a Movie object (our own class)
        // movies became a list of Movie objects because query method maps each row to a Movie object and returns a list of them
        return movies;
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = """
                   insert into movie (name, release_date)
                   values(?, ?)
                   """;
        // we use update method for insert, update, delete operations
        return jdbcTemplate.update(sql, movie.name(), movie.releaseDate());

    }

    @Override
    public int deleteMovie(int id) {
        String sql = "delete from movie where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        String sql = "SELECT id, name, release_date FROM movie WHERE id = ?";
        try {
            Movie movie = jdbcTemplate.queryForObject(sql, new MovieRowMapper(), id);
            return Optional.ofNullable(movie);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();  // Kayıt yoksa boş Optional döndür
        }
    }


}
