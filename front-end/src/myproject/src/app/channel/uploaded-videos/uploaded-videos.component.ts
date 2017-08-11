import { Component, OnInit } from '@angular/core';
import { VideoService } from "../../services/video_service.service";
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
  selector: 'app-uploaded-videos',
  templateUrl: './uploaded-videos.component.html',
  styleUrls: ['./uploaded-videos.component.css'],
  providers: [VideoService]
})
export class UploadedVideosComponent implements OnInit {
  user: any;
  videos: any[];
  constructor(
    private videoservice: VideoService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.user ={
      id:'',
      name:''
    }
  }
 /*  this.route.params
      .switchMap((params: Params) =>
         */
  getVideosList() {
      this.user.id = sessionStorage.getItem('c_u');
      this.videoservice.getVideoList(this.user.id)
      .subscribe(data => {
        this.videos = data;
      })
  }
  ngOnInit() {
    this.getVideosList();
    //console.log(this.user)
  }

}
