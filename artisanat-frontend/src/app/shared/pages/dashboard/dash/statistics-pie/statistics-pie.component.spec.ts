import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticsPieComponent } from './statistics-pie.component';

describe('StatisticsPieComponent', () => {
  let component: StatisticsPieComponent;
  let fixture: ComponentFixture<StatisticsPieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StatisticsPieComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StatisticsPieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
