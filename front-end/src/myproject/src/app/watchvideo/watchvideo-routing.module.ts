import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { WatchvideoComponent }    from './watchvideo.component';


const videoRoutes: Routes = [
  { path: 'video/:id',  component: WatchvideoComponent },
  
];

@NgModule({
  imports: [
    RouterModule.forChild(videoRoutes)
  ],
  exports: [
    RouterModule
  ],

})
export class WatchVideoRoutingModule { }