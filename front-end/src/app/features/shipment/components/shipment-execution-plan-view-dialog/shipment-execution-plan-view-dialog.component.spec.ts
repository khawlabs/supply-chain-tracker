import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipmentExecutionPlanViewDialogComponent } from './shipment-execution-plan-view-dialog.component';

describe('ShipmentExecutionPlanViewDialogComponent', () => {
  let component: ShipmentExecutionPlanViewDialogComponent;
  let fixture: ComponentFixture<ShipmentExecutionPlanViewDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShipmentExecutionPlanViewDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShipmentExecutionPlanViewDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
