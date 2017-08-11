import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DevExtremeModule } from 'devextreme-angular';
import { DxCheckBoxModule, 
        DxFileUploaderModule, 
        DxSelectBoxModule,
        DxNumberBoxModule,
        DxButtonModule,
        DxFormModule,  } from 'devextreme-angular';

import { UploadvideoComponent } from './uploadvideo.component';

import { UploadRoutingModule } from './upload-routing.module';

/*
creator: Truong Vinh Phuc
function: only user can upload video
date:11/08/2017
note: if user upload video but not submit 
it can cause some bugs because the back-end still not delete that video
Khong cong khai: only user can watch
Rieng tu: shared user can watch
*/
@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        UploadRoutingModule,
        DevExtremeModule,
        DxCheckBoxModule,
        DxFileUploaderModule,
        DxSelectBoxModule,
        DxNumberBoxModule,
        DxButtonModule,
        DxFormModule,
    ],
    declarations:[
        UploadvideoComponent
    ] 
})
export class UploadVideoModule {}