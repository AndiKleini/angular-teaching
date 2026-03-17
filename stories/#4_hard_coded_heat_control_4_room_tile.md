# Session 4 Hard coded html template for room-tile component

Following html can be copied to the html template in [room-tile.html](../src/app/room-tile/room-tile.html) instead of the data bound heat control div block (compare **<div class="thermostat-control">**).

```html
<div class="thermostat-control">
    <div class="dial">
        <div class="inner-circle">
            <span id="current-temp">23</span>
            <div id="status" class="heating">Heating</div>
            <div class="target-control">
                <button (click)="changeTemperature(-0.5)">-</button>
                <span id="target-temp">23</span>
                <button (click)="changeTemperature(0.5)">+</button>
            </div>
            <span>Divergence:</span>
        </div>
    </div>
</div>
```

Replace also the typescript code in [room-tile.ts](../src/app/room-tile/room-tile.ts) by 

```javascript
export class RoomTile implements OnInit {

    @Output() toggleAlarm = new EventEmitter<number>();

    @Input( { required: true } ) public roomId!: number;
    public room!: Room;
  
    constructor(private buildingSrv: Building) { }
    
    ngOnInit(): void {
      this.room = this.buildingSrv.getRooms().find(r => r.id === this.roomId)!;
    }

    public changeTemperature(delta: number): void {
      
    }
}
```