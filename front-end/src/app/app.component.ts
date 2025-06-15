import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import {WebSocketService} from "src/app/core/services/websocket/web-socket-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'front-end';
  private subscription!: Subscription;

  constructor(
    private wsService: WebSocketService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.wsService.connect();
    this.subscription = this.wsService.shipmentNotifications$.subscribe(notification => {
      this.snackBar.open(notification.message, 'Close', {
        horizontalPosition: 'right',
        verticalPosition: 'top'
      });
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.wsService.disconnect();
  }
}
