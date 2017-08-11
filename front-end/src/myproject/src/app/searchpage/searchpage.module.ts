import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VideoModule } from "../video/video.module";
import { SearchpageComponent } from './searchpage.component';
import { SearchpageRoutingModule } from "./searchpage-routing.module";

/*
creator: Truong Vinh Phuc
function: search page
date:11/08/2017
note: can only search one type ( video or user) at a time
*/
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    VideoModule,
    SearchpageRoutingModule,
  ],
  declarations: [SearchpageComponent]
})
export class SearchpageModule { }
