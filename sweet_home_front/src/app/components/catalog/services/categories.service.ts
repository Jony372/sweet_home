import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { CategoryModel } from '../models/cateogory.model';
import { environment } from '../../../../environments/environment';

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
}
