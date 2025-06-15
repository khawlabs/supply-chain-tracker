import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Shipment } from 'src/app/core/models/shipment.model';
import {GlobalLoadingService} from "src/app/shared/services/global-loading/global-loading.service";
import {ExecutionPlan} from "src/app/core/models/execution-plan.model";
import {ExecutionPlanService} from "src/app/core/services/execution-plan/execution-plan.service";

@Component({
  selector: 'app-shipment-execution-plan-view-dialog',
  templateUrl: './shipment-execution-plan-view-dialog.component.html',
  styleUrls: ['./shipment-execution-plan-view-dialog.component.scss']
})
export class ShipmentExecutionPlanViewDialogComponent implements OnInit {
  plan?: ExecutionPlan;

  constructor(
    public dialogRef: MatDialogRef<ShipmentExecutionPlanViewDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public shipment: Shipment,
    private planService: ExecutionPlanService,
    public globalLoading: GlobalLoadingService
  ) {}

  ngOnInit(): void {
    this.globalLoading.show();

    this.planService.getExecutionPlanByShipmentId(this.shipment.id).subscribe({
      next: plan => this.plan = plan,
      error: err => {
        console.error('Failed to load plan', err);
        this.dialogRef.close();
      },
      complete: () => this.globalLoading.hide()
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}
