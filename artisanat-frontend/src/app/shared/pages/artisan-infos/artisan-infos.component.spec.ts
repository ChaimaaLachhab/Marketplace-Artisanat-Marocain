import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtisanInfosComponent } from './artisan-infos.component';

describe('ArtisanInfosComponent', () => {
  let component: ArtisanInfosComponent;
  let fixture: ComponentFixture<ArtisanInfosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtisanInfosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArtisanInfosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
