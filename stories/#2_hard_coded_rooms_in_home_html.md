# 2.1 Session: Hard coded html for home component

This text contains the hard coded html data that can be used by the [home component](../src/app/home/home.html). Remove the @for block in [home component](../src/app/home/home.html) and copy the html below to the template in [home component](../src/app/home/home.html) instead. Now you are prepared for session 2.1,  which teaches the usage of @for.

<!-- Kitchen -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?kitchen" alt="{{rooms[1].name}}" loading="lazy">
    <h3>🍳{{rooms[1].name}}</h3>
    <p>{{rooms[1].devices.join(', ')}}</p>
</div>
<!-- Living Room -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?living-room" alt="Living Room" loading="lazy">
    <h3>🛋️ Living Room</h3>
    <p>Lights, TV, blinds, climate</p>
</div>
<!-- Sleeping Room -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?bedroom" alt="Sleeping Room" loading="lazy">
    <h3>😴 Sleeping Room</h3>
    <p>Wake-up light, temperature, alarm</p>
</div>
<!-- Toilette -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?toilet" alt="Toilette" loading="lazy">
    <h3>🚽 Toilette</h3>
    <p>Motion sensor, exhaust fan</p>
</div>
    <div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?bathroom" alt="Second Bathroom" loading="lazy">
    <h3>🛁 Second Bathroom</h3>
    <p>Humidity, lighting, ventilation</p>
</div>
<!-- Kitchen -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?kitchen" alt="Kitchen" loading="lazy">
    <h3>🍳 Kitchen</h3>
    <p>Appliances, smoke detector, temperature</p>
</div>
<!-- Living Room -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?living-room" alt="Guest Room" loading="lazy">
    <h3>🛋️ Guest Room</h3>
    <p>Lights, TV, blinds, climate</p>
</div>
<!-- Sleeping Room -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?bedroom" alt="Sleeping Room Children" loading="lazy">
    <h3>😴 Sleeping Room Children</h3>
    <p>Wake-up light, temperature, alarm</p>
</div>
<!-- Toilette -->
<div class="room-tile">
    <img src="https://source.unsplash.com/featured/300x200?toilet" alt="Toilette" loading="lazy">
    <h3>🚽 Toilette 2</h3>
    <p>Motion sensor, exhaust fan</p>
</div>