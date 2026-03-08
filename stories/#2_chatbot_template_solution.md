<div class="message-input-area">
    <input type="text" placeholder="Type your message here..." #messageInput>
    <button (click)="submitMessage(messageInput.value)">Send</button>
</div>