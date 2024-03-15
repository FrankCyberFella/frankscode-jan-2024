import { Component } from '@angular/core';

@Component({
  selector: 'home-page',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  /* class defines a module which contains data and methods for the component */
  pageTitle = 'Welcome to FSD - Cohort-1-2024 Angular App';
  rightnow  = Date();   // Gets the current date and time as a string

  // substring(start-index, 3-of-chars) - whenever you something called an index it starts at 0
  currentdatetime = Date().substring(0,15); // extract the day name, month, day and year
}
