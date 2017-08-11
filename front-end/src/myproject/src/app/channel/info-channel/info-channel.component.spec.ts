import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoChannelComponent } from './info-channel.component';

describe('InfoChannelComponent', () => {
  let component: InfoChannelComponent;
  let fixture: ComponentFixture<InfoChannelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoChannelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoChannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
