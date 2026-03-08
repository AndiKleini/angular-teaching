import { Component } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';

@Component({
  selector: 'app-home',
  imports: [ ListDevicesPipe ],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  public rooms: Room[] = [];
  constructor(buildingSrv: Building) {
    this.rooms = buildingSrv.getRooms();
    console.log('Home component initialized with rooms:', this.rooms);
  }
}
