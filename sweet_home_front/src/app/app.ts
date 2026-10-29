import { Component, signal } from '@angular/core';
import { HeaderComponent } from './components/header/header.component';
import { HeroComponent } from './components/hero/hero.component';
import { CatalogComponent } from './components/catalog/catalog.component';

@Component({
  selector: 'app-root',
  imports: [HeaderComponent, HeroComponent, CatalogComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('sweet_home_front');
}
