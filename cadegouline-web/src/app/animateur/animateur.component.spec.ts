import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimateurComponent } from './animateur.component';

describe('AnimateurComponent', () => {
  let component: AnimateurComponent;
  let fixture: ComponentFixture<AnimateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
