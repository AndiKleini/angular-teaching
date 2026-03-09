  Code of room_tile component:
  @Input( {required: true} ) roomId!: number;

  public room!: Room;

  constructor(private buildingSrv: Building) {}

  ngOnInit(): void {
    this.room = this.buildingSrv.getRooms().find(r => r.id === this.roomId)!;
  }

Template of room-tile component
<div class="room-tile">
    <p class="room-tile-title">{{room.name}}</p>
    <h3>{{room.name}}</h3>
    <p class="room-tile-devices">{{ room.devices | listDevices }}</p>
</div>

Template of home.html when integrating the room component

        @for(item of rooms; track $index) {
            <app-room-tile [roomId]="item.id"></app-room-tile>
        }