import { TestBed } from '@angular/core/testing';

import { LeafletFacade } from '../services/leaflet-facade';

describe('LeafletFacade', () => {
  let service: LeafletFacade;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeafletFacade);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
