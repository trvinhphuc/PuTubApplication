import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { VideoComponent } from './video.component';

/*
creator: Truong Vinh Phuc
function: this is a thumbnail which link to watch video page
date:11/08/2017
note: the image of this is loaded from database
because the video still not have its own image representation so it use deafault image
*/
@NgModule({
    imports:[
        CommonModule,
        FormsModule, 
    ],
    exports: [VideoComponent],
    declarations:[   
       VideoComponent,
    ]

})
export class VideoModule {}