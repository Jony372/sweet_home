import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { CategoryModel } from '../components/catalog/models/cateogory.model';
import { environment } from '../../environments/environment';

interface CategoryReqModel {
  name: string;
  description: string;
}

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/categories`;

  public categories = signal<CategoryModel[]>([]);
  public isLoading = signal<boolean>(false);
  public error = signal<string | null>(null);

  constructor() {
    this.getCategories();
  }

  getCategories(): void {
    if(this.isLoading()) return;

    this.isLoading.set(true);
    this.http.get<CategoryModel[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.categories.set(data);
      },
      error: (err) => {
        this.error.set(err.message);
      },
      complete: () => {
        this.isLoading.set(false);
      }
    });
  }

  createCategory(categoryData: CategoryReqModel): void {
    this.http.post<CategoryModel>(this.apiUrl, categoryData).subscribe({
      next: (newCategory) => {
        this.categories.update((currentCategories) => [...currentCategories, newCategory]);
      },
      error: (err) => {
        this.error.set(err.message);
      }
    });
  }
}
