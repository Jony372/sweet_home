import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { HouseModel } from '../components/catalog/models/house.model';
import { PageModel } from '../models/page.model';
import { environment } from '../../environments/environment';
import { NotificationsService } from './notifications.service';

interface HouseReqModel {
  name: string;
  description: string;
  imgUrl: string;
  price: number;
  categoryId: string;
}

@Injectable({
  providedIn: 'root',
})
export class CatalogService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/houses`;
  private currentPage = signal<number>(0);
  private currentCategoryId = signal<number>(-1);
  private notificationsService = inject(NotificationsService);

  public readonly canLoadMore = signal<boolean>(true);
  public catalog = signal<HouseModel[]>([]);
  public isLoading = signal<boolean>(false);
  public error = signal<string | null>(null);

  constructor() {
    this.loadProducts();
  }

  public loadProducts(categoryId: number = -1, elements?: number, sortBy?: string, sortDir?: 'asc' | 'desc'): void {
    console.log(`Loading products for category ID: ${categoryId}, isloading: ${this.isLoading()}, currentPage: ${this.currentPage}, canLoadMore: ${this.canLoadMore()}`);
    if(this.isLoading()) return;
    this.isLoading.set(true);
    this.error.set(null);

    if(this.currentCategoryId() !== categoryId) {
      this.currentPage.set(0);
      this.catalog.set([]);
      this.canLoadMore.set(true);
    }

    const params = new HttpParams({
      fromObject: {
        page: this.currentPage().toString(),
        elements: elements?.toString() || '10',
        sortBy: sortBy || 'createdAt',
        sortDir: sortDir || 'desc'
      }
    })

    this.currentCategoryId.set(categoryId);

    if (this.currentCategoryId() > 0) {
      this.loadByCategory(this.currentCategoryId()!, params);
    } else {
      this.loadAllProducts(params);
    }
  }

  public loadAllProducts(params:HttpParams):void {
    if(!this.canLoadMore()) {
      this.isLoading.set(false);
      this.error.set(null);
      return;
    };

    this.http.get<PageModel<HouseModel>>(this.apiUrl, { params }).subscribe({
      next: (response) => {
        this.catalog.update((currentCatalog) => [...currentCatalog, ...response.content]);
        this.currentPage.update((current) => current + 1);
        this.canLoadMore.set(this.currentPage() < response.page.totalPages);
      },
      error: (err) => {
        this.error.set(err.message || 'An error occurred while loading products.');
      },
      complete: () => {
        this.isLoading.set(false);
      }
    })

  }

  loadByCategory(categoryId: number, params: HttpParams): void {
    if(!this.canLoadMore()) {
      this.isLoading.set(false);
      this.error.set(null);
      return;
    };
    this.http.get<PageModel<HouseModel>>(`${this.apiUrl}/category/${categoryId}`, { params }).subscribe({
      next: (response) => {
        this.catalog.update((currentCatalog) => [...currentCatalog, ...response.content]);
        this.currentPage.update((current) => current + 1);
        this.canLoadMore.set(this.currentPage() < response.page.totalPages);
      },
      error: (err) => {
        this.error.set(err.message || 'An error occurred while loading products.');
      },
      complete: () => {
        this.isLoading.set(false);
      }
    });
  }

  createHouse(houseData: HouseReqModel): void {
    this.http.post<HouseModel>(this.apiUrl, houseData).subscribe({
      next: (newHouse) => {
        this.notificationsService.show('Casa creada exitosamente.', 'success');
        this.currentPage.set(0);
        this.catalog.set([]);
        this.loadProducts(this.currentCategoryId());
      }
    })
  }
}
