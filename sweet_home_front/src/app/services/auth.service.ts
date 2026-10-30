import { computed, inject, Injectable, signal } from '@angular/core';
import { environment } from '../../environments/environment';
import { UserModel } from '../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginResponseModel } from '../models/login-response.model';
import { NotificationsService } from './notifications.service';
import { RegisterReqModel } from '../models/register-req.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  private http = inject(HttpClient);
  private notificationsService = inject(NotificationsService);


  public showLoginModal = signal<boolean>(false);
  currentUser = signal<UserModel | null>(null);
  isAdmin = computed(() => this.currentUser()?.isAdmin ?? false);

  login(username: string, password: string, rememberMe: boolean):Observable<LoginResponseModel> {
    return this.http.post<LoginResponseModel>(`${this.apiUrl}/login`, { username, password, rememberMe }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));
        this.currentUser.set(response.user);
        this.showLoginModal.set(false);
        this.notificationsService.show('Inicio de sesión exitoso.', 'success');
      })
    );
  }

  register(registerData: RegisterReqModel): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, registerData).pipe(
      tap(() => {
        this.notificationsService.show('Registro exitoso. Ahora puedes iniciar sesión.', 'success');
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUser.set(null);
    this.showLoginModal.set(false);
  }
}
