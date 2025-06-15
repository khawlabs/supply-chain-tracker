import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ErrorComponent} from "./shared/components/error/error.component";
import {PageNotFoundComponent} from "./shared/components/page-not-found/page-not-found.component";
import {AuthGuard} from "./core/guards/auth.guard";

const routes: Routes = [
  {
    // Lazy load ExecutionPlanModule
    path: 'execution-plans',
    loadChildren: () => import('./features/execution-plan/execution-plan.module').then(m => m.ExecutionPlanModule),
    canActivateChild: [AuthGuard]
  },
  {
    // Lazy load ExecutionPlanModule
    path: 'shipments',
    loadChildren: () => import('./features/shipment/shipment.module').then(m => m.ShipmentModule),
    canActivateChild: [AuthGuard]
  },
  {
    // Default route
    path: '',
    redirectTo: 'execution-plans',
    pathMatch: 'full'
  },
  {
    path: 'error',
    component: ErrorComponent
  },
  {
    // Redirect all unmatched routes to PageNotFoundComponent
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  // Configure root routes
  exports: [RouterModule]
})
export class AppRoutingModule {}
