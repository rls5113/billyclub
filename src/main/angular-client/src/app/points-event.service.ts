import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PointsEvent} from "./pointsEvent";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PointsEventService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getPointsEvents(): Observable<PointsEvent[]> {
    return this.http.get<PointsEvent[]>(`${this.apiServerUrl}/api/v1/pointsEvent/`);
  }
  public addPointsEvents(pointsEvent: PointsEvent): Observable<PointsEvent> {
    return this.http.post<PointsEvent>(`${this.apiServerUrl}/api/v1/pointsEvent/`, pointsEvent);
  }
  public updatePointsEvents(pointsEvent: PointsEvent): Observable<PointsEvent> {
    return this.http.put<PointsEvent>(`${this.apiServerUrl}/api/v1/pointsEvent/`, pointsEvent);
  }
  public deletePointsEvents(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/api/v1/pointsEvent/${id}`);
  }

}
