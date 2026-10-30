import { HttpInterceptorFn } from '@angular/common/http';
import { NotificationsService } from '../services/notifications.service';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { ErrorResponseModel } from '../models/error-response.model';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const notificationsService = inject(NotificationsService);

  return next(req).pipe(
    catchError((error: ErrorResponseModel) => {
      let errorMessage = 'Ocurrió un error inesperado.';
      if (error && error.message) {
        errorMessage = error.message;
      }else if(error.status === 0){
        errorMessage = 'No se pudo conectar con el servidor. Por favor, verifica tu conexión a internet.';
      }
      notificationsService.show(errorMessage, 'error');
      console.error('Error interceptado:', error);
      
      return throwError(() => error);
    })
  )
};
