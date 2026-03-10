import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomTile } from './room-tile';

describe('RoomTile', () => {
  let component: RoomTile;
  let fixture: ComponentFixture<RoomTile>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomTile]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomTile);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
