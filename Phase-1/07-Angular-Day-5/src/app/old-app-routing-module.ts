/* Define Routing information */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SampleFormComponent } from './sample-form/sample-form.component';
import { StateSealsComponent } from './state-seals/state-seals.component';
import { HomeComponent }       from './home/home.component';
const routes: Routes = [
 { path: '', redirectTo: '/home', pathMatch: 'full' },
 { path: 'home', component: HomeComponent },
 { path: 'persinfo', component: SampleFormComponent },
 { path: 'stateseals', component: StateSealsComponent },
];

@NgModule({
 imports: [RouterModule.forRoot(routes)],
 exports: [RouterModule]
})
export class AppRoutingModule {
    
 }