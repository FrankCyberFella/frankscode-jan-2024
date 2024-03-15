import { CommonModule }  from '@angular/common';
import { Component }     from '@angular/core';
import { MoviesInfo }    from '../moviesInfo';     // access the MoviesList interface
import { MoviesService } from '../movies.service'; // access the MoviesServices service
import { RouterLink }    from '@angular/router';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './movie-list.component.html',
  styleUrl: './movie-list.component.css'
})
export class MovieListComponent {

// This will hold the data for the movies to be displayed
// the data will come from a call to a service
// it starts out as an empty array  
//     var-name   : data-type
public moviesList : MoviesInfo [] ; // This is an array of MoviesInfo objects

// constructor is used to initialize data in the component
//
// This style of constructor will define the variable, create a MoviesService object
//                                       and assign that object to the variable
//                                       using Dependency Injection
//
// Dependency Injection is the automatic creation of variables and objects required
//
// Dependeny Injection decouples the service from the code
// (makes it much easier to change the service)
//   
// without Dependency Injection the code would look like this:
//
//     private movieService;   // define a variable to hold the service
//
//     constructor() {
//                    movieService = new MoviesServices();// create the service
//                   }                                    //    and assign it to the variable
//                              
constructor(private movieService  : MoviesService ) {
  // Call the service method to send back the current list of movies from the data source
  this.moviesList = movieService.getMoviesList(); // Initialize our moviesList from service
  //  1. movieService.getMoviesList() - go to the movieService and run the method getMoviesList
  //  2. = - take with the method returns and assign it to (store it in)
} //  3. this.moviesList is assigned the data from step 2

sortListOfMovies(field2Sort : string) {

  switch(field2Sort) {
    case "Title": { 
                   this.movieService.sortByTitle() 
                   break
                  }
    case "Date":  {
                   this.movieService.sortByReleaseYear() 
                   break
                  }
    case "Dir":  { 
                   this.movieService.sortByDirector() 
                   break;
                  }
    default:   {
                throw new Error(`Invalid option (${field2Sort}) sent to sortListOfMovies`);
               }                  
  }
}
}
