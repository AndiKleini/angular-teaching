import { Component, ChangeDetectorRef } from '@angular/core';
import { HelpdeskChat } from '../services/helpdesk-chat';
import { ChatMessage } from '../entities/chat-message';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-contact',
  imports: [ NgClass ],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
  standalone: true,
})
export class Contact {
  public chatHistory: ChatMessage[] = [];
  constructor(private chatSrv: HelpdeskChat, private cdr: ChangeDetectorRef) {
    this.chatHistory = chatSrv.getChatHistory();
    chatSrv.onMessageSubmitted = (message: ChatMessage) => {
      this.chatHistory = chatSrv.getChatHistory();
      this.cdr.markForCheck();
    }
  }
  public submitMessage(message: string): void { 
    console.log('Submitting message:', message);

    this.chatSrv.submitMessage(message);
  }
}