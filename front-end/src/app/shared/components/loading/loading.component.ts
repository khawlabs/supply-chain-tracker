import { Component } from '@angular/core';
import {GlobalLoadingService} from "src/app/shared/services/global-loading/global-loading.service";

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrl: './loading.component.scss'
})
export class LoadingComponent {
  constructor(public loadingService: GlobalLoadingService) {}
}
