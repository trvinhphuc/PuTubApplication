import { Component, OnInit } from '@angular/core';

import { UserService } from "../services/user.service";
import { VideoService } from './../services/video_service.service';
import { CommentService } from "../services/comments.service";

import 'rxjs/add/operator/switchMap';
import notify from 'devextreme/ui/notify';
//import '../../test.mp4';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VgAPI } from 'videogular2/core';

@Component({
  selector: 'app-watchvideo',
  templateUrl: './watchvideo.component.html',
  styleUrls: ['./watchvideo.component.css'],
  providers: [VideoService, UserService, CommentService],

})

export class WatchvideoComponent implements OnInit {

  preload: string = 'auto';
  api: VgAPI;
  videos: Array<any>;
  video: any;
  testData: any;
  id_video: number;
  current_user: any;
  sub: any;
  users: any[];
  userId: any;
  user: any;
  count:number;
  show_list : any[];
  watchable:boolean;
  constructor(
    private service: UserService,
    private VideoService: VideoService,
    private commentService: CommentService,
    private route: ActivatedRoute,
    private router: Router,

  ) {
    this.sub = {
      id: '',
      subname: ''
    };

    if(this.service.getCookie('userLogin')!=''){
       this.current_user = this.service.getCookie('userLogin');
        console.log(this.current_user)}//id user
    else {this.current_user = '0';console.log(this.current_user)}
    
    this.count = 4;
  }
  onPlayerReady(api: VgAPI) {
    this.api = api;
    this.api.getDefaultMedia().subscriptions.ended.subscribe(
      () => {
        // Set the video to the beginning
        this.api.getDefaultMedia().currentTime = 0;
      }
    );
  }
  getVideos(): void {
    //console.log(this.userId);
    this.VideoService.getVideoList(this.userId)
      .subscribe(videos => {this.videos = videos;
        this.show_list = this.videos.slice(0,this.count);
      });

  }
  gotoChannel() {
    this.router.navigate(["channel", this.userId]);
  }
  subcribe() {

    this.sub.id = this.current_user;
    this.sub.subname = this.video.ownername;
    this.service.setSubcriber(this.sub)
      .subscribe(data => {
        //console.log(data);
        if (data == true) {
          notify({
            message: "Bạn đã đăng ký theo dõi " + this.sub.subname,
            position: {
              my: "center",
              at: "center"
            }
          }, "success", 3000);

        }
        else alert("Không thể đăng ký theo dõi");
      });
  }
  download() {
    window.open("http://localhost:8080/test/rest/video2/test3/" + this.id_video)
  }
  more(){
    this.count = this.count + 4;
    this.show_list = this.videos.slice(0,this.count);
  }
  like(){
    //console.log("click");
  }
  gotoVideoinfo(){
    this.router.navigate(['videoinfo',this.id_video])
  }
  delVideo(){
    this.VideoService.deleteVideo(this.id_video)
    .subscribe( data => {
      let response = data;
      if(response){
          notify({
            message: "Video đã được xóa thành công!" ,
            position: {
              my: "center",
              at: "center"
            }
          }, "success", 2000);
          setTimeout(() => {
            this.router.navigate(['home']);
            //window.location.reload();
          }, 2500);
         
      }
      else {
          notify({
            message: "Lỗi, không thể xóa video." ,
            position: {
              my: "center",
              at: "center"
            }
          }, "error", 3000);
      }
    })
  }
  ngOnInit() {
    //this.getVid_DB();
    
    this.route.params
      // (+) converts string 'id' to a number
      .switchMap((params: Params) => this.VideoService.getVid(this.id_video = +params['id']))
      .subscribe((video: any) => {
      this.video = video;
        this.service.getUsers()
          .subscribe(data => {
            this.users = data;
            this.userId = this.users.find(x => x.username === this.video.ownername).id;
            this.getVideos();
          });
      })
        
    this.VideoService.check_privacy(this.id_video,this.current_user)
    .subscribe(data => {
      let response = data; 
      this.watchable = response; 
      if(!this.watchable){
        notify({
            message: "Bạn không có quyền xem video này " ,
            position: {
              my: "center",
              at: "center"
            }
          }, "warning", 3000);
      }
    })
  }

}