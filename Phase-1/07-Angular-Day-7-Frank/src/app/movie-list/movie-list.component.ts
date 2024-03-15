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

/* This method is called when a word in the header is clicked 
   A value is passed to tell it which field we should sort by
*/

sortListOfMovies(field2Sort : string) {

  // switch is a substitute for a series of if-else
  // switch(variable-name)
  // case say when the variable has this value do some processing

  switch(field2Sort) {    // check the value in field2sort
    case "Title": {       // if it's the word "Title"
                   this.movieService.sortByTitle() // call the sortByTitle() in the movieService
                   break  // leave the switch statement (go to the end of switch and continue)
                  }
    case "Date":  {
                   this.movieService.sortByReleaseYear() 
                   break
                  }
    case "Dir":  { 
                   this.movieService.sortByDirector() 
                   break;
                  }
    default:   {  // if the value does not match any case - generate an error
                throw new Error(`Invalid option (${field2Sort}) sent to sortListOfMovies`);
               }                  
  }
}
}
