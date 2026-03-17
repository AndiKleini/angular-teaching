import { Injectable, signal } from '@angular/core';
import { Room } from '../entities/room';

@Injectable({
  providedIn: 'root',
})
export class Building {
  public getRooms() : Room[] {
    return [
      { id: 1, name: 'Living Room', devices: ['TV', 'Lamp', 'Thermostat'] },
      { id: 2, name: 'Kitchen', devices: ['Fridge', 'Oven', 'Dishwasher'] },
      { id: 3, name: 'Bedroom', devices: ['Bed', 'Lamp', 'Alarm Clock'] },
    ];
  }

  public baseTemperature = signal<number>(23.0);
}
