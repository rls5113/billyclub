import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PlayerComponent } from './player/player.component';
import { HeaderComponent } from './header/header.component';
import { PointsEventListComponent } from './points-event-list/points-event-list.component';
import {HttpClientModule} from "@angular/common/http";
import {PointsEventService} from "./points-event-list/points-event.service";

@NgModule({
  declarations: [
    AppComponent,
    PlayerComponent,
    HeaderComponent,
    PointsEventListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [PointsEventService],
  bootstrap: [AppComponent]
})
export class AppModule { }
