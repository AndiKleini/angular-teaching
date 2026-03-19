import { Injectable, signal } from '@angular/core';
import { Room } from '../entities/room';
import { interval, Observable, Subject } from 'rxjs';
import { HouseEvent } from '../entities/house-event';
import { randomInt } from 'crypto';

const BASE_TEMPERATURE = 23.0;

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

  public baseTemperature = signal<number>(BASE_TEMPERATURE);

  private eventSubject?: Subject<HouseEvent>;

  public houseEvents() : Observable<HouseEvent> {
      if (!this.eventSubject) {
        this.eventSubject = new Subject<HouseEvent>();
        setInterval(() => {
          var randomNumber = Math.floor(1000 * Math.random()) % 3;
          var message = '';
          switch(randomNumber) {
            case 0:
              message = 'Motion detected in the living room.';
              break;
            case 1:
              message = 'Front door opened.';
              break;
            case 2:
              message = 'Smoke detected in the kitchen!';
              break;
          }
          this.eventSubject?.next({ timestamp: new Date(), message: message });
        }, 10000);
      }
      return this.eventSubject.asObservable();
  }
}
