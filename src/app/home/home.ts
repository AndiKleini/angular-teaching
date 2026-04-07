import { Component, computed, signal } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { RoomTile } from "../room-tile/room-tile";
import { AsyncPipe, NgClass } from "@angular/common";
import { GetTimePipe } from "../pipes/get-time";
import { map, Observable, scan } from 'rxjs';
import { HouseEvent } from '../entities/house-event';

@Component({
  selector: 'app-home',
  imports: [RoomTile, NgClass, GetTimePipe, AsyncPipe],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  rooms: Room[];
  isAlarmActive: boolean = false;

  public baseTemperature = signal(0);

  public houseEvents: Observable<HouseEvent[]> = new Observable<HouseEvent[]>();
  
  constructor(private buildingSrv: Building) {
    this.rooms = this.buildingSrv.getRooms();
    this.baseTemperature = this.buildingSrv.baseTemperature;
    this.houseEvents = this.buildingSrv.houseEvents().
      pipe(
        scan((events: HouseEvent[], event: HouseEvent) => [...events, event], []),
        map((houseEvents: HouseEvent[]) => houseEvents.slice(-5))
      );
  }

  public toggleAlarmGlobally() {
    this.isAlarmActive = !this.isAlarmActive;
  }

  public toggleAlarm(roomId: number): void {
    this.toggleAlarmGlobally();
  }

  public changeTemperature(diff: number) {
    this.baseTemperature.update(t => t + diff);
  }
}
