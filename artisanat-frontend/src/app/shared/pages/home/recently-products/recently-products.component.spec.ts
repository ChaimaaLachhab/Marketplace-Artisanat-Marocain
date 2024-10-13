import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentlyProductsComponent } from './recently-products.component';

describe('RecentlyProductsComponent', () => {
  let component: RecentlyProductsComponent;
  let fixture: ComponentFixture<RecentlyProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecentlyProductsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RecentlyProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
