/* define the URL path-COmponent connection for the application  
    this tells angular router which component to switch for URL path
*/

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

// Be sure we have imported all comonents we are connectin to a URL path
import { SampleFormComponent }  from './sample-form/sample-form.component';
import { StateSealsComponent }  from './state-seals/state-seals.component';
import { HomeComponent }        from './home/home.component';
import { LearnerListComponent } from './learner-list/learner-list.component';

// the routes attribute defines the URL path-Component connection
// it is an array of objects with each object specifing a URL path
//       and the component its connected to
const routes: Routes = [
  // if there is no URL path use (redirect the /home path
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  // if the URL path is /home switch to the HomeComponent
  { path: 'home', component: HomeComponent },
  // if the URL path is /persinfo switch to the SampleFormComponent
  { path: 'persinfo', component: SampleFormComponent },
  { path: 'learners', component: LearnerListComponent},
  // if the URL path is /stateseals switch to the StateSealsComponent
  { path: 'stateseals', component: StateSealsComponent }
 ];

@NgModule({
  declarations: [],
  // we need to the RouterModule the name of the array of paths
  imports: [RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}






