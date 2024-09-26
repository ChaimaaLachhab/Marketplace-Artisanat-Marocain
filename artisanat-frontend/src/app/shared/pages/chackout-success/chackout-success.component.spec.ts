import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChackoutSuccessComponent } from './chackout-success.component';

describe('ChackoutSuccessComponent', () => {
  let component: ChackoutSuccessComponent;
  let fixture: ComponentFixture<ChackoutSuccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChackoutSuccessComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChackoutSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
