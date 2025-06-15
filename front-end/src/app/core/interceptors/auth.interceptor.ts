import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
// import { catchError, switchMap } from 'rxjs/operators';
// import { AuthService } from './auth.service'; // Assume AuthService manages authentication

@Injectable()
export class FakeAuthInterceptor implements HttpInterceptor {
 /**
  * for ex.
  *   constructor(private authService: AuthService) {}
  *  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  *     // Get the auth token from the service.
  *     const authToken = this.authService.getAuthToken();
  *
  *     // Clone the request and replace the original headers with
  *     // cloned headers, updated with the authorization.
  *     const authReq = req.clone({
  *       headers: req.headers.set('Authorization', `Bearer ${authToken}`)
  *     });
  *
  *     // send cloned request with header to the next handler.
  *     return next.handle(authReq).pipe(
  *       catchError((error: HttpErrorResponse) => {
  *         // Handle specific HTTP errors here (e.g., refresh token logic)
  *         return throwError(error);
  *       })
  *     );
  *   }
  *
  * */

 intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   // Simulate retrieving a token (use a static token for testing)
   const authToken = 'fake-token-for-testing';

   // Clone the request to add the new header.
   const authReq = req.clone({
     headers: req.headers.set('Authorization', `Bearer ${authToken}`)
   });

   // Pass the cloned request instead of the original request to the next handle
   return next.handle(authReq);
 }
}
