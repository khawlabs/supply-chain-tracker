import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shipment } from '../../models/shipment.model';
import {environment} from "../../../../environments/environment";

@Injectable({
    providedIn: 'root',
})
export class ShipmentService {
    private apiUrl = `${environment.apiBaseUrl}${environment.endpoints.shipments}`;

    constructor(private http: HttpClient) {}

    getAllShipments(): Observable<Shipment[]> {
        return this.http.get<Shipment[]>(this.apiUrl);
    }

    getShipmentById(id: number): Observable<Shipment> {
        return this.http.get<Shipment>(`${this.apiUrl}/${id}`);
    }
    deleteShipmentById(id: number): Observable<Shipment> {
        return this.http.delete<Shipment>(`${this.apiUrl}/${id}`);
    }
}
