import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { Role } from "../enums/role.enum";

export const roleGuard = (allowedRoles: Role[]): CanActivateFn => {
  return (_route, state) => {
    const authService = inject(AuthenticationService);
    const router = inject(Router);

    if (!authService.isLoggedIn()) {
      router.navigate(['/login']);
      return false;
    }

    if (allowedRoles.some(role => authService.hasRole(role))) {
      return true;
    }

    router.navigate(['/unauthorized']);
    return false;
  };
};
