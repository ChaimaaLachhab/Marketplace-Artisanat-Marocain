import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-header-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './header-dashboard.component.html',
  styleUrl: './header-dashboard.component.css'
})
export class HeaderDashboardComponent {
  @Output() createNewProduct = new EventEmitter<void>();
  @Output() logout = new EventEmitter<void>();

  onCreateNewProduct() {
    this.createNewProduct.emit();
  }

  onLogout() {
    this.logout.emit();
  }
}
