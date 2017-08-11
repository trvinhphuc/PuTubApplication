import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

//import { WatchvideoComponent } from './watchvideo.component';
import { VideoModule } from './../video/video.module';
//import { WatchVideoRoutingModule } from './watchvideo-routing.module';
import { ChannelComponent } from "./channel.component";
import { ChannelRoutingModule } from "./channel-routing.module";
import { HomeVideoListModule } from './../home-video-list/home-video-list.module';
import { HttpModule }    from '@angular/http';

import { HomeChannelComponent } from './home-channel/home-channel.component';
import { UploadedVideosComponent } from './uploaded-videos/uploaded-videos.component';
import { InfoChannelComponent } from './info-channel/info-channel.component';
import { SubChannelComponent } from './sub-channel/sub-channel.component';

/*
creator: Truong Vinh Phuc
function: channel of a user, user only can log out from here
date:11/08/2017
note:only uploaded-videos is working, others are still under developing
*/
@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        ChannelRoutingModule,
        VideoModule,
        HttpModule,
        HomeVideoListModule
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    declarations:[        
        ChannelComponent, 
        HomeChannelComponent, 
        UploadedVideosComponent, 
        InfoChannelComponent, 
        SubChannelComponent,
    ]

})
export class ChannelModule {}