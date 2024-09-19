import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroAboutUsComponent } from './hero-about-us.component';

describe('HeroAboutUsComponent', () => {
  let component: HeroAboutUsComponent;
  let fixture: ComponentFixture<HeroAboutUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeroAboutUsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HeroAboutUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
