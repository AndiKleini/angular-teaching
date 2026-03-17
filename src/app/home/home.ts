import { Component, computed, signal } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';
import { RoomTile } from "../room-tile/room-tile";
import { NgClass } from "@angular/common";
import { sign } from 'node:crypto';

@Component({
  selector: 'app-home',
  imports: [ RoomTile, NgClass],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  rooms: Room[];
  isAlarmActive: boolean = false;

  public baseTemperature = signal(0);
  
  constructor(private buildingSrv: Building) {
    this.rooms = this.buildingSrv.getRooms();
    this.baseTemperature = this.buildingSrv.baseTemperature;
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
