import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, Observable, of } from 'rxjs';

import { ShipmentService } from 'src/app/core/services/shipment/shipment.service';
import { GlobalSearchService } from 'src/app/shared/services/global-search/global-search.service';
import { GlobalLoadingService } from 'src/app/shared/services/global-loading/global-loading.service';

import { Shipment } from 'src/app/core/models/shipment.model';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import {ExecutionPlanService} from "src/app/core/services/execution-plan/execution-plan.service";
import {
  PlanCreateShipmentDialogComponent
} from "src/app/features/shipment/components/plan-create-shipment-dialog/plan-create-shipment-dialog.component";
import {ExecutionPlanModule} from "src/app/features/execution-plan/execution-plan.module";

@Component({
  selector: 'app-shipment-list',
  templateUrl: './shipment-list.component.html',
  styleUrls: ['./shipment-list.component.scss']
})
export class ShipmentListComponent {
  shipments$!: Observable<Shipment[]>;
  searchControl = new FormControl('');

  constructor(
    private shipmentService: ShipmentService,
    private executionPlanService: ExecutionPlanService,
    private searchService: GlobalSearchService,
    public globalLoading: GlobalLoadingService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {
    this.loadShipments();
  }

  loadShipments(): void {
    const allShipments$ = this.shipmentService.getAllShipments().pipe(
      catchError(err => {
        console.error('Failed to fetch shipments:', err);
        return of([]);
      })
    );
    this.shipments$ = this.searchService.filterList(allShipments$, this.searchControl);
  }

  openPlanDialog(shipment: Shipment): void {
    const dialogRef = this.dialog.open(PlanCreateShipmentDialogComponent, {
      width: '400px',
      data: shipment
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!result) return;

      this.globalLoading.show();
      console.log('!!!!: ',result)

      this.executionPlanService.createExecutionPlan(result).subscribe({
        next: () => {
          this.snackBar.open('✅ Execution plan created!', 'Close', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
          });
          this.loadShipments(); // refresh after creation
        },
        error: err => {
          console.error('Plan creation failed:', err);
          this.snackBar.open('❌ Failed to create plan.', 'Close', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
          });
        },
        complete: () => this.globalLoading.hide()
      });
    });
  }

  confirmDelete(shipment: Shipment): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'Delete Shipment',
        message: `Are you sure you want to delete shipment "${shipment.shipmentId}"?`
      }
    });

    dialogRef.afterClosed().subscribe(confirmed => {
      if (!confirmed) return;

      this.globalLoading.show();

      this.shipmentService.deleteShipmentById(shipment.id).subscribe({
        next: () => {
          this.snackBar.open('🗑️ Shipment deleted.', 'Close', { duration: 3000 });
          this.loadShipments(); // refresh after delete
        },
        error: err => {
          console.error('Delete failed:', err);
          this.snackBar.open('❌ Delete failed.', 'Close', { duration: 3000 });
        },
        complete: () => this.globalLoading.hide()
      });
    });
  }
}
