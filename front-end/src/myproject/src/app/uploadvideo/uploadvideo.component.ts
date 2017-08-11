import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { DxCheckBoxModule, DxFileUploaderModule, DxSelectBoxModule } from 'devextreme-angular';
import { VideoService } from "../services/video_service.service";
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-uploadvideo',
  templateUrl: './uploadvideo.component.html',
  styleUrls: ['./uploadvideo.component.css'],
  providers: [VideoService]
})
export class UploadvideoComponent implements OnInit {
  video: any;
  value: any[] = [];
  priv_choices: any[];
  visible: boolean;
  privacy: any;
  shared_users: any = '';
  constructor(
    private videoservice: VideoService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.video = {
      videoname: '',
      descript: '',
      tag: '',
      realname: '',
      ownername: '',
      privacy: '',
    }
    this.priv_choices = [
      'Công khai',
      'Không công khai',
      'Riêng tư'];
    this.visible = false;
  }
  checkPrivacy() {
    if(this.privacy == ('' || 'Công khai')){
      this.video.privacy = '0';
      this.visible = false;
    }
    else if( this.privacy == 'Riêng tư' ){
      this.visible = true;
      this.video.privacy = JSON.parse(sessionStorage.getItem('userinfo')).username;
    }
    else {
      this.visible = false;
      this.video.privacy = JSON.parse(sessionStorage.getItem('userinfo')).username;
    }
    
    //console.log(this.video);
  }
  sendVideoInfo() {
    if (this.video.realname != '') {
      this.video.realname = this.video.realname[0].name;
      this.video.ownername = JSON.parse(sessionStorage.getItem('userinfo')).username;
      if(this.video.privacy != '0')
        this.video.privacy += this.shared_users;
      this.videoservice.sendVideo(this.video)
        .subscribe(data => {
          if (data == true) {
            notify({
              message: "Chúc mừng bạn đã đăng tải thành công!!! ",
              position: {
                my: "center top",
                at: "center top"
              }
            }, "success", 3000);
            this.router.navigate(['home']);
          }
          else alert("Can not upload");
        })
    }
    else {
      notify({
        message: "Bạn chưa chọn video kìa!! ",
        position: {
          my: "center",
          at: "center"
        }
      }, "error", 3000);
    }

  }
  ngOnInit() {

  }

}