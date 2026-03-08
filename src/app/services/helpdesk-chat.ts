import { Injectable } from '@angular/core';
import { ChatMessage } from '../entities/chat-message';
import { delay, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HelpdeskChat {
  public onMessageSubmitted: (message: ChatMessage) => void = () => {};
  public history: ChatMessage[] = [
      { id: 1, sender: 'User', message: 'Hello, I need help with my smart home.' },
      { id: 2, sender: 'Support', message: 'Sure! What seems to be the issue?' },
      { id: 3, sender: 'User', message: 'My living room lights are not responding.' },
      { id: 4, sender: 'Support', message: 'Have you tried turning them off and on again?' },
    ]
  public getChatHistory(): ChatMessage[] {
    return this.history;
  }
  public submitMessage(message: string): void {
    this.history.push({ id: this.history.length + 1, sender: 'User', message });
    
    const supportResponse = of(this.generateSupportResponse(message)).pipe(delay(5000));
    supportResponse.subscribe(response => {
      this.history.push({ id: this.history.length + 1, sender: 'Support', message: response });
      this.onMessageSubmitted({ id: this.history.length, sender: 'Support', message: response });
    });
  }
  private generateSupportResponse(userMessage: string): string {
    console.log('Generating support response for user message:', userMessage);
    if (userMessage.toLowerCase().includes('light')) {
      return 'Please check if the light bulbs are properly screwed in and if the power is on.';
    } else if (userMessage.toLowerCase().includes('thermostat')) {
      return 'Try resetting your thermostat by unplugging it for 30 seconds and plugging it back in.';
    } else {
      return 'Thank you for your message. We will get back to you shortly.';
    }
  }
}
