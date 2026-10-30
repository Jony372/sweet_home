import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  openMenu = signal<boolean>(false);

  toggleMenu() {
    this.openMenu.update((current) => !current);
  }
}
