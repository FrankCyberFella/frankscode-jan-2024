// import gives us access to external features and components
// Most imported name must be included in imports property of @Component
import { Component }       from '@angular/core';
import { RouterOutlet }    from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomeComponent }   from './home/home.component';

@Component({ // Metadata for the component - gives Angular some basic info
  selector: 'app-root',  // This is what is used to include this component 
                         //      on a page 
  standalone: true,      // Added in Angular 17 to allow components to ne independent
  imports: [RouterOutlet, NavBarComponent, HomeComponent], // identifies things used
  templateUrl: './app.component.html',  // Where to find the html file
  styleUrl: './app.component.css'       //       and css file
})
// This defines a module for this component
// export - say let things outside this component have access to it
// class - This is a group of things associated with this component 
export class AppComponent {
 
}
