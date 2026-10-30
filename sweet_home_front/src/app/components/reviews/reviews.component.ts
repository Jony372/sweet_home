import { Component, inject } from '@angular/core';
import { ReviewCardComponent } from './components/review-card/review-card.component';
import { RatingsService } from '../../services/ratings.service';

@Component({
  selector: 'app-reviews',
  imports: [ReviewCardComponent],
  templateUrl: './reviews.component.html',
  styleUrl: './reviews.component.css',
})
export class ReviewsComponent {
  public ratingService = inject(RatingsService);
}
