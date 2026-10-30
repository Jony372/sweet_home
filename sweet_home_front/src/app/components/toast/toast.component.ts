import { Component, input, output } from '@angular/core';
import { ToastModel } from '../../models/toast.model';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-toast',
  imports: [NgClass],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.css',
})
export class ToastComponent {
  public notification = input.required<ToastModel>();
  public onClose = output();

  closeToast() {
    this.onClose.emit();
  }
}
