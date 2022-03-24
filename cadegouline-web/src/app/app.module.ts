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

@NgModule({
  declarations: [
    AppComponent,
    AnimateurComponent,
    HomeComponent
  ],
  imports: [
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      MatSliderModule,
      ReactiveFormsModule,
      HttpClientModule,
      ToastrModule.forRoot()
  ],
  exports: [],
  providers: [
      AnimateurService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
