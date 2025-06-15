import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ShipmentListComponent} from "src/app/features/shipment/components/shipment-list/shipment-list.component";
import {ShipmentRoutingModule} from "src/app/features/shipment/shipment-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { PlanCreateShipmentDialogComponent } from './components/plan-create-shipment-dialog/plan-create-shipment-dialog.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatSelectModule} from "@angular/material/select";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatIconModule} from "@angular/material/icon";
import {MatNativeDateModule} from "@angular/material/core";
import {MatCardModule} from "@angular/material/card";
import {MatSnackBarModule} from "@angular/material/snack-bar";



@NgModule({
  declarations: [
    ShipmentListComponent,
    PlanCreateShipmentDialogComponent,
  ],
  imports: [
    CommonModule,
    ShipmentRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatCardModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatSelectModule,
    MatDatepickerModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule
  ]
})
export class ShipmentModule { }
