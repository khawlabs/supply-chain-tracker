import { Injectable } from '@angular/core';
import SockJS from 'sockjs-client/dist/sockjs.min.js';

import { Client, IMessage, Stomp } from '@stomp/stompjs';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class WebSocketService {
  private client!: Client;
  public shipmentNotifications$ = new Subject<any>();

  connect(): void {
    this.client = Stomp.over(() => new SockJS('http://localhost:8081/ws')); // ✅ no runtime crash

    this.client.onConnect = () => {
      this.client.subscribe('/topic/shipments', (message: IMessage) => {
        const payload = JSON.parse(message.body);
        this.shipmentNotifications$.next(payload);
      });
    };

    this.client.activate();
  }

  disconnect(): void {
    if (this.client) {
      this.client.deactivate();
    }
  }
}
