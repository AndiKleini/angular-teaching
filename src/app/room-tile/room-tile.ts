import { Component, computed, EventEmitter, Input, Output, signal } from '@angular/core';
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
    public baseTemperature = signal(23);
    public temperature = signal(23);
    public divergence = computed(() => this.temperature() - this.baseTemperature());
  
    constructor(private buildingSrv: Building) { 
      this.baseTemperature = buildingSrv.baseTemperature;
      this.temperature.set(this.baseTemperature());
    }
    
    ngOnInit(): void {
      this.room = this.buildingSrv.getRooms().find(r => r.id === this.roomId)!;
    }

    public changeTemperature(delta: number): void {
      this.temperature.update(t => t + delta);
    }
}
