import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubChannelComponent } from './sub-channel.component';

describe('SubChannelComponent', () => {
  let component: SubChannelComponent;
  let fixture: ComponentFixture<SubChannelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubChannelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubChannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
