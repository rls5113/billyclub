import { Component, OnInit } from '@angular/core';
import {PointsEvent} from "./pointsEvent";
import {PointsEventService} from "./points-event.service";
import {Observable} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-points-event-list',
  templateUrl: './points-event-list.component.html',
  styleUrls: ['./points-event-list.component.css']
})
export class PointsEventListComponent implements OnInit {

  public pointsEvents: PointsEvent[] = [];

  constructor(private pointsEventService: PointsEventService) { }

  ngOnInit(): void {
    this.getPointsEvents();
  }

  public getPointsEvents(): void  {
    this.pointsEventService.getPointsEvents().subscribe(
      (response: PointsEvent[]) => {
        this.pointsEvents = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
