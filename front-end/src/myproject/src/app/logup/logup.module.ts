import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LogupRoutingModule } from './logup-routing.module' ;
import { HttpModule }    from '@angular/http';
import { LogupComponent } from './logup.component';
import { DxCheckBoxModule,
         DxSelectBoxModule,
         DxNumberBoxModule,
         DxButtonModule,
         DxFormModule,
        DxFileUploaderModule  } from 'devextreme-angular';
    
/*
creator: Truong Vinh Phuc
function: for registration
date:11/08/2017
note: 
-if user upload avatar but not submit it can cause some bugs because the back-end still not delete that image
-bugs remains on dx form 
*/
@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        LogupRoutingModule,
        DxCheckBoxModule,
         DxSelectBoxModule,
         DxNumberBoxModule,
         DxFormModule,
         DxButtonModule,
         HttpModule,
         DxFileUploaderModule
    ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    declarations:[
      LogupComponent,
     // DxFormComponent,
    ]

})
export class LogupModule {}