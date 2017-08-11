import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
  selector: 'app-root',
  styleUrls: ['./app.component.css'],
  templateUrl: './app.component.html',
  providers: [UserService],
})
export class AppComponent implements OnInit {
  title = 'PuTub';
  isLogin: boolean;
  user: any;
  constructor(
    private service: UserService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.user = {
      id: ''
    }
  }
  gotoInfo(){
    this.router.navigate(['userinfo',this.user.id])
  }
  checkLogin() {
    //this.isLogin = false;
    if (this.service.getCookie("userLogin") != "") {
      this.isLogin = true;
      this.user.id = this.service.getCookie("userLogin");
      this.service.setCookie('userLogin', this.user.id, 48);
      this.service.getUserInfo(this.user.id)
        .subscribe(data => {
          let response = data;
          sessionStorage.setItem('userinfo', JSON.stringify(response))
        })

    }


    //setTimeout(window.location.reload(),100);
  }

  ngOnInit() {
    this.checkLogin();
    //setTimeout(window.location.reload(),100);
  }

}