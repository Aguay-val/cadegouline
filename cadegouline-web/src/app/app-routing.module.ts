import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AnimateurComponent} from "./animateur/animateur.component";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {
    path: 'animateur',
    component: AnimateurComponent
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
