import { Component, OnInit } from '@angular/core';
import { Chart, ChartType, ChartData } from 'chart.js/auto';

@Component({
  selector: 'app-statistics-pie',
  templateUrl: './statistics-pie.component.html',
  standalone: true,
  styleUrls: ['./statistics-pie.component.css']
})
export class StatisticsPieComponent implements OnInit {
  chart: Chart<'pie', number[], string> | undefined;

  ngOnInit() {
    this.createChart();
  }

  createChart() {
    const ctx = document.getElementById('pieChart') as HTMLCanvasElement;
    if (ctx) {
      this.chart = new Chart<'pie', number[], string>(ctx, {
        type: 'pie', // Set chart type explicitly
        data: {
          labels: ['Segment A', 'Segment B', 'Segment C'],
          datasets: [{
            data: [300, 50, 100],
            backgroundColor: ['#379ae6', '#1dd75b', '#efb034']
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: 'right',
            }
          }
        }
      });
    }
  }
}
