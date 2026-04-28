# API-1 session add UserController

**Preparation**
Prepare the session 1 by modification of the application following [this](#api-1-session-add-usercontroller);

**API session 1**
- Create UserController
- Create GetMapping in UserController
- Implement Error code 404
- Create UserService
- Move the logic of the get method to the service
- Implement create user with Post mapping and creation method in UserService
- Implement update user with Put mapping and update method in UserService
- Implement delete user with Delete mapping and delete method in UserService

# API-2 session implement authentication

**Preparation**
Prepare the session by modification of the application following [this].

**API session 2**
Implement the afore removed methods.

# API-3 session load password from database

**Preparation**
 - remove implementation of method authenticate and createCredentials from [IAuthenticationService](../src/main/java/at/smarthome/service/IAuthenticationService.java) in [AuthenticationService](../src/main/java/at/smarthome/service/impl/AuthenticationService.java). Substitute the code with throwing a NotImplementedException.

**API session 3**
- Implement the afore removed methods