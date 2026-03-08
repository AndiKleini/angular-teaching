# Tasks for mob programming

This page summarizes the tasks that can be accomplished by mob programming sessions in a bigger group.
The goal is to become confident with the angular framework.

## 1. Add components and basic routing
- Move static footer into separate component
- Create NotFound Page by creating separate NotFound control
- Add route for NotFound page to cover all paths
- Add route link back from afore created NotFound page to the home control

## 2. Databinding, Templates 
Preparation:
- Remove @for in [home.html](../src/app/home/home.html) and replace it with hard coded room html [here](./#2_hard_coded_rooms_in_home_html.md).
- Remove the whole javascript code from home controller [home](../src/app/home/home.ts)
- Remove code in transform operation of pipe [listDevices](../src/app/pipes/list-devices.ts)
- Remove the data binding of Send button and input control in [contact component](../src/app/contact/contact.html).
Tasks:
- Inject service building in home controller
- Bind the array of rooms emitted by building.getRooms() into the template of the home component
    - Start with the first room on the top
    - Create repeating template for rooms
- create display for empty list of rooms under usage of @empty
- create pipe for displaying list of devices
- fix the broken chatbot service by establishment of the data-binding in contact component.

## 3. Parent -> Child
- create a room-tile component that gets the roomId from the template
- activate alarm for the whole building by a button click on the room tile use Output capabilities.