import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectionListingComponent } from './collection-listing.component';

describe('CollectionListingComponent', () => {
  let component: CollectionListingComponent;
  let fixture: ComponentFixture<CollectionListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollectionListingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CollectionListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
