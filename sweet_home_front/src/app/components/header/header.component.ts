import { NgClass } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  imports: [NgClass],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  authService = inject(AuthService);
  openMenu = signal<boolean>(false);
  openProfileMenu = signal<boolean>(false);

  toggleMenu() {
    this.openMenu.update((current) => !current);
  }

  openAuthModal() {
    this.openProfileMenu.set(false);
    this.authService.showLoginModal.set(true);
  }

  logout() {
    this.authService.logout();
    this.openProfileMenu.set(false);
  }

  toggleProfileMenu() {
    this.openProfileMenu.update((current) => !current);
  }
}
