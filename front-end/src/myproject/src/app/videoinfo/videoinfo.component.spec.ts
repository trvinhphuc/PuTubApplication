import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoinfoComponent } from './videoinfo.component';

describe('VideoinfoComponent', () => {
  let component: VideoinfoComponent;
  let fixture: ComponentFixture<VideoinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VideoinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
