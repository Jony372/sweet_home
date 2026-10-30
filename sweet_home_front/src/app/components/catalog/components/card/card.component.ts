import { Component, input } from '@angular/core';
import { HouseModel } from '../../models/house.model';
import { CurrencyPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-card',
  imports: [CurrencyPipe, DatePipe],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css',
})
export class CardComponent {
  houseInfo = input.required<HouseModel>();
}
