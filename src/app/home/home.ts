import { Component } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';
import { RoomTile } from "../room-tile/room-tile";
import { NgClass } from "@angular/common";

@Component({
  selector: 'app-home',
  imports: [ RoomTile, NgClass],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  rooms: Room[];
  isAlarmActive: boolean = false;

  constructor(private buildingSrv: Building) {
    this.rooms = this.buildingSrv.getRooms();
    console.log(this.rooms);
  }

  public toggleAlarmGlobally() {
    this.isAlarmActive = !this.isAlarmActive;
  }

  public toggleAlarm(roomId: number): void {
    console.log(`Alarm toggled for room with ID: ${roomId}`);
    this.toggleAlarmGlobally();
  }
}
