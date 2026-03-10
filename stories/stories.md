# Tasks for mob programming

This page summarizes the tasks that can be accomplished by mob programming sessions in a bigger group. For each session you have to conduct preparations and then the described tasks.
The goal is to become confident with the angular framework.

## Session 1, Add components and basic routing

**Preparations:**
- remove component [footer](../src/app/footer/) and [not-found](.src/app/footer) and all of their dependents like imports and routes.

### Sessions 1

- Move static footer into separate component ([compare](./#1_hard_coded_footer.md))
- Create NotFound Page by creating separate NotFound control
- Add route for NotFound page to cover all paths
- Add route link back from afore created NotFound page to the home control

## Session 2, Databinding, Templates 

**Preparations:**
- Remove @for in [home.html](../src/app/home/home.html) and replace it with hard coded room html [here](./#2_hard_coded_rooms_in_home_html.md).
- Remove the whole javascript code from home controller [home](../src/app/home/home.ts)
- Remove code in transform operation of pipe [listDevices](../src/app/pipes/list-devices.ts)
- Remove the data binding of Send button and input control in [contact component](../src/app/contact/contact.html). A solution can be found [here](./#2_chatbot_template_solution.md)

### Sessions 2

**Session 2.1 (home component refactoring):**

- Inject service building in home controller
- Bind the array of rooms emitted by building.getRooms() into the template of the home component
    - Start with the first room on the top
    - Create repeating template for rooms
- create display for empty list of rooms under usage of @empty
- create pipe for displaying list of devices
**Session 2.2 (fix chatbot service):**
- fix the broken chatbot service by establishment of the data-binding in contact component.

## Session 3 Input and Output 

**Preparations**
- Remove the component [room-tile](../src/app/room-tile/) and all it'S dependents. The html template of [home-component](../src/app/home/home.html) can be populated with the html template code [here](..)
- Remove javascript method **toggleAlram(roomId: number)a: void** from [home-component](../src/app/home/home.ts)

**Session 3.1 (introduce room-tile component)**

- create a room-tile component that gets the roomId from the template
- activate alarm for the whole building by a button click on the room tile use Output capabilities.

**Session 3.2 (create alarm buttons in room tiles)**

- create an activate/deactivate alarm button in the room-tile. Whenever this button is clicked the alarm is toggled. The state needs to be propagated to the main component which already has this alarm toggle capabilities.