import { Action } from './action.model';
import { TemperatureRange } from './temperature-range.model';

export interface PlanTemplate {
    id: number;
    name: string;
    actions: Action[];
    temperatureRange: TemperatureRange;
}
