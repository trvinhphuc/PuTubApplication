import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AvatarModule } from "../avatar/avatar.module";
import { UserinfoRoutingModule } from "./userinfo-routing.module";
import { UserinfoComponent } from './userinfo.component';
import { BasicInfoComponent } from './basic-info/basic-info.component';
import { PasswordChangeComponent } from './password-change/password-change.component';
import { SubcribedChannelsComponent } from './subcribed-channels/subcribed-channels.component';

import {
  DxCheckBoxModule,
  DxSelectBoxModule,
  DxNumberBoxModule,
  DxButtonModule,
  DxFormModule,
  DxFileUploaderModule
} from 'devextreme-angular';

/*
creator: Truong Vinh Phuc
function: update user info
date:11/08/2017
note:
 only user can update his/her info 
if user upload new avatar but not submit 
it can cause some bugs because the back-end still not delete that image
*/
@NgModule({
  imports: [
    CommonModule,
    AvatarModule,
    UserinfoRoutingModule,
    DxCheckBoxModule,
    DxSelectBoxModule,
    DxNumberBoxModule,
    DxButtonModule,
    DxFormModule,
    DxFileUploaderModule,
  ],
  declarations: [UserinfoComponent, 
    BasicInfoComponent, 
    PasswordChangeComponent, 
    SubcribedChannelsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class UserinfoModule { }
