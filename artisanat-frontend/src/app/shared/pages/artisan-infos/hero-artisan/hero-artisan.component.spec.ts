import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroArtisanComponent } from './hero-artisan.component';

describe('HeroArtisanComponent', () => {
  let component: HeroArtisanComponent;
  let fixture: ComponentFixture<HeroArtisanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeroArtisanComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HeroArtisanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
