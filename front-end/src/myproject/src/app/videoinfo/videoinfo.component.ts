import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VideoService } from "../services/video_service.service";
import { UserService } from "../services/user.service";
import notify from 'devextreme/ui/notify';
import { VgAPI } from 'videogular2/core';
@Component({
  selector: 'app-videoinfo',
  templateUrl: './videoinfo.component.html',
  styleUrls: ['./videoinfo.component.css'],
  providers: [VideoService, UserService],
})
export class VideoinfoComponent implements OnInit {
  preload: string = 'auto';
  api: VgAPI;
  video: any;
  value: any[] = [];
  priv_choices: any[];
  visible: boolean;
  privacy: any;
  shared_users: any = '';
  id_video: any;
  current_user: any;
  watchable: boolean;
  constructor(
    private videoservice: VideoService,
    private userservice: UserService,
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
    this.current_user = this.userservice.getCookie('userLogin');//id user
  }
  checkPrivacy() {
    if (this.privacy == ('' || 'Công khai')) {
      this.video.privacy = '0';
      this.visible = false;
    }
    else if (this.privacy == 'Riêng tư') {
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
    if (this.video.privacy != '0')
      this.video.privacy += this.shared_users;
    this.videoservice.updateVideo(this.video)
      .subscribe(data => {
        if (data == true) {
          notify({
            message: "Đã chỉnh sửa thông tin thành công. ",
            position: {
              my: "center top",
              at: "center top"
            }
          }, "success", 3000);

        }
        else alert("Can not update");
      })

  }
  delVideo() {
    this.videoservice.deleteVideo(this.id_video)
      .subscribe(data => {
        let response = data;
        if (response) {
          notify({
            message: "Video đã được xóa thành công!",
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
            message: "Lỗi, không thể xóa video.",
            position: {
              my: "center",
              at: "center"
            }
          }, "error", 3000);
        }
      })
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
  ngOnInit() {
    this.route.params
      // (+) converts string 'id' to a number
      .switchMap((params: Params) => this.videoservice.getVid(this.id_video = +params['id']))
      .subscribe(data => {
        this.video = data;
        if (this.video.privacy != '0') {
          if (this.video.privacy != JSON.parse(sessionStorage.getItem('userinfo')).username) {
            this.visible = true;
            this.shared_users = this.video.privacy;
          }
          else this.privacy = 'Không công khai'
        }
        this.videoservice.check_privacy(this.id_video, this.current_user)
          .subscribe(data => {
            let response = data;
            this.watchable = response;
            if (!this.watchable) {
              notify({
                message: "Bạn không có quyền xem video này ",
                position: {
                  my: "center",
                  at: "center"
                }
              }, "warning", 3000);
            }
          })
      })

  }

}
