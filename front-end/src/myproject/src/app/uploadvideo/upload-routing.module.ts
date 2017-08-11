import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UploadvideoComponent }    from './uploadvideo.component';


const uploadRoutes: Routes = [

  { path: '',  component: UploadvideoComponent },
  
];

@NgModule({
  imports: [
    RouterModule.forChild(uploadRoutes)
  ],
  exports: [
    RouterModule
  ],

})
export class UploadRoutingModule { }