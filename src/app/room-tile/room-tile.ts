import { Component, Input } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-room-tile',
  imports: [ ListDevicesPipe],
  templateUrl: './room-tile.html',
  styleUrl: './room-tile.css',
})
export class RoomTile implements OnInit {

    @Input( { required: true } ) public roomId!: number;
    public room!: Room;
    constructor(private buildingSrv: Building) { }
    
    ngOnInit(): void {
      console.log("RoomTile ngOnInit with roomId", this.roomId);
      this.room = this.buildingSrv.getRooms().find(r => r.id === this.roomId)!;
      console.log("RoomTile found room", this.room);
    }
}
