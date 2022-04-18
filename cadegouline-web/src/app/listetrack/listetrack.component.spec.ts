import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListetrackComponent } from './listetrack.component';

describe('ListetrackComponent', () => {
  let component: ListetrackComponent;
  let fixture: ComponentFixture<ListetrackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListetrackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListetrackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
