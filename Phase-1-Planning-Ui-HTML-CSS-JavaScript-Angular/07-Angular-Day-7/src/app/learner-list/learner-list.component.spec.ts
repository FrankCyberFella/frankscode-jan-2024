import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LearnerListComponent } from './learner-list.component';

describe('LearnerListComponent', () => {
  let component: LearnerListComponent;
  let fixture: ComponentFixture<LearnerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LearnerListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LearnerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
