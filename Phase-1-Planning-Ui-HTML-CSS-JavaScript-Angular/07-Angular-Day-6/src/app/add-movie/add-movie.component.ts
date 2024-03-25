import { Component }     from '@angular/core';
import { MoviesInfo }    from '../moviesInfo';
import { FormsModule }   from '@angular/forms';
import { MoviesService } from '../movies.service';
import { CommonModule }  from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-movie',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-movie.component.html',
  styleUrl: './add-movie.component.css'
})
export class AddMovieComponent {

constructor(private movieService  : MoviesService, 
  private router: Router) {}

public newMovie : MoviesInfo =  {
                                  title : "",
                                  releaseYear: new Date().getFullYear(),
                                  director: ""
                                };


addMovie(newMovie : MoviesInfo) {
  console.table(newMovie);
  this.movieService.addMovies(newMovie)
  this.router.navigate(['/movies'])
}
cancelButtonClicked() {
  this.router.navigate(['/movies'])
}
}


