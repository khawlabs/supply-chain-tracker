
export const environment = {
  production: true,
  apiBaseUrl: 'http://localhost:8081',
  endpoints: {
    shipments: '/api/shipment',
    executionPlans: '/api/execution-plan',
    templates: '/api/plan-templates',
    createExecutionPlansBulk: '/api/execution-plans/bulk-create'
  }
};
