import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from "../../core/guards/auth.guard";

import {
  ExecutionPlanListComponent
} from "src/app/features/execution-plan/components/execution-plan-list/execution-plan-list.component";

const routes: Routes = [
  {
    path: '',
    component: ExecutionPlanListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],  // Use RouterModule.forChild for feature modules
  exports: [RouterModule]
})
export class ExecutionPlanRoutingModule {}
