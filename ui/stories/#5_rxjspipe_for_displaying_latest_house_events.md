# Session 5 (implement pipe for extracting last house events)

Remove the rxjs pipe in [home component](../src/app/home/home.ts) and subsitute it by trivial observable that emits an empty array.
´´´javascript
this.houseEvents = of([]);
´´´
This was the code that was removed:
´´´javascript
this.buildingSrv.houseEvents().
      pipe(
        scan((events: HouseEvent[], event: HouseEvent) => [...events, event], []),
        map((houseEvents: HouseEvent[]) => houseEvents.slice(-5))
      );
´´´

Remove the rxjs pipe in [room tile component](../src/app/room-tile/room-tile.ts) and subsitute it by trivial observable that emits an empty array.
´´´javascript
this.houseEvents = of([]);
´´´
This was the code that was removed:
´´´javascript
this.buildingSrv.houseEvents().
      pipe(
        scan((events: HouseEvent[], event: HouseEvent) => [...events, event], []),
        map((houseEvents: HouseEvent[]) => houseEvents.slice(-3))
      );
´´´