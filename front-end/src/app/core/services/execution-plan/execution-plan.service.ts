import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExecutionPlan } from '../../models/execution-plan.model';
import {environment} from "src/environments/environment";

@Injectable({
    providedIn: 'root',
})
export class ExecutionPlanService {
    private apiUrl = `${environment.apiBaseUrl}${environment.endpoints.executionPlans}`;
    private apiUrlCreate = `${environment.apiBaseUrl}${environment.endpoints.createExecutionPlansBulk}`;

    constructor(private http: HttpClient) {}

    createExecutionPlan(executionPlan: ExecutionPlan): Observable<ExecutionPlan> {
      return this.http.post<ExecutionPlan>(`${this.apiUrl}`, executionPlan);
    }

    createExecutionPlans(shipmentIds: number[], templateId: number): Observable<ExecutionPlan[]> {
        return this.http.post<ExecutionPlan[]>(`${this.apiUrlCreate}`, shipmentIds, {
            params: { templateId: templateId.toString() },
        });
    }

    getAllExecutionPlans(): Observable<ExecutionPlan[]> {
        return this.http.get<ExecutionPlan[]>(this.apiUrl);
    }

    getExecutionPlanById(id: number): Observable<ExecutionPlan> {
        return this.http.get<ExecutionPlan>(`${this.apiUrl}/${id}`);
    }

    deleteExecutionPlan(id: number): Observable<ExecutionPlan> {
      return this.http.get<ExecutionPlan>(`${this.apiUrl}/${id}`);
    }
}
