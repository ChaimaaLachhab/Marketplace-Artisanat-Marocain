import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";

interface Stat {
  label: string;
  value: string;
  change: number;
  positive: boolean;
}

@Component({
  selector: 'app-stats',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './stats.component.html',
  styleUrl: './stats.component.css'
})
export class StatsComponent {
  @Input() stats: Stat[] = [];
}
