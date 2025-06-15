export interface Shipment {
  id: number;
  origin: string;
  destination: string;
  shipmentId: string;
  status: string;
  createdAt: string;
  createdBy: string;
  lastModifiedBy: string;
  lastModifiedAt: string;
  assigned: boolean;
}
