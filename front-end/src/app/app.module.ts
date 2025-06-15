import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import {CoreModule} from "./core/core.module";
import { AppRoutingModule } from './app-routing.module';
import {SharedModule} from "./shared/shared.module";
import {LayoutsModule} from "./layouts/layouts.module";
import {ExecutionPlanModule} from "./features/execution-plan/execution-plan.module";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {RouterModule} from "@angular/router";
import {ShipmentModule} from "src/app/features/shipment/shipment.module";
import {MatSnackBarModule} from "@angular/material/snack-bar";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CoreModule,
    AppRoutingModule,
    SharedModule,
    LayoutsModule,
    ExecutionPlanModule,
    ShipmentModule,
    MatSnackBarModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
