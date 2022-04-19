import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { AnimateurComponent } from './animateur/animateur.component';
import { HomeComponent } from './home/home.component';
import { AnimateurService } from "./services/animateur.service";
import { HttpClientModule } from "@angular/common/http";
import { ToastrModule } from "ngx-toastr";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { ListalltracksComponent } from './listalltracks/listalltracks.component';
import { ListAllTracksService } from "./services/list-all-tracks.service";
import { RouterModule } from "@angular/router";
import {MatTableModule} from "@angular/material/table";

@NgModule({
  declarations: [
    AppComponent,
    AnimateurComponent,
    HomeComponent,
    ListalltracksComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RouterModule,
        BrowserAnimationsModule,
        MatSliderModule,
        ReactiveFormsModule,
        HttpClientModule,
        ToastrModule.forRoot(),
        MatProgressSpinnerModule,
        MatTableModule
    ],
  exports: [],
  providers: [
      AnimateurService,
      ListAllTracksService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
