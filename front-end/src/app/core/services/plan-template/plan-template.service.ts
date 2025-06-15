import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PlanTemplate } from '../../models/plan-template.model';
import {environment} from "../../../../environments/environment";

@Injectable({
    providedIn: 'root',
})
export class PlanTemplateService {
    private apiUrl = `${environment.apiBaseUrl}${environment.endpoints.templates}`;

    constructor(private http: HttpClient) {}

    getAllTemplates(): Observable<PlanTemplate[]> {
        return this.http.get<PlanTemplate[]>(this.apiUrl);
    }

    getTemplateById(id: number): Observable<PlanTemplate> {
        return this.http.get<PlanTemplate>(`${this.apiUrl}/${id}`);
    }
}
