import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private injector: Injector) {}

  handleError(error: any): void {
    console.error('Global Error:', error);

    // Get the router instance without direct injection (to avoid cyclic dependency)
   // const router = this.injector.get(Router);
   // router.navigate(['/error']); // Redirect to an error page

    // Optionally, log the error to a remote server
  }
}
