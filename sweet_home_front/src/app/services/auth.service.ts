import { computed, inject, Injectable, signal } from '@angular/core';
import { environment } from '../../environments/environment';
import { UserModel } from '../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginResponseModel } from '../models/login-response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  private http = inject(HttpClient);

  currentUser = signal<UserModel | null>(null);
  isAdmin = computed(() => this.currentUser()?.isAdmin ?? false);

  login(username: string, password: string, rememberMe: boolean):Observable<LoginResponseModel> {
    return this.http.post<LoginResponseModel>(`${this.apiUrl}/login`, { username, password, rememberMe }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));
        this.currentUser.set(response.user);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUser.set(null);
  }

  private loadUserFromStorage(): UserModel | null {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }
}
