import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './not-found.component';
import { ChannelComponent } from "./channel/channel.component";
import { Guard }                from './services/guard.service';
import { SelectivePreloadingStrategy } from './selective-preloading-strategy';

const appRoutes: Routes = [
  {
    path: 'upload',
    loadChildren: 'app/uploadvideo/uploadvideo.module#UploadVideoModule',
  },
  {
    path: 'login',
    loadChildren: 'app/login/login.module#LoginModule',
    
  },
  {
    path: 'logup',
    loadChildren: 'app/logup/logup.module#LogupModule',
    
  },
  {
    path: 'channel/:id',
    //component: ChannelComponent,
    loadChildren: 'app/channel/channel.module#ChannelModule',
  },
  {
    path: 'userinfo/:id',
    loadChildren: 'app/userinfo/userinfo.module#UserinfoModule',
  },
  {
    path: 'search/:option/:searchQuery',
    loadChildren: 'app/searchpage/searchpage.module#SearchpageModule',
    
  },
  {
    path: 'videoinfo/:id',
    canActivate:[Guard],
    loadChildren: 'app/videoinfo/videoinfo.module#VideoinfoModule',
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  //{ path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { preloadingStrategy: SelectivePreloadingStrategy }
    )
  ],
  exports: [
    RouterModule
  ],
  providers:[SelectivePreloadingStrategy]
})
export class AppRoutingModule { }