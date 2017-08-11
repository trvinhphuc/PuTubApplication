import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Router } from '@angular/router';
import { UserService } from './../services/user.service';
import notify from 'devextreme/ui/notify';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [UserService],
})
export class LoginComponent implements OnInit {
  user: any;
  constructor(private loginService: UserService, private router: Router) {
    this.user = {
      username: '',
      password: ''
    };
  }
  login() {
    //console.log(this.user);
    this.loginService.login(this.user)
      .subscribe(data => {
        //console.log(data);
        if (data == true) {
          this.router.navigate(['home']);
          notify({
            message: "Chào mừng bạn trở lại với PuTub ^^",
            position: {
              my: "center",
              at: "center"
            }
          }, "success", 2000);
          setTimeout(() => {
            window.location.reload();
          }, 2500);

        }
        else alert("Wrong username or password");


      });

  }

  ngOnInit() {
    //this.login();
  }

}
