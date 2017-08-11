import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DevExtremeModule } from 'devextreme-angular';
import { VideoinfoComponent } from './videoinfo.component'
import { VideoinfoRoutingModule } from "./videoinfo-routing.module";
import {
  DxCheckBoxModule,
  DxSelectBoxModule,
  DxNumberBoxModule,
  DxButtonModule,
  DxFormModule,
} from 'devextreme-angular';
import { VgCoreModule } from 'videogular2/core';
import { VgControlsModule } from 'videogular2/controls';
import { VgOverlayPlayModule } from 'videogular2/overlay-play';
import { VgBufferingModule } from 'videogular2/buffering';

/*
creator: Truong Vinh Phuc
function: update video info
date:11/08/2017
note: 
only the owner can upload the video's info, but this check still not working, fix Guard.service for this
*/
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    VideoinfoRoutingModule,
    DevExtremeModule,
    DxCheckBoxModule,
    DxSelectBoxModule,
    DxNumberBoxModule,
    DxButtonModule,
    DxFormModule,

    VgBufferingModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgCoreModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [VideoinfoComponent]
})
export class VideoinfoModule { }
