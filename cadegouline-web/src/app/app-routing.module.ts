import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AnimateurComponent} from "./animateur/animateur.component";
import {HomeComponent} from "./home/home.component";
import {ListalltracksComponent} from "./listalltracks/listalltracks.component";

const routes: Routes = [
  {
    path: 'animateur',
    component: AnimateurComponent
  },
  {
    path: 'listalltracks',
    component: ListalltracksComponent
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: '*',
    component: HomeComponent, pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
