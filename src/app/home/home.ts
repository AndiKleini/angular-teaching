import { Component } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';
import { RoomTile } from "../room-tile/room-tile";

@Component({
  selector: 'app-home',
  imports: [ListDevicesPipe, RoomTile],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  rooms: Room[];

  constructor(private buildingSrv: Building) {
    this.rooms = this.buildingSrv.getRooms();
    console.log(this.rooms);
  }
}
