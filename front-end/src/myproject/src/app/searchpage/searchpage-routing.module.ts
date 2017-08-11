import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SearchpageComponent } from './searchpage.component';

const searchRoutes: Routes = [
  { path: '',  component: SearchpageComponent },
  
];
@NgModule({
    imports: [
        RouterModule.forChild(searchRoutes)
    ],
    exports:[
        RouterModule
    ],
})
export class SearchpageRoutingModule { }