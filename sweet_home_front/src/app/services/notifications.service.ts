import { Injectable, signal } from '@angular/core';
import { ToastModel } from '../models/toast.model';

@Injectable({
  providedIn: 'root',
})
export class NotificationsService {
  public toasts = signal<ToastModel[]>([]);
  private idCounter = 0;

  public show(message: string, type: 'success' | 'error' | 'info' | 'warning', duration: number = 5000): void {
    const id = this.idCounter++;
    this.toasts.update((toasts) => [...toasts, { id, message, type }]);

    setTimeout(() => {
      this.removeToast(id);
    }, duration);
  }

  public removeToast(id: number): void {
    this.toasts.update((toasts) => toasts.filter((toast) => toast.id !== id));
  }
}
