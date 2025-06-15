import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExecutionPlanRoutingModule } from './execution-plan-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ExecutionPlanListComponent } from './components/execution-plan-list/execution-plan-list.component';
import {MatCardModule} from "@angular/material/card";
import {MatNativeDateModule} from "@angular/material/core";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSnackBarModule} from "@angular/material/snack-bar";



@NgModule({
  declarations: [
    ExecutionPlanListComponent,
  ],
    imports: [
        CommonModule,
        ExecutionPlanRoutingModule,
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
export class ExecutionPlanModule { }
