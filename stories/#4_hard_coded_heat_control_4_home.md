# Session 4 Hard coded html template for home component

Following html can be copied to the html template in [home.html](../src/app/home/home.html) instead of the data bound heat control div block (compare **<div class="thermostat-control">**).

```html
<div class="thermostat-control">
    <div class="dial">
        <div class="inner-circle">
            <div id="status" class="heating">Heating</div>
            <div class="target-control">
                <button (click)="changeTemperature(-0.5)">-</button>
                <span id="target-temp">23</span>
                <button (click)="changeTemperature(0.5)">+</button>
            </div>
        </div>
    </div>
</div>
```

Replace also the typescript code in [home.ts](../src/app/room-tile/room-tile.ts) by 

```javascript
export class Home {

  rooms: Room[];
  isAlarmActive: boolean = false;

  constructor(private buildingSrv: Building) {
    this.rooms = this.buildingSrv.getRooms();
  }

  public toggleAlarmGlobally() {
    this.isAlarmActive = !this.isAlarmActive;
  }

  public toggleAlarm(roomId: number): void {
    console.log(`Alarm toggled for room with ID: ${roomId}`);
    this.toggleAlarmGlobally();
  }

  public changeTemperature(diff: number) {
    
  }
}
```