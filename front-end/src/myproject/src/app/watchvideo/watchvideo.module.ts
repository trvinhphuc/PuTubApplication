import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { WatchvideoComponent } from './watchvideo.component';
import { CommentsComponent } from './comments.component';
import { VideoModule } from './../video/video.module';
import { WatchVideoRoutingModule } from './watchvideo-routing.module';
import { AvatarModule } from "../avatar/avatar.module";
//import { CommentsModule } from './comments/comments.module';

import {VgCoreModule} from 'videogular2/core';
import {VgControlsModule} from 'videogular2/controls';
import {VgOverlayPlayModule} from 'videogular2/overlay-play';
import {VgBufferingModule} from 'videogular2/buffering';

/*
creator: Truong Vinh Phuc
function: watch video page
date:11/08/2017
note:
only user who is allowed can watch video or comment
guest only can watch public video

*/
@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        WatchVideoRoutingModule,
        VideoModule,
        AvatarModule,
        //CommentsModule,

        VgBufferingModule,
        VgControlsModule,
        VgOverlayPlayModule,
        VgCoreModule,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    declarations:[
        WatchvideoComponent,
        CommentsComponent,
    ]

})
export class WatchVideoModule {}