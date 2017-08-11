import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { VideoinfoComponent } from './videoinfo.component'

const videoinfoRoutes: Routes = [

  {
    path: '',
    component: VideoinfoComponent,
    
  },

];

@NgModule({
  imports: [
    RouterModule.forChild(videoinfoRoutes)
  ],
  exports: [
    RouterModule
  ],

})
export class VideoinfoRoutingModule { }