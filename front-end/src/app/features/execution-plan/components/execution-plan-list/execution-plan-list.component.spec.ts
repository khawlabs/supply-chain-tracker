import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutionPlanListComponent } from './execution-plan-list.component';

describe('ExecutionPlanListComponent', () => {
  let component: ExecutionPlanListComponent;
  let fixture: ComponentFixture<ExecutionPlanListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExecutionPlanListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutionPlanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
