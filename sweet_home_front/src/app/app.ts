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
import { AuthModalComponent } from './components/modals/auth-modal/auth-modal.component';
import { CreateCategoryComponent } from './components/modals/create-category/create-category.component';

@Component({
  selector: 'app-root',
  imports: [ToastComponent, AuthModalComponent, NgClass, CreateCategoryComponent, HeaderComponent, HeroComponent, CatalogComponent, AboutUsComponent, ReviewsComponent, FooterComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  public notificationsService = inject(NotificationsService);
  public authService = inject(AuthService);
  public openFabMenu = signal<boolean>(false);
  public showHouseModal = signal<boolean>(false);
  public showCategoryModal = signal<boolean>(false);

  closeModal(){
    this.authService.showLoginModal.set(false);
  }

  openHouseModal(){
    this.openFabMenu.set(false);
  }

  openCategoryModal(){
    this.showCategoryModal.set(true);
  }

  closeCategoryModal(){
    this.showCategoryModal.set(false);
  }

  closeHouseModal(){
    this.showHouseModal.set(false);
  }

  toggleFabMenu() {
    this.openFabMenu.update((current) => !current);
  }
}
