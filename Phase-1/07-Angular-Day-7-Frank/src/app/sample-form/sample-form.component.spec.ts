import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleFOrmComponent } from './sample-form.component';

describe('SampleFOrmComponent', () => {
  let component: SampleFOrmComponent;
  let fixture: ComponentFixture<SampleFOrmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SampleFOrmComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SampleFOrmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
