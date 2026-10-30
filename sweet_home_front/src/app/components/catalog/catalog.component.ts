import { Component, effect, inject, model, signal } from '@angular/core';
import { CatalogService } from './services/catalog.service';
import { CardComponent } from './components/card/card.component';
import { CategoriesService } from './services/categories.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-catalog',
  imports: [CardComponent, FormsModule],
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.css',
})
export class CatalogComponent {
  catalogService = inject(CatalogService);
  categoriesService = inject(CategoriesService);

  categoryId = signal<number>(-1);

  constructor() {
    // effect(() => {
    //   console.log(`Category ID changed to: ${this.categoryId()}`);
    //   this.catalogService.loadProducts(this.categoryId());
    // });
  }


  loadMore() {
    console.log(`Loading more products for category ID: ${this.categoryId()}`);
    this.catalogService.loadProducts(this.categoryId());
  }
}
