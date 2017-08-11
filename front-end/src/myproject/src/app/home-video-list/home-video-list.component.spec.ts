import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeVideoListComponent } from './home-video-list.component';

describe('HomeVideoListComponent', () => {
  let component: HomeVideoListComponent;
  let fixture: ComponentFixture<HomeVideoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeVideoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeVideoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
