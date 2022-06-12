import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

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
import { EmissionComponent } from './emission/emission.component';
import {ProgramService} from "./services/program.service";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule} from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    AnimateurComponent,
    HomeComponent,
    ListalltracksComponent,
    EmissionComponent
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
        MatTableModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatInputModule,
        MatNativeDateModule,
        FormsModule
    ],
  exports: [],
  providers: [
      AnimateurService,
      ListAllTracksService,
      ProgramService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
