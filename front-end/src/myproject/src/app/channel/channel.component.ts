import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';
import notify from 'devextreme/ui/notify';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserService } from './../services/user.service';
@Component({
  selector: 'channel',
  templateUrl: './channel.component.html',
  styleUrls: ['./channel.component.css'],
  providers: [UserService],
})
export class ChannelComponent implements OnInit {
  user: any;
  imageData: any;
  a: Observable<any>;
  current_user:any;
  sub:any;
  constructor(
    private service: UserService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.user = {
      id: '',
      ava: ''
    };
    this.sub = {
      id: '',
      subname: ''
    };
    this.current_user = this.service.getCookie('userLogin');
  }
  logout(){
    this.service.setCookie('userLogin',null,-1);
    sessionStorage.clear();
    this.router.navigate(["home"]);
    window.location.reload();
  }
  
  subcribe() {

    this.sub.id = this.current_user;
    this.sub.subname = this.user.username;
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
  ngOnInit() {
      this.route.params
      .switchMap((params: Params) => this.service.getAvatar(this.user.id=+params['id']))
      .subscribe(imageData => {
        this.imageData = imageData
      })
      sessionStorage.setItem('c_u',this.user.id);
      this.service.getUserInfo(this.user.id)
      .subscribe(data => this.user = data)
  }

}
