
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { HomeVideoListComponent } from './home-video-list.component';
import { ListVideoComponent } from './list-video.component';
import { VideoModule} from './../video/video.module';
import { DxListModule,DxFormModule } from 'devextreme-angular';
//import { VideoComponent } from './video/video.component';

import { HomeRoutingModule } from './home-routing.module';

/*
creator: Truong Vinh Phuc
function: home of the project, contain search bar and list of all users(except admin)
date:11/08/2017
note:
*/
@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        HomeRoutingModule,
        VideoModule,
        DxListModule,
        DxFormModule
    ],
    
    schemas:[CUSTOM_ELEMENTS_SCHEMA],
    declarations:[
        HomeVideoListComponent,
        ListVideoComponent,
        //VideoComponent,
    ]

})
export class HomeVideoListModule {}