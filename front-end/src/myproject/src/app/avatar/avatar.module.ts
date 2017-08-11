import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AvatarComponent } from './avatar.component';
/*
creator: Truong Vinh Phuc
function: get avatar from back-end
date:11/08/2017
note:
*/

@NgModule({
    imports:[
        CommonModule,
        FormsModule, 
    ],
    exports: [AvatarComponent],
    declarations:[   
       AvatarComponent,
    ]

})
export class AvatarModule {}