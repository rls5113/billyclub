import { Component } from '@angular/core';
import {PointsEvent} from "./pointsEvent";
import {PointsEventService} from "./points-event.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public pointsEvents: PointsEvent[] | undefined;

  constructor(private pointsEventService: PointsEventService) {  }

  ngOnInit() {
    this.getPointsEvents();
  }
  public getPointsEvents(): void {
    this.pointsEventService.getPointsEvents().subscribe(
      (response: PointsEvent[]) => {
        this.pointsEvents = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
