import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadedVideosComponent } from './uploaded-videos.component';

describe('UploadedVideosComponent', () => {
  let component: UploadedVideosComponent;
  let fixture: ComponentFixture<UploadedVideosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadedVideosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadedVideosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
