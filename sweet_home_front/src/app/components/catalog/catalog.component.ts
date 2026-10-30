import { Component, inject } from '@angular/core';
import { CatalogService } from './services/catalog.service';
import { CardComponent } from './components/card/card.component';

@Component({
  selector: 'app-catalog',
  imports: [CardComponent],
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.css',
})
export class CatalogComponent {
  catalogService = inject(CatalogService);

  loadMore() {
    this.catalogService.loadProducts();
  }
}
