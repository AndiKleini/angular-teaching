# Session 3.1 Hard coded html template for home component

Following html can be copied to the html template in [home-component](../src/app/home/home.html) as body of the @for directive that is rendering the room tiles. Then you can start creating proper component for it.

```html
<div class="room-tile">
    <p class="room-tile-title">{{room.name}}</p>
    <h3>{{room.name}}</h3>
    <p class="room-tile-devices">{{ room.devices | listDevices }}</p>
</div>
```