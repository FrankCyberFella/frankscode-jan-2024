import { Routes }               from '@angular/router';
import { HomeComponent }        from './home/home.component';
import { SampleFormComponent }  from './sample-form/sample-form.component';
import { StateSealsComponent }  from './state-seals/state-seals.component';
import { LearnerListComponent } from './learner-list/learner-list.component';

export const routes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full' }  ,
    {path: 'learners',   component: LearnerListComponent},
    {path: 'home',       component: HomeComponent}       ,
    {path: 'persinfo',   component: SampleFormComponent} ,
    {path: 'stateseals', component: StateSealsComponent}
];
