import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {DividerModule} from "primeng/divider";
import {RatingModule} from "primeng/rating";
import {Category} from "../../../../core/enums/category.enum";
import {Collection} from "../../../../core/enums/collection.enum";
import {NgForOf, NgIf, TitleCasePipe} from "@angular/common";
import {AccordionModule} from "primeng/accordion";
import {MatIcon} from "@angular/material/icon";
import {RadioButtonModule} from "primeng/radiobutton";

@Component({
  selector: 'app-filter-sidebar',
  standalone: true,
  imports: [
    MatIcon,
    FormsModule,
    AccordionModule,
    TitleCasePipe,
    RadioButtonModule,
    RatingModule,
    NgForOf,
    NgIf,
    DividerModule
  ],
  templateUrl: './filter-sidebar.component.html',
  styleUrl: './filter-sidebar.component.css'
})
export class FilterSidebarComponent implements OnInit {
  @Output() filtersChanged = new EventEmitter<any>();

  expandedSection: string | null = null;

  collectionOptions = Object.values(Collection);
  categoryOptions = Object.values(Category);

  filters: any = {
    collections: {},
    categories: {},
    minPrice: null,
    maxPrice: null,
    rating: null
  };

  ngOnInit() {
    this.initializeFilters();
  }

  initializeFilters() {
    this.collectionOptions.forEach(col => this.filters.collections[col] = false);
    this.categoryOptions.forEach(cat => this.filters.categories[cat] = false);
  }

  toggleSection(section: string): void {
    this.expandedSection = this.expandedSection === section ? null : section;
  }

  onSubmit() {
    this.filtersChanged.emit(this.filters);
  }

  clearFilters() {
    this.initializeFilters();
    this.filters.minPrice = null;
    this.filters.maxPrice = null;
    this.filters.rating = null;
    this.filtersChanged.emit(this.filters);
  }
}
