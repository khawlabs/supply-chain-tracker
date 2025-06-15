import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { catchError, Observable, of } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';

import { GlobalLoadingService } from 'src/app/shared/services/global-loading/global-loading.service';
import { GlobalSearchService } from 'src/app/shared/services/global-search/global-search.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { ExecutionPlan } from 'src/app/core/models/execution-plan.model';
import {ExecutionPlanService} from "src/app/core/services/execution-plan/execution-plan.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-execution-plan-list',
  templateUrl: './execution-plan-list.component.html',
  styleUrls: ['./execution-plan-list.component.scss']
})
export class ExecutionPlanListComponent {
  searchControl = new FormControl('');
  executionPlans$!: Observable<ExecutionPlan[]>;

  constructor(
    private planService: ExecutionPlanService,
    private searchService: GlobalSearchService,
    public globalLoading: GlobalLoadingService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.loadPlans();
  }

  loadPlans(): void {
    const allPlans$ = this.planService.getAllExecutionPlans().pipe(
      catchError(err => {
        console.error('Error loading plans:', err);
        this.snackBar.open('❌ Failed to load execution plans', 'Close', { duration: 3000 });
        return of([]);
      })
    );

    this.executionPlans$ = this.searchService.filterList(allPlans$, this.searchControl).pipe(
      tap(res=>console.log('!!!!!!!!!!: ',res))
    );
  }

  confirmDelete(planId: number): void {
    this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'Delete Execution Plan',
        message: `Are you sure you want to delete plan #${planId}?`
      }
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;

      this.globalLoading.show();

      this.planService.deleteExecutionPlan(planId).subscribe({
        next: () => {
          this.snackBar.open('✅ Execution plan deleted', 'Close', { duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top' });
          this.loadPlans();
        },
        error: err => {
          console.error('Delete failed:', err);
          this.snackBar.open('❌ Failed to delete plan', 'Close', { duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'});
        },
        complete: () => this.globalLoading.hide()
      });
    });
  }
}
