import { Component, EventEmitter, Input, Output } from '@angular/core';
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

    @Output() toggleAlarm = new EventEmitter<number>();

    @Input( { required: true } ) public roomId!: number;
    public room!: Room;

    constructor(private buildingSrv: Building) { }
    
    ngOnInit(): void {
      this.room = this.buildingSrv.getRooms().find(r => r.id === this.roomId)!;
    }
}
