import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { RatingModel } from '../components/reviews/models/rating.model';
import { environment } from '../../environments/environment';
import { PageModel } from '../models/page.model';

@Injectable({
  providedIn: 'root',
})
export class RatingsService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/ratings`;

  public ratings = signal<RatingModel[]>([]);
  public isLoading = signal<boolean>(false);
  public error = signal<string | null>(null);

  constructor() {
    this.loadRatings();
  }

  loadRatings(): void {
    if (this.isLoading()) return;
    
    this.error.set(null);
    this.isLoading.set(true);
    this.http.get<PageModel<RatingModel>>(`${this.apiUrl}?page=0&size=3`).subscribe({
      next: (response) => {
        this.ratings.set(response.content);
      },
      error: (error) => {
        this.error.set('Failed to load ratings '+error.message);
      },
      complete: () => {
        this.isLoading.set(false);
      }
    });
  }

}
