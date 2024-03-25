import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StateSealsComponent } from './state-seals.component';

describe('StateSealsComponent', () => {
  let component: StateSealsComponent;
  let fixture: ComponentFixture<StateSealsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StateSealsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StateSealsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
