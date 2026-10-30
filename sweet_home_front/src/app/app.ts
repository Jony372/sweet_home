import { Component, inject, signal } from '@angular/core';
import { HeaderComponent } from './components/header/header.component';
import { HeroComponent } from './components/hero/hero.component';
import { CatalogComponent } from './components/catalog/catalog.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { FooterComponent } from './components/footer/footer.component';
import { NotificationsService } from './services/notifications.service';
import { NgClass } from '@angular/common';
import { ToastComponent } from './components/toast/toast.component';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  imports: [ToastComponent, HeaderComponent, HeroComponent, CatalogComponent, AboutUsComponent, ReviewsComponent, FooterComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  public notificationsService = inject(NotificationsService);
  public authService = inject(AuthService);
}
