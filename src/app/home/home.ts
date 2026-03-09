import { Component } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';

@Component({
  selector: 'app-home',
  imports: [ListDevicesPipe],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  rooms: Room[];

  constructor(private building: Building) {
    this.rooms = this.building.getRooms();
    console.log(this.rooms);
  }
}
