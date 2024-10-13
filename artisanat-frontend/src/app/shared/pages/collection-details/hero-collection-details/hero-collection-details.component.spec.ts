import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroCollectionDetailsComponent } from './hero-collection-details.component';

describe('HeroCollectionDetailsComponent', () => {
  let component: HeroCollectionDetailsComponent;
  let fixture: ComponentFixture<HeroCollectionDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeroCollectionDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HeroCollectionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
