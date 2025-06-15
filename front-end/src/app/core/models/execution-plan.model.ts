import {Shipment} from "src/app/core/models/shipment.model";
import {PlanTemplate} from "src/app/core/models/plan-template.model";

export interface ExecutionPlan {
    id: number;
    planTemplateId: number;
    shipmentId: string;
    fragile: boolean;
    notifyCustomer: boolean;
    origin: string;
    priority: string;
    estimatedDeliveryDate: string;
    destination: string;
    shipment: Shipment;
    planTemplate: PlanTemplate;
}
