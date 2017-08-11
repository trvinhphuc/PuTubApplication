import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeVideoListComponent } from './home-video-list.component';

const homeRoutes: Routes = [
  { path: 'home',  component: HomeVideoListComponent },
];
@NgModule({
    imports: [
        RouterModule.forChild(homeRoutes)
    ],
    exports:[
        RouterModule
    ],
})
export class HomeRoutingModule { }