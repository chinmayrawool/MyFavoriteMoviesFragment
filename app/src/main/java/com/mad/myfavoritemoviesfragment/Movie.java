package com.mad.myfavoritemoviesfragment;



import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Chinmay Rawool on 1/27/2017.
 */

public class Movie implements Serializable{
    private String name,description,imdbLink,genre;
    private int year,rating;

    public Movie(String name, String description, String imdbLink, String genre, int year, int rating) {
        this.name = name;
        this.description = description;
        this.imdbLink = imdbLink;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public Movie() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
    public static Comparator<Movie> MovieYear = new Comparator<Movie>() {

        public int compare(Movie m1, Movie m2) {

            int movieYear1 = m1.getYear();
            int movieYear2 = m2.getYear();

	   /*For ascending order*/
            return movieYear1-movieYear2;


        }
    };

    public static Comparator<Movie> MovieRating = new Comparator<Movie>() {

        public int compare(Movie m1, Movie m2) {

            int movieRating1 = m1.getRating();
            int movieRating2 = m2.getRating();

	   /*For descending order*/
            return movieRating2-movieRating1;
        }
    };
}
