import { Injectable } from '@angular/core';
import {
  CanActivate, CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.checkLogin();
  }

  canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.canActivate(route, state); // You can directly reuse canActivate logic
  }

  private checkLogin(): boolean {
    /**
     * for ex :
     *
     *  const isLoggedIn = localStorage.getItem('userToken') ? true : false;
     *
     *     if (!isLoggedIn) {
     *       this.router.navigate(['/login']);
     *       return false;
     *     }
     * */

    return true;
  }
}
