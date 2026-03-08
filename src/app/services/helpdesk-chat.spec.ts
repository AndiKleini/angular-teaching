import { TestBed } from '@angular/core/testing';

import { HelpdeskChat } from './helpdesk-chat';

describe('HelpdeskChat', () => {
  let service: HelpdeskChat;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HelpdeskChat);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
