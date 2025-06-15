import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanCreateShipmentDialogComponent } from './plan-create-shipment-dialog.component';

describe('PlanCreateShipmentDialogComponent', () => {
  let component: PlanCreateShipmentDialogComponent;
  let fixture: ComponentFixture<PlanCreateShipmentDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlanCreateShipmentDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlanCreateShipmentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
