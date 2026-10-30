import { Component, input } from '@angular/core';
import { RatingModel } from '../../models/rating.model';

@Component({
  selector: 'app-review-card',
  imports: [],
  templateUrl: './review-card.component.html',
  styleUrl: './review-card.component.css',
})
export class ReviewCardComponent {
  rating = input.required<RatingModel>();
}
