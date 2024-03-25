import { Component }     from '@angular/core';
import { MoviesInfo }    from '../moviesInfo';  // include the MoviesInfo interface
import { FormsModule }   from '@angular/forms';
import { MoviesService } from '../movies.service';
import { CommonModule }  from '@angular/common';
import { Router }        from '@angular/router';

@Component({
  selector: 'app-add-movie',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-movie.component.html',
  styleUrl: './add-movie.component.css'
})
export class AddMovieComponent {
// Uses Dependency Injection to define the variable movieService,
//        create an instanceof MoviesService and assign that to the movieService
// also Dependency Injects the Router into the router variable (so we can go to different pages)
constructor(private movieService  : MoviesService, 
            private router        : Router) {}

// Define a place to hold a new movie when entered by the user 
// Data from the web page will be used to fill in the values a new movie (two-way bind)
//     variable : data-type - using the interface as a data-type
public newMovie : MoviesInfo =  {
                                  title : "Type a title",
                                  releaseYear: new Date().getFullYear(), // initialize to this year
                                  director: ""
                                };

// This method method called when the submit is clicked in the html form
// it receives a new movie object with values entered on form from the user
// the it tells the router to go the /movies page
//funcname(parameter : data-type) - using interface as a data-type
  addMovie(newMovie  : MoviesInfo) {
    console.table(newMovie);
    this.movieService.addMovies(newMovie) // call the service to add the newMovie to th data source
    this.router.navigate(['/movies']) // Tell the router to go to the /movies page
  }
  cancelButtonClicked() {
    this.router.navigate(['/movies']) // Tell the router to go to the /movies page
  }
}


