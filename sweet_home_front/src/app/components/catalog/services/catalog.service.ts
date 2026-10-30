import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { HouseModel } from '../models/house.model';
import { PageModel } from '../../../models/page.model';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CatalogService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/houses`;
  private currentPage = 0;

  public readonly canLoadMore = signal<boolean>(true);
  public catalog = signal<HouseModel[]>([]);
  public isLoading = signal<boolean>(false);
  public error = signal<string | null>(null);

  constructor() {
    this.loadProducts();
  }

  public loadProducts(elements?: number, sortBy?: string, sortDir?: 'asc' | 'desc'):void {
    if(!this.canLoadMore || this.isLoading()) return;
    this.isLoading.set(true);
    this.error.set(null);

    const params = new HttpParams({
      fromObject: {
        page: this.currentPage.toString(),
        elements: elements?.toString() || '10',
        sortBy: sortBy || 'createdAt',
        sortDir: sortDir || 'desc'
      }
    })

    this.http.get<PageModel<HouseModel>>(this.apiUrl, { params }).subscribe({
      next: (response) => {
        this.catalog.update((currentCatalog) => [...currentCatalog, ...response.content]);
        this.currentPage++;
        this.canLoadMore.set(this.currentPage < response.page.totalPages);
      },
      error: (err) => {
        this.error.set(err.message || 'An error occurred while loading products.');
      },
      complete: () => {
        this.isLoading.set(false);
      }
    })

  }
}
