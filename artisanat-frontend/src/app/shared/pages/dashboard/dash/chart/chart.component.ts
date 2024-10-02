import { Component } from '@angular/core';
import {Chart} from "chart.js/auto";

@Component({
  selector: 'app-chart',
  standalone: true,
  imports: [],
  templateUrl: './chart.component.html',
  styleUrl: './chart.component.css'
})
export class ChartComponent {
  chart: Chart | undefined;

  ngOnInit() {
    this.createChart();
  }

  createChart() {
    const ctx = document.getElementById('myChart') as HTMLCanvasElement;
    this.chart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        datasets: [
          {
            label: 'Service',
            data: [65, 59, 80, 81, 56, 55, 40, 65, 59, 80, 81, 56],
            backgroundColor: '#0d554a',
          },
          {
            label: 'Sale',
            data: [28, 48, 40, 19, 86, 27, 90, 28, 48, 40, 19, 86],
            backgroundColor: '#f2c66d',
          }
        ]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}
