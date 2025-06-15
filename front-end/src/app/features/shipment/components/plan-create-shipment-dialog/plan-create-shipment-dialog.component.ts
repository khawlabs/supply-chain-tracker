import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Shipment } from 'src/app/core/models/shipment.model';
import { PlanTemplate } from 'src/app/core/models/plan-template.model';
import { PlanTemplateService } from 'src/app/core/services/plan-template/plan-template.service';

@Component({
  selector: 'app-plan-create-shipment-dialog',
  templateUrl: './plan-create-shipment-dialog.component.html',
  styleUrls: ['./plan-create-shipment-dialog.component.scss']
})
export class PlanCreateShipmentDialogComponent implements OnInit {
  form!: FormGroup;
  templates: PlanTemplate[] = [];

  constructor(
    public dialogRef: MatDialogRef<PlanCreateShipmentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public shipment: Shipment,
    private fb: FormBuilder,
    private templateService: PlanTemplateService
  ) {}

  ngOnInit(): void {
    // Load templates
    this.templateService.getAllTemplates().subscribe({
      next: res => this.templates = res,
      error: err => {
        console.error('Failed to load templates', err);
        this.templates = [];
      }
    });

    // Create form with validation
    this.form = this.fb.group({
      priority: ['', Validators.required],
      estimatedDeliveryDate: ['', Validators.required],
      fragile: [false, Validators.required],
      notifyCustomer: [false, Validators.required],
      planTemplate: [null, Validators.required]
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched(); // show errors
      return;
    }

    const formValue = this.form.value;

    const payload = {
      priority: formValue.priority,
      estimatedDeliveryDate: formValue.estimatedDeliveryDate,
      fragile: formValue.fragile,
      notifyCustomer: formValue.notifyCustomer,
      planTemplateId: formValue.planTemplate.id,
      shipmentId: this.shipment.shipmentId,
      shipment : this.shipment
    };

    this.dialogRef.close(payload);
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
