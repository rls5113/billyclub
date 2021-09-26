import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {PointsEventService} from "./points-event.service";
import { PointsEventComponent } from './points-event/points-event.component';
import { PlayerComponent } from './player/player.component';

@NgModule({
  declarations: [
    AppComponent,
    PointsEventComponent,
    PlayerComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [PointsEventService],
  bootstrap: [AppComponent]
})
export class AppModule { }
