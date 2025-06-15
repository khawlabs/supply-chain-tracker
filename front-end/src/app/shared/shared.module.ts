import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ErrorComponent } from './components/error/error.component';
import {GlobalLoadingService} from "src/app/shared/services/global-loading/global-loading.service";
import { LoadingComponent } from './components/loading/loading.component';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MatSnackBarModule} from "@angular/material/snack-bar";



@NgModule({
  declarations: [
    PageNotFoundComponent,
    ErrorComponent,
    LoadingComponent,
    ConfirmDialogComponent
  ],
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatDialogModule,
    MatButtonModule
  ],
  exports: [
    LoadingComponent
  ],
  providers: [
    GlobalLoadingService
  ]
})
export class SharedModule { }
