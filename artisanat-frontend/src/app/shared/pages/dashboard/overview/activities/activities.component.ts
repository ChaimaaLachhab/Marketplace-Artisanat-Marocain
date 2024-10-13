import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";

interface Activity {
  user: string;
  avatar: string;
  description: string;
}

@Component({
  selector: 'app-activities',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './activities.component.html',
  styleUrl: './activities.component.css'
})
export class ActivitiesComponent {
  @Input() activities: Activity[] = [];
}
