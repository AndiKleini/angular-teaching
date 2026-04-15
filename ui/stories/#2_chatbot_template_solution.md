# Session 2 Solution for Fix Chatbot

Establish the data-binding in the [control-component](../src/app/contact/contact.html) by setting the template variable to input and bing the proper submitMessage method to the click.
After this change the messages can be send to the service.

```html
<div class="message-input-area">
    <input type="text" placeholder="Type your message here..." #messageInput>
    <button (click)="submitMessage(messageInput.value)">Send</button>
</div>
```