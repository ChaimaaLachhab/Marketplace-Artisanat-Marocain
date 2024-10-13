import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatedProductsComponent } from './rated-products.component';

describe('RatedProductsComponent', () => {
  let component: RatedProductsComponent;
  let fixture: ComponentFixture<RatedProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RatedProductsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RatedProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
