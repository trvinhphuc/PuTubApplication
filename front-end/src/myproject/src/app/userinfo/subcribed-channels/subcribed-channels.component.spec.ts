import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcribedChannelsComponent } from './subcribed-channels.component';

describe('SubcribedChannelsComponent', () => {
  let component: SubcribedChannelsComponent;
  let fixture: ComponentFixture<SubcribedChannelsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubcribedChannelsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubcribedChannelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
