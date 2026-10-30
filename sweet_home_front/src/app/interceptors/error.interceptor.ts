import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { NotificationsService } from '../services/notifications.service';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const notificationsService = inject(NotificationsService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let errorMessage = 'Ocurrió un error inesperado.';
      if (error.error && error.error.message) {
        errorMessage = error.error.message;
      }else if(error.status === 0){
        errorMessage = 'No se pudo conectar con el servidor. Por favor, verifica tu conexión a internet.';
      }
      notificationsService.show(errorMessage, 'error');
      console.error('Error interceptado:', error);

      return throwError(() => error);
    })
  )
};
