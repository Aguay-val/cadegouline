import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListalltracksComponent } from './listalltracks.component';

describe('ListalltracksComponent', () => {
  let component: ListalltracksComponent;
  let fixture: ComponentFixture<ListalltracksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListalltracksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListalltracksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
