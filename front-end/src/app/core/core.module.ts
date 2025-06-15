import {APP_INITIALIZER, ErrorHandler, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FakeAuthInterceptor} from './interceptors/auth.interceptor';
import {GlobalErrorHandler} from './globel-error-handler/global.errorHandler';
import {AppInitService} from './app-init-service/app-init-service.service';
import {ShipmentService} from "./services/shipment/shipment.service";
import {ExecutionPlanService} from "./services/execution-plan/execution-plan.service";
import {PlanTemplateService} from "./services/plan-template/plan-template.service";

export function initializeApp(appInitService: AppInitService) {
  return () => appInitService.initApp();
}

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [
    ShipmentService,
    ExecutionPlanService,
    PlanTemplateService,
    AppInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppInitService],
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: FakeAuthInterceptor,
      multi: true
    },
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    }
  ]
})
export class CoreModule { }
