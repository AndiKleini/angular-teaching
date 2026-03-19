import { Component, computed, EventEmitter, Input, Output, signal } from '@angular/core';
import { Building } from '../services/building';
import { Room } from '../entities/room';
import { ListDevicesPipe } from '../pipes/list-devices';
import { OnInit } from '@angular/core';
import { HouseEvent } from '../entities/house-event';
import { map, Observable, scan } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { GetTimePipe } from "../pipes/get-time";

@Component({
  selector: 'app-room-tile',
  imports: [ListDevicesPipe, AsyncPipe, GetTimePipe],
  templateUrl: './room-tile.html',
  styleUrl: './room-tile.css',
})
export class RoomTile implements OnInit {

    @Output() toggleAlarm = new EventEmitter<number>();

    @Input( { required: true } ) public roomId!: number;
    public room!: Room;

    public baseTemperature = signal(23);
    public temperature = signal(23);
    public divergence = computed(() => this.temperature() - this.baseTemperature());
  
    public houseEvents: Observable<HouseEvent[]> = new Observable<HouseEvent[]>();

    constructor(private buildingSrv: Building) { 
      this.baseTemperature = buildingSrv.baseTemperature;
      this.temperature.set(this.baseTemperature());
      this.houseEvents = this.buildingSrv.houseEvents().
        pipe(
          scan((events: HouseEvent[], event: HouseEvent) => [...events, event], []),
          map((houseEvents: HouseEvent[]) => houseEvents.slice(-3))
        );
    }
    
    ngOnInit(): void {
      this.room = this.buildingSrv.getRooms().find(r => r.id === this.roomId)!;
    }

    public changeTemperature(delta: number): void {
      this.temperature.update(t => t + delta);
    }
}