import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserinfoComponent } from "./userinfo.component";
import { BasicInfoComponent } from "./basic-info/basic-info.component";
import { PasswordChangeComponent } from "./password-change/password-change.component";
import { SubcribedChannelsComponent } from "./subcribed-channels/subcribed-channels.component";

const userinfoRoutes: Routes = [

  {
    path: '',
    component: UserinfoComponent,
    children: [
      { path: '', component: BasicInfoComponent },
      { path: 'basic_info', component: BasicInfoComponent },
      { path: 'password_change', component: PasswordChangeComponent },
      { path: 'subcribed_channels', component: SubcribedChannelsComponent },
      
    ]
  },

];

@NgModule({
  imports: [
    RouterModule.forChild(userinfoRoutes)
  ],
  exports: [
    RouterModule
  ],

})
export class UserinfoRoutingModule { }