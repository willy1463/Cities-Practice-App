import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CitiesComponent } from './cities/cities.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: 'cities', component: CitiesComponent },
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
