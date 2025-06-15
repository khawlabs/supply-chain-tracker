import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "src/app/core/guards/auth.guard";
import {ShipmentListComponent} from "src/app/features/shipment/components/shipment-list/shipment-list.component";

const routes: Routes = [
  {
    path: '',
    component: ShipmentListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],  // Use RouterModule.forChild for feature modules
  exports: [RouterModule]
})
export class ShipmentRoutingModule { }
