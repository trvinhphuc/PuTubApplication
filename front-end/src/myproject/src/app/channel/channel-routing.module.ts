import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ChannelComponent } from './channel.component';
import { HomeChannelComponent } from './home-channel/home-channel.component';
import { UploadedVideosComponent } from './uploaded-videos/uploaded-videos.component';
import { InfoChannelComponent } from './info-channel/info-channel.component';
import { SubChannelComponent } from './sub-channel/sub-channel.component';

const channelRoutes: Routes = [

  {
    path: '',
    component: ChannelComponent,
    children: [
      { path: '', component: HomeChannelComponent },
      { path: 'home_channel', component: HomeChannelComponent },
      { path: 'info_channel', component: InfoChannelComponent },
      { path: 'sub_channel', component: SubChannelComponent },
      { path: 'uploaded_videos', component: UploadedVideosComponent },
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(channelRoutes)
  ],
  exports: [
    RouterModule
  ],

})
export class ChannelRoutingModule { }