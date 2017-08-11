import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }    from '@angular/forms';
import { Router } from '@angular/router';

import { AppComponent } from './app.component';
import { VideoModule } from './video/video.module';
import { PageNotFoundComponent } from './not-found.component';


import { AppRoutingModule } from './app-routing.module';
import { UploadVideoModule } from './uploadvideo/uploadvideo.module';
import { HomeVideoListModule } from './home-video-list/home-video-list.module';
import { WatchVideoModule } from './watchvideo/watchvideo.module';
import { ChannelModule } from './channel/channel.module';
import { UserinfoModule } from "./userinfo/userinfo.module";
import { SearchpageModule } from './searchpage/searchpage.module';
import { AvatarModule } from "./avatar/avatar.module";
import { VideoinfoModule } from './videoinfo/videoinfo.module';

import { Guard } from "./services/guard.service";
import { DialogService }           from './dialog.service';


@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    
  ],
  imports: [
    BrowserModule,
    
    AppRoutingModule,

    ChannelModule,
    VideoModule,
    UploadVideoModule,
    HomeVideoListModule,
    WatchVideoModule,
    AvatarModule,
    UserinfoModule,
    SearchpageModule,
    VideoinfoModule,
  ],
  providers: [DialogService,Guard],
  bootstrap: [AppComponent]
})
export class AppModule {
  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}