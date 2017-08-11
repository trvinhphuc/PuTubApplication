import { Component, OnInit } from '@angular/core';
import { UserService } from "../services/user.service";
import { VideoService } from "../services/video_service.service";
import { SearchService } from "../services/search.service";
import { Router, ActivatedRoute, Params } from '@angular/router';
import notify from 'devextreme/ui/notify';
@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css'],
  providers: [UserService, VideoService, SearchService]
})
export class SearchpageComponent implements OnInit {
  users: any[] = [];
  videos: any[] = [];
  ser: any;
  searchValue: string;
  option: any;
  check: boolean;
  result: any;
  //user: any;
  constructor(
    private userService: UserService,
    private videoService: VideoService,
    private searchService: SearchService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.searchValue = '';
    this.option = {
      user: '',
      video: '',
    }
    this.check = true;

  }
  checkValidation() {
    if (this.option.user == this.option.video) {
      let msg;
      if (this.option.user != '')
        msg = "Chọn 1 thôi nha";
      else msg = "Chọn thứ bạn cần tìm nào"
      notify({
        message: msg,
        position: {
          my: "center",
          at: "center"
        }
      }, "error", 2000);
      return false;
    }
    else return true

  }
  checkEmp() {
    if (this.searchValue != null) {
      this.check = false;
    }
    if (this.searchValue == "") {
      this.check = true;
    }
  }
  searchRequest() {
    //this.searchValue = null;
    if (this.checkValidation()) {
      let op: string;
      if (this.option.user == true) op = 'user';
      if (this.option.video == true) op = 'video';
      this.searchService.search(this.searchValue,op)
        .subscribe(data => {
          if (data != "No result") {
            if (op == 'user') {
              this.users = data;
              this.videos = null;
              this.result = null;
              for (let user of this.users) {
                this.userService.getAvatar(user.id)
                  .subscribe(imageData => user.ava = imageData)
              }
            }
            if (op == 'video') {
              this.videos = data;
              this.users = null;
              this.result = null;
            }

          }
          else {
            this.result = data;
            this.videos = null;
            this.users = null;
          }
        })
    }
  }

  ngOnInit() {
    let op;
    this.route.params
      .switchMap((params: Params) => this.searchService.search(params['searchQuery'], op = params['option'], ))
      .subscribe(data => {
        if (data != "No result") {
          if (op == 'user') {
            this.users = data;
            this.option.user = true;
            for (let user of this.users) {
              this.userService.getAvatar(user.id)
                .subscribe(imageData => user.ava = imageData)
            }
          }
          if (op == 'video') {
            this.videos = data;
            this.option.video = true;
            console.log(this.videos);
          }

        }
        else this.result = data;

      })
  }
}
/* //search engine
    this.userService.getUsers()
    .subscribe(data => {
      this.users = data;
      for(let i=0; i< this.users.length ;i++){
          this.videoService.getVideoList(this.users[i].id)
          .subscribe(vid => {
            if(vid.length!=0){
              for(let i=0 ; i<vid.length; i++)
                this.videos.push(vid[i]);
            }
            //console.log(this.videos);
          })        
      }
      console.log(this.users);
      console.log(this.videos);
      let user = this.users.find( x => x.username == this.ser.value)
      if(user!=null) console.log(user);
      let video = this.videos.find( x => x.videoname == this.ser.value)
      if(video!=null) console.log(video);      
    })
  */