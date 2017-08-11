import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LogupComponent }    from './logup.component';


const logupRoutes: Routes = [
  { path: '',  component: LogupComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(logupRoutes)
  ],
  exports: [
    RouterModule
  ],

})
export class LogupRoutingModule { }